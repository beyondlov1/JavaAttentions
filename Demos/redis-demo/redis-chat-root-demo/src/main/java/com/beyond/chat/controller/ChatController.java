package com.beyond.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RestController
public class ChatController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/signup")
    public Object signUp(){
        redisTemplate.opsForValue().set("hello","world");
        return "ok";
    }

    @RequestMapping("/signin")
    public Object signIn(String username,HttpServletRequest request, HttpServletResponse response){
        Cookie foundCookie = getCookie(request,"username");
        if (foundCookie == null){
            Cookie cookie = new Cookie("username",username);
            response.addCookie(cookie);
        }
        return "ok";
    }

    @RequestMapping("/sync")
    public Object sync(String channel,HttpServletRequest request, HttpServletResponse response){
        Cookie foundOffsetCookie = getCookie(request,"offset");
        if (foundOffsetCookie == null){
            long size = 0L;
            if (redisTemplate.hasKey(channel)){
                size = redisTemplate.opsForList().size(channel);
            }
            Cookie cookie = new Cookie("offset",String.valueOf(size));
            response.addCookie(cookie);
            return null;
        }
        int offset = Integer.valueOf(foundOffsetCookie.getValue());
        if (redisTemplate.hasKey(channel)){
            int baseOffset = getBaseOffset();
            Cookie cookie = new Cookie("offset",String.valueOf(redisTemplate.opsForList().size(channel)+baseOffset));
            response.addCookie(cookie);
            return redisTemplate.opsForList().range(channel,offset-baseOffset,Integer.MAX_VALUE);
        }
        return null;
    }

    private Cookie getCookie(HttpServletRequest request,String name) {
        Cookie foundCookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)){
                foundCookie = cookie;
                break;
            }
        }
        return foundCookie;
    }

    @RequestMapping("/send")
    public Object send(String channel, String msg,HttpServletRequest request){
        msg = Objects.requireNonNull(getCookie(request, "username")).getValue() + ":" +msg;
        String finalMsg = msg;
        redisTemplate.setEnableTransactionSupport(true);
        Long size = redisTemplate.opsForList().size(channel);
        int baseOffset = getBaseOffset();
        redisTemplate.multi();
        if (size >100){
            redisTemplate.opsForList().leftPop(channel);
            redisTemplate.opsForValue().set("baseOffset",String.valueOf(++baseOffset));
        }
        redisTemplate.opsForList().rightPush(channel, finalMsg);
        redisTemplate.exec();
        return "ok";
    }


    private int getBaseOffset() {
        int baseOffset = 0;
        if (redisTemplate.hasKey("baseOffset")){
            String baseOffsetStr = redisTemplate.opsForValue().get("baseOffset");
            baseOffset = Integer.valueOf(baseOffsetStr);
        }
        return baseOffset;
    }

}

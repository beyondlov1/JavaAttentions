package com.beyond.demo.controller;

import com.beyond.demo.bean.User;
import com.beyond.demo.playground.spring.jms.mdp1.SpringSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("articleId")
public class SpringTraceController implements EnvironmentAware {

    private Environment environment;

    private String[] sensitiveStrs = new String[] {"a","b"};

    @ModelAttribute("comment")
    public String replaceSensitiveStr(String comment){
        if (comment == null){
            return null;
        }
        for (String sensitiveStr : sensitiveStrs) {
            comment = comment.replace(sensitiveStr,"");
        }
        return comment;
    }

    @RequestMapping("/article/{articleId}/comment")
    public String comment(@PathVariable String articleId,RedirectAttributes redirectAttributes, Model model){
        redirectAttributes.addFlashAttribute("comment", model.asMap().get("comment"));
        redirectAttributes.addFlashAttribute("articleId",articleId);
        model.addAttribute("articleId",articleId);
        return "redirect:/showArticle";
    }

    @RequestMapping("/showArticle")
    public String showArticle(Model model){
        Object articleId = model.asMap().get("articleId");
        model.addAttribute("articleTitle","articleTitle"+articleId);
        model.addAttribute("article","articleContent"+articleId);
        return "demo";
    }


    @RequestMapping("/showUser")
    public HttpEntity showUser(User user){
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        return new HttpEntity<>(user);
    }


    @Autowired
    SpringSender springSender;
    @RequestMapping("/sendMessage")
    @ResponseBody
    public Object sendMessage(){
        springSender.send();
        return "ok";
    }


    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}

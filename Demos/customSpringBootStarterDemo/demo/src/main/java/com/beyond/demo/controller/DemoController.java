package com.beyond.demo.controller;

import com.beyond.demo.mapper.UserMapper;
import com.beyond.demo.playground.configable.SelfCreated;
import com.beyond.demo.playground.configable.SelfCreatedInterface;
import org.jooq.DSLContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * @author beyondlov1
 * @date 2019/04/05
 */
@RestController
public class DemoController implements ApplicationContextAware {

    @Autowired
    DSLContext create;

    private ApplicationContext applicationContext;


//    @RequestMapping("/test")
//    public Object test() {
//        Schema schema = TEST.getSchema();
//        System.out.println(schema.getTables().get(1).getName());
//        this.create.insertInto(TEST).set(TEST.ID,"23434").set(TEST.USERNAME,"username2").set(TEST.PASSWORD,"password2").execute();
//        Result<TestRecord> result = this.create.selectFrom(TEST).fetch();
//        List<String> list = new ArrayList<>();
//        for (TestRecord testRecord : result) {
//            String username = testRecord.getUsername();
//            list.add(username);
//        }
//
//        return list;
//    }


    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/testRest")
    public Object testRest() {
        return restTemplate.getForEntity("http://www.baidu.com", String.class);
    }

    @RequestMapping("/testPathVariable/{id}")
    public Object testPathVariable(@PathVariable String id){
        System.out.println(id);
        SelfCreatedInterface selfCreated = new SelfCreated();
        DemoController demoController = ((SelfCreated) selfCreated).getDemoController();
        System.out.println(demoController);
        return id;
    }

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/testMybatis")
    public Object testMybatis(){
        return userMapper.selectAllUser();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}

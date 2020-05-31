package com.beyond.demo.controller;

import com.beyond.demo.mapper.tables.records.TestRecord;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.beyond.demo.mapper.Tables.TEST;


/**
 * @author beyondlov1
 * @date 2019/04/05
 */
@RestController
public class DemoController {

    @Autowired
    DSLContext create;

    @RequestMapping("/test")
    public Object test() {
        Schema schema = TEST.getSchema();
        System.out.println(schema.getTables().get(1).getName());
        this.create.insertInto(TEST).set(TEST.ID,"23434").set(TEST.USERNAME,"username2").set(TEST.PASSWORD,"password2").execute();
        Result<TestRecord> result = this.create.selectFrom(TEST).fetch();
        List<String> list = new ArrayList<>();
        for (TestRecord testRecord : result) {
            String username = testRecord.getUsername();
            list.add(username);
        }

        return list;
    }
}

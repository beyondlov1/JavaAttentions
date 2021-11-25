package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenshipeng
 * @date 2021/11/25
 */
@RestController
public class TestController {

    @Autowired
    @Qualifier("ds1Template")
    JdbcTemplate ds1Template;

    @Autowired
    @Qualifier("ds2Template")
    JdbcTemplate ds2Template;

    @Transactional
    @RequestMapping("hello")
    public Object hello(){
        ds1Template.execute("insert into db_test.book (id, name, page)values(1,'hello',43)");

        ds2Template.execute("insert into db_test.author (id, name, country) values(1,'hello',9999)");

        int i=1/0;
        return "yes";
    }
}

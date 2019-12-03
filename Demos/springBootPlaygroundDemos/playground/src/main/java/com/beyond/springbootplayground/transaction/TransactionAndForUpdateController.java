package com.beyond.springbootplayground.transaction;

import com.beyond.springbootplayground.transaction.repository.TFullTextTestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenshipeng
 * @date 2019/12/02
 */
@RestController
@RequiredArgsConstructor
public class TransactionAndForUpdateController {

    private final TFullTextTestMapper tFullTextTestMapper;

    @RequestMapping("/start")
    @Transactional(rollbackFor = Exception.class)
    public Object start(String name) throws InterruptedException {
        // for update 要加上事务才会起作用
        TFullTextTest tFullTextTest = tFullTextTestMapper.selectByPrimaryKeyForUpdate(1);
        System.out.println(name);
        Thread.sleep(10000);
        return tFullTextTest;
    }
}

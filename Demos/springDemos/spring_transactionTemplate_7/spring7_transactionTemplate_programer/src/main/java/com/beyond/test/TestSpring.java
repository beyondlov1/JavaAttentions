package com.beyond.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.beyond.entity.Account;
import com.beyond.service.AccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestSpring {

	@Resource(name = "accountService")
	private AccountService accountService;

	// 测试事务方法
	@Test
	public void test3() {
		Account source = new Account();
		Account target = new Account();

		source.setId("source");
		source.setMoney(800d);
		target.setId("target");
		target.setMoney(800d);

		accountService.transfer(source, target, 100d);
	}
}

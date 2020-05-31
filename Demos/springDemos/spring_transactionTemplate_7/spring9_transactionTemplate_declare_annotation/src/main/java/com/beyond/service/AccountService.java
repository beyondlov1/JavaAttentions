package com.beyond.service;

import org.springframework.transaction.annotation.Transactional;

import com.beyond.dao.AccountDao;
import com.beyond.entity.Account;

@Transactional
public class AccountService {

	private AccountDao accountDao;

	public void modifyTransfer(Account source, Account target, Double money) {

		source.setMoney(source.getMoney() - money);
		target.setMoney(target.getMoney() + money);

		accountDao.updateMoney(source);
		accountDao.updateMoney(target);

	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
}

package com.beyond.service;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.beyond.dao.AccountDao;
import com.beyond.entity.Account;

public class AccountService {
	private TransactionTemplate transactionTemplate;

	private AccountDao accountDao;

	public void transfer(Account source, Account target, Double money) {

		transactionTemplate.execute(new TransactionCallback<Account>() {

			@Override
			public Account doInTransaction(TransactionStatus status) {
				source.setMoney(source.getMoney() - money);
				target.setMoney(target.getMoney() + money);

				int i = 10 / 0;

				accountDao.updateMoney(source);
				accountDao.updateMoney(target);
				return null;
			}
		});
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
}

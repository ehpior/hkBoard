package com.hk.test.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.hk.test.dao.AccountDao;
import com.hk.test.dao.LoginDao;
import com.hk.test.dto.AccountDto;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	PlatformTransactionManager transactionManager;
	
	public ArrayList<AccountDto> listAccount(){
		
		AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
		
		return accountDao.listAccount();
	}
	
	public int signUpResult(AccountDto accountDto, String salt) {
		
		AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
		
		TransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(definition);
		
		try {
			//account insert
			accountDao.writeAccount(accountDto);
			//account.accnt_id select
			int id = accountDao.selectIdAccount(accountDto.getId());
			//account_salt insert
			accountDao.writeAccountSalt(id, salt);
			
			transactionManager.commit(status);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			transactionManager.rollback(status);
		}
		
		return 1;
	}
	
	public int deleteAccount(int accnt_id) {
		
		AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
		
		int result=0;
		
		TransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(definition);
		
		try {
			
			result = accountDao.deleteAccount(accnt_id);
			result = accountDao.deleteAccountSalt(accnt_id);
			
			transactionManager.commit(status);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			transactionManager.rollback(status);
		}
		
		return result;
	}
}

package com.hk.test.service;

import java.util.ArrayList;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.hk.test.dao.AccountDao;
import com.hk.test.dto.AccountDto;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	PlatformTransactionManager transactionManager;
	
	public ArrayList<AccountDto> selectAccountList(){
		
		AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
		
		return accountDao.selectAccountList();
	}
	
	public int signUpResult(AccountDto accountDto, String salt) {
		
		AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
		
		TransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(definition);
		
		try {

			accountDao.insertAccount(accountDto);

			//int accnt_id = Optional.ofNullable(accountDao.selectAccount(accountDto.getId())).orElse(0).intValue();
			int accnt_id = accountDao.selectAccount(accountDto.getId());
			
			if (accnt_id==0) throw new Exception();
			
			accountDao.insertAccountSalt(accnt_id, salt);
			
			transactionManager.commit(status);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			transactionManager.rollback(status);
		}
		
		return 1;
	}
	
	public int deleteAccount(int accnt_id) {
		
		AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
		
		TransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(definition);
		
		try {
			
			accountDao.deleteAccount(accnt_id);
			accountDao.deleteAccountSalt(accnt_id);
			
			transactionManager.commit(status);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			transactionManager.rollback(status);
		}
		
		return 1;
	}
}

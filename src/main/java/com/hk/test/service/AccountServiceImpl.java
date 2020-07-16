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
	
	public ArrayList<AccountDto> listAccount(){
		
		AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
		
		return accountDao.listAccount();
	}
	
	public int signUpResult(AccountDto accountDto, String salt) {
		
		AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
		
		TransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(definition);
		
		try {

			accountDao.writeAccount(accountDto);

			int id = Optional.ofNullable(accountDao.selectIdAccount(accountDto.getId())).orElse(0).intValue();
			
			if (id==0) throw new Exception();
			
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

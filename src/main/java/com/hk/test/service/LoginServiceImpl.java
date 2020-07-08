package com.hk.test.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.hk.test.dao.AccountDao;
import com.hk.test.dao.LoginDao;
import com.hk.test.dto.AccountDto;
import com.hk.test.dto.LoginDto;
import com.hk.test.dto.LoginHistoryDto;
import com.hk.test.util.SHA256Util;

public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	PlatformTransactionManager transactionManager;

	public AccountDto loginResult(LoginDto loginDto) {
		
		LoginDao dao = sqlSession.getMapper(LoginDao.class);
		AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
		
		TransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(definition);
		
		AccountDto accountDto = null;
		
		try {
			//account.accnt_id select
			int id = accountDao.selectIdAccount(loginDto.getId());
			//salt_value select
			String salt = accountDao.selectAccountSalt(id);
			
			loginDto.setPw(SHA256Util.getEncrypt(loginDto.getPw(),salt));
			
			accountDto = dao.loginCheck(loginDto);
			
			transactionManager.commit(status);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			transactionManager.rollback(status);
		}
		
		
		
		if(accountDto != null) {
			return accountDto;
		}
		return null;
		
	}
	
	public boolean loginHistory(LoginHistoryDto loginHistoryDto) {
		
		LoginDao dao = sqlSession.getMapper(LoginDao.class);
		
		TransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(definition);
		
		try {
			dao.updateLastLoginAccount(loginHistoryDto.getLogin_date(), loginHistoryDto.getAccnt_id());
			//System.out.println(String.valueOf(40/0));
			dao.insertLoginHistory(loginHistoryDto);
			
			transactionManager.commit(status);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			transactionManager.rollback(status);
		}
		
		return false;
	}
}

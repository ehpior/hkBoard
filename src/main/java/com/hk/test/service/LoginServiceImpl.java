package com.hk.test.service;

import java.util.Optional;

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
		
		LoginDao loginDao = sqlSession.getMapper(LoginDao.class);
		AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
		AccountDto accountDto = null;
		
		int id = Optional.ofNullable(accountDao.selectIdAccount(loginDto.getId())).orElse(0).intValue();
		
		if(id==0) return null;
		
		else {		
			TransactionDefinition definition = new DefaultTransactionDefinition();
			TransactionStatus status = transactionManager.getTransaction(definition);
			
			try {
				String salt = accountDao.selectAccountSalt(id);
				
				loginDto.setPw(SHA256Util.getEncrypt(loginDto.getPw(), salt));
				
				if(loginDto.getUser_type().equals("N")) {
					accountDto = loginDao.loginCheckWithType(loginDto);
				}
				else {					
					accountDto = loginDao.loginCheck(loginDto);
				}
				
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
		
	}
	
	public boolean signUpCheckId(String id) {
		
		LoginDao loginDao = sqlSession.getMapper(LoginDao.class);
		
		if(Optional.ofNullable(loginDao.signUpCheckId(id)).orElse(0).intValue() == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean signUpCheckNickname(String nickname) {
		
		LoginDao loginDao = sqlSession.getMapper(LoginDao.class);
		
		if(Optional.ofNullable(loginDao.signUpCheckNickname(nickname)).orElse(0).intValue() == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean loginHistory(LoginHistoryDto loginHistoryDto) {
		
		LoginDao loginDao = sqlSession.getMapper(LoginDao.class);
		
		TransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(definition);
		
		try {
			
			loginDao.updateLastLoginAccount(loginHistoryDto.getLogin_date(), loginHistoryDto.getAccnt_id());

			loginDao.insertLoginHistory(loginHistoryDto);
			
			transactionManager.commit(status);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			transactionManager.rollback(status);
		}
		
		return false;
	}
}

package com.hk.test.controller;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.hk.test.dto.AccountDto;
import com.hk.test.dto.BoardDto;
import com.hk.test.dto.LoginDto;
import com.hk.test.dto.LoginHistoryDto;
import com.hk.test.naver.NaverLoginBO;
import com.hk.test.service.AccountServiceImpl;
import com.hk.test.service.BoardServiceImpl;
import com.hk.test.service.LoginServiceImpl;
import com.hk.test.util.CommUtil;
import com.hk.test.util.RSAUtil;
import com.hk.test.util.SHA256Util;


@Controller
public class LoginController{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	PlatformTransactionManager transactionManager;
	
	@Autowired
	LoginServiceImpl loginService;
	
	@Autowired
	BoardServiceImpl boardService;
	
	@Autowired
	AccountServiceImpl accountService;
	
	@Autowired
	NaverLoginBO naverLoginBO;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value = "/login.hk")
	public String login(HttpServletRequest request, HttpSession session, Model model) {
		System.out.println("/login.hk");
		
		return "login";
	}
	
	//public String loginResult(HttpServletRequest request, HttpSession session, LoginDto loginDto) {
	@RequestMapping(value = "/loginResult")
	public @ResponseBody JSONObject loginResult(HttpServletRequest request, HttpSession session) {
		
		System.out.println("/loginResult");
		
		JSONObject listObj = new JSONObject();
		
		String id = request.getParameter("user_id");
		String pw = request.getParameter("user_pwd");
		
		try {
			PrivateKey privateKey = (PrivateKey) session.getAttribute("_RSA_WEB_Key_");
			
			session.removeAttribute("_RSA_WEB_Key_");
			
			if (privateKey == null) 
			{ 
				listObj.put("state","false");
				return listObj;
			}
	
			id = RSAUtil.decryptRsa(privateKey, id);
			pw = RSAUtil.decryptRsa(privateKey, pw);
	        
			listObj.put("state","true");
			
		}catch(Exception e) {
			System.out.println("exception");
			listObj.put("state","false");
			return listObj;
		}
		
		LoginDto loginDto = new LoginDto(id, pw);
		
		AccountDto dto = loginService.loginResult(loginDto);
		
		if(dto != null) {
			if(session.getAttribute("login")!=null) {
				session.removeAttribute("login");
			}
			session.setAttribute("login", dto);
		}else {
			listObj.put("state","false");
			return listObj;
		}

		String ua = request.getHeader("user-agent");
		
		LoginHistoryDto loginHistoryDto = new LoginHistoryDto(dto.getAccnt_id(),
				new Timestamp(System.currentTimeMillis()), CommUtil.mobileCheck(ua), 
				CommUtil.getClientIP(request), CommUtil.getClientBrowser(ua), CommUtil.getClientOS(ua));
		
		loginService.loginHistory(loginHistoryDto);
		
		return listObj;
	}
	
	@RequestMapping(value = "/loginResultNaver")
	// public String loginResultNaver(HttpServletRequest request, HttpSession
	// session, LoginDto loginDto) {
	public String loginResultNaver(Model model, @RequestParam String code, @RequestParam String state,
			HttpSession session) throws IOException, ParseException {
		
		System.out.println("/loginResultNaver");
		OAuth2AccessToken oauthToken = naverLoginBO.getAccessToken(session, code, state);
		// 1. 
		String apiResult = naverLoginBO.getUserProfile(oauthToken); // 
		/**
		 * apiResult json  {"resultcode":"00", "message":"success",
		 * "response":{"id":"33666449","nickname":"shinn****","age":"20-29","gender":"M","email":"sh@naver.com","name":"\uc2e0\ubc94\ud638"}}
		 **/
		// 2.
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
		// 3.
		JSONObject response_obj = (JSONObject) jsonObj.get("response");
		
		String naver_id = (String)response_obj.get("id");
		String naver_name = (String)response_obj.get("name");
		
		AccountDto accountDto = new AccountDto();

		accountDto.setId(naver_id);
		accountDto.setName(naver_name);
		
		// 4.
		model.addAttribute("loginNaver", accountDto);
		//model.addAttribute("result", apiResult);
		return "loginNaver";
	}
	
	@RequestMapping(value = "/getRSA")
	public @ResponseBody JSONObject getRSA(HttpSession session) {
		System.out.println("/getRSA");
		
		JSONObject listObj = new JSONObject();
		
		try {
		
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(1024);
			KeyPair keyPair = generator.genKeyPair();
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey publicKey = keyPair.getPublic();
			PrivateKey privateKey = keyPair.getPrivate();
	 
			session.setAttribute("_RSA_WEB_Key_", privateKey);   //RSA 개인키 세션 저장
			RSAPublicKeySpec publicSpec = (RSAPublicKeySpec) keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
			String publicKeyModulus = publicSpec.getModulus().toString(16);
			String publicKeyExponent = publicSpec.getPublicExponent().toString(16);
	 
			listObj.put("RSAModulus", publicKeyModulus);  //RSA Modulus set
			listObj.put("RSAExponent", publicKeyExponent);   //RSA Exponent set
        
		}catch(Exception e) {
			
		}
		return listObj;
	}
	
	@ResponseBody
	@RequestMapping(value = "/loginNaverUrl")
	public String loginNaverUrl(HttpSession session) {
		System.out.println("/loginNaverUrl");
		
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		
		return naverAuthUrl;
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session, LoginDto loginDto) {
		System.out.println("/logout");
		
		session.removeAttribute("login");
		
		return "redirect:/home.hk";
	}
	
	@RequestMapping(value = "/signUp.hk", method = RequestMethod.GET)
	public String signUp(Model model) {
		
		System.out.println("/signUp.hk");
		
		return "signUp";
	}
	
	@RequestMapping(value = "/signUpResult", method = RequestMethod.POST)
	public String signUpResult(AccountDto accountDto, HttpServletRequest request, HttpSession session, Model model) {
		
		System.out.println("/signUpResult");
	
		try {
			
			PrivateKey privateKey = (PrivateKey) session.getAttribute("_RSA_WEB_Key_");
			
			session.removeAttribute("_RSA_WEB_Key_");
			
			if (privateKey == null) 
			{ 
				System.out.println("privateKeyNull");
				return "signUp";
			}
	
			accountDto.setNickname(RSAUtil.decryptRsa(privateKey, accountDto.getNickname()));
			accountDto.setName(RSAUtil.decryptRsa(privateKey, accountDto.getName()));
			accountDto.setUser_type(RSAUtil.decryptRsa(privateKey, accountDto.getUser_type()));
			accountDto.setPhone(RSAUtil.decryptRsa(privateKey, accountDto.getPhone()));
			accountDto.setId(RSAUtil.decryptRsa(privateKey, accountDto.getId()));
			accountDto.setS_passwd(RSAUtil.decryptRsa(privateKey, accountDto.getS_passwd()));
			
			System.out.println(accountDto.getNickname());
			
			System.out.println(accountDto.toString());
			
		}catch(Exception e) {
			
		}
		
		
		String salt = SHA256Util.generateSalt();
        
        accountDto.setS_passwd(SHA256Util.getEncrypt(accountDto.getS_passwd(), salt));
		
		accountService.signUpResult(accountDto, salt);

		return "redirect:/home.hk";
		
	}
	
	@RequestMapping(value = "/signUpCheckId")
	public @ResponseBody JSONObject signUpCheckId(HttpServletRequest request, HttpSession session) {
		System.out.println("/signUpCheckId");
		
		JSONObject listObj = new JSONObject();
		
		String id = request.getParameter("id");
		
		if(loginService.signUpCheckId(id)){
			listObj.put("signUpCheckId", 1);
		}
		else {
			listObj.put("signUpCheckId", 0);
		}
		
   
		return listObj;
	}
	
	@RequestMapping(value = "/signUpCheckNickname")
	public @ResponseBody JSONObject signUpCheckNickname(HttpServletRequest request, HttpSession session) {
		System.out.println("/signUpCheckNickname");
		
		JSONObject listObj = new JSONObject();
		
		String nickname = request.getParameter("nickname");
		
		if(loginService.signUpCheckId(nickname)){
			listObj.put("signUpCheckNickname", 1);
		}
		else {
			listObj.put("signUpCheckNickname", 0);
		}
		
		return listObj;
	}
	
}

package com.hk.test.util;

import javax.servlet.http.HttpServletRequest;

public class CommUtil {

	public static String getClientIP(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		// logger.info("> X-FORWARDED-FOR : " + ip);

		if (ip == null) {
			ip = request.getHeader("Proxy-Client-IP");
			// logger.info("> Proxy-Client-IP : " + ip);
		}
		if (ip == null) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			// logger.info("> WL-Proxy-Client-IP : " + ip);
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_CLIENT_IP");
			// logger.info("> HTTP_CLIENT_IP : " + ip);
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			// logger.info("> HTTP_X_FORWARDED_FOR : " + ip);
		}
		if (ip == null) {
			ip = request.getRemoteAddr();
			// logger.info("> getRemoteAddr : "+ip);
		}
		// logger.info("> Result : IP Address : "+ip);

		return ip;
	}

	public static String mobileCheck(String ua) {

		if (ua.indexOf("iPhone") != -1 || ua.indexOf("iPad") != -1 || ua.indexOf("Android") != -1
				|| ua.indexOf("BlackBerry") != -1 || ua.indexOf("symbian") != -1 || ua.indexOf("sony") != -1
				|| ua.indexOf("Mobile") != -1) {
			return "Y";
		} else {
			return "N";
		}
	}

	public static String getClientOS(String userAgent) {

		String os = "";

		userAgent = userAgent.toLowerCase();

		if (userAgent.indexOf("windows nt 6.1") > -1) {
			os = "Windows7";
		} else if (userAgent.indexOf("windows nt 6.2") > -1 || userAgent.indexOf("windows nt 6.3") > -1) {
			os = "Windows8";
		} else if (userAgent.indexOf("windows nt 10.0") > -1) {
			os = "Windows10";
		} else if (userAgent.indexOf("windows nt 6.0") > -1) {
			os = "WindowsVista";
		} else if (userAgent.indexOf("windows nt 5.1") > -1) {
			os = "WindowsXP";
		} else if (userAgent.indexOf("windows nt 5.0") > -1) {
			os = "Windows2000";
		} else if (userAgent.indexOf("windows nt 4.0") > -1) {
			os = "WindowsNT";
		} else if (userAgent.indexOf("windows 98") > -1) {
			os = "Windows98";
		} else if (userAgent.indexOf("windows 95") > -1) {
			os = "Windows95";
		}
		// window 외
		else if (userAgent.indexOf("iphone") > -1) {
			os = "iPhone";
		} else if (userAgent.indexOf("ipad") > -1) {
			os = "iPad";
		} else if (userAgent.indexOf("android") > -1) {
			os = "android";
		} else if (userAgent.indexOf("mac") > -1) {
			os = "mac";
		} else if (userAgent.indexOf("linux") > -1) {
			os = "Linux";
		} else {
			//os = userAgent;
			os = "unknown";
		}
		return os;
	}

	public static String getClientBrowser(String userAgent) {
		String browser = "";

		if (userAgent.indexOf("Trident/7.0") > -1) {
			browser = "ie11";
		} else if (userAgent.indexOf("MSIE 10") > -1) {
			browser = "ie10";
		} else if (userAgent.indexOf("MSIE 9") > -1) {
			browser = "ie9";
		} else if (userAgent.indexOf("MSIE 8") > -1) {
			browser = "ie8";
		} else if (userAgent.indexOf("Chrome/") > -1) {
			browser = "Chrome";
		} else if (userAgent.indexOf("Chrome/") == -1 && userAgent.indexOf("Safari/") >= -1) {
			browser = "Safari";
		} else if (userAgent.indexOf("Firefox/") >= -1) {
			browser = "Firefox";
		} else {
			//browser = userAgent;
			browser = "unknown";
		}
		return browser;
	}
	
	public static int parseInt(String tmp) {
		
		if(tmp==null || tmp=="") {
			return 0;
		}
		else {
			return Integer.parseInt(tmp);
		}
	}

}

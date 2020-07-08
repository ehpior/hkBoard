package com.hk.test.test;

import com.hk.test.util.SHA256Util;

public class RSATest {
    public static void main(String[] args) throws Exception {
       
        
        String salt = SHA256Util.generateSalt();
        String password = "testpw";
        
        password = SHA256Util.getEncrypt(password, salt);
        
        System.out.println("shapassword: " + password);
        
        
        
    }
}
package com.hk.test.test;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import com.hk.test.util.CipherUtil;
import com.hk.test.util.SHA256Util;

public class RSATest {
    public static void main(String[] args) throws Exception {
        // RSA 키쌍을 생성합니다.
        KeyPair keyPair = CipherUtil.genRSAKeyPair();

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        String plainText = "test123";

        // Base64 인코딩된 암호화 문자열 입니다.
        String encrypted = CipherUtil.encryptRSA(plainText, publicKey);
        System.out.println("encrypted : " + encrypted);

        // 복호화 합니다.
        String decrypted = CipherUtil.decryptRSA(encrypted, privateKey);
        System.out.println("decrypted : " + decrypted);

        // 공개키를 Base64 인코딩한 문자일을 만듭니다.
        byte[] bytePublicKey = publicKey.getEncoded();
        String base64PublicKey = Base64.getEncoder().encodeToString(bytePublicKey);
        System.out.println("Base64 Public Key : " + base64PublicKey);

        // 개인키를 Base64 인코딩한 문자열을 만듭니다.
        byte[] bytePrivateKey = privateKey.getEncoded();
        String base64PrivateKey = Base64.getEncoder().encodeToString(bytePrivateKey);
        System.out.println("Base64 Private Key : " + base64PrivateKey);
        
        
        String salt = SHA256Util.generateSalt();
        String password = "testpw";
        
        password = SHA256Util.getEncrypt(password, salt);
        
        
        
    }
}
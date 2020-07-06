package com.hk.test.test;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import com.hk.test.util.CipherUtil;
import com.hk.test.util.SHA256Util;

public class RSATest {
    public static void main(String[] args) throws Exception {
        // RSA Ű���� �����մϴ�.
        KeyPair keyPair = CipherUtil.genRSAKeyPair();

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        String plainText = "test123";

        // Base64 ���ڵ��� ��ȣȭ ���ڿ� �Դϴ�.
        String encrypted = CipherUtil.encryptRSA(plainText, publicKey);
        System.out.println("encrypted : " + encrypted);

        // ��ȣȭ �մϴ�.
        String decrypted = CipherUtil.decryptRSA(encrypted, privateKey);
        System.out.println("decrypted : " + decrypted);

        // ����Ű�� Base64 ���ڵ��� �������� ����ϴ�.
        byte[] bytePublicKey = publicKey.getEncoded();
        String base64PublicKey = Base64.getEncoder().encodeToString(bytePublicKey);
        System.out.println("Base64 Public Key : " + base64PublicKey);

        // ����Ű�� Base64 ���ڵ��� ���ڿ��� ����ϴ�.
        byte[] bytePrivateKey = privateKey.getEncoded();
        String base64PrivateKey = Base64.getEncoder().encodeToString(bytePrivateKey);
        System.out.println("Base64 Private Key : " + base64PrivateKey);
        
        
        String salt = SHA256Util.generateSalt();
        String password = "testpw";
        
        password = SHA256Util.getEncrypt(password, salt);
        
        
        
    }
}
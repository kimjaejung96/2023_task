package com.tredlinx.task.common.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
@Slf4j
public class Encryptor {
    private Encryptor() {
    }

    public static String encrypt(String value) {
        StringBuilder stringBuffer = new StringBuilder();

        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");

        } catch (NoSuchAlgorithmException e) {
            log.error("\nEncrypt Error : {}", e.getMessage());
            throw new RuntimeException();
        }
        messageDigest.update(value.getBytes());

        byte[] digest = messageDigest.digest() ;

        for(int i=0; i < digest.length; i++){
            byte tmpStrByte = digest[i];
            String encryptedTxt = Integer.toString((tmpStrByte & 0xff) + 0x100, 16).substring(1);

            stringBuffer.append(encryptedTxt) ;
        }

        return stringBuffer.toString();
    }
}

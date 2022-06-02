package com.example.digiitplay;

import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;

public class EncryptDecrypt {

    public String encrypt(String plaintext) {
        String encrypted = "1";
        try {
            encrypted = AESCrypt.encrypt("1123581321345589", plaintext);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return encrypted;
    }

    public String decrypt(String ciphertext) {
        String decrypted = "1";
        try {
            decrypted = AESCrypt.decrypt("1123581321345589", ciphertext);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return decrypted;
    }
}

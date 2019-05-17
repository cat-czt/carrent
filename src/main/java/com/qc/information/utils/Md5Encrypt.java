package com.qc.information.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: czt
 * @Date: 18-10-30 下午4:21
 */
public class Md5Encrypt {

    private static final Logger logger = LoggerFactory.getLogger(Md5Encrypt.class);

    public Md5Encrypt() {
    }

    public static String encrypt(String inStr) {
        MessageDigest msgDigest = null;

        try {
            msgDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException var8) {
            logger.error("System doesn't support MD5 algorithm.");
            throw new IllegalStateException("System doesn't support MD5 algorithm.");

        }

        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for(int i = 0; i < charArray.length; ++i) {
            byteArray[i] = (byte)charArray[i];
        }

        byte[] md5Bytes = msgDigest.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();

        for(int i = 0; i < md5Bytes.length; ++i) {
            int val = md5Bytes[i] & 255;
            if (val < 16) {
                hexValue.append("0");
            }

            hexValue.append(Integer.toHexString(val));
        }

        return hexValue.toString();
    }

    public static String encryptFor16(String code) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(code.getBytes());
            return (new BigInteger(1, md.digest())).toString(16);
        } catch (NoSuchAlgorithmException var2) {
            logger.error("System doesn't support MD5 algorithm.");
            throw new IllegalStateException("System doesn't support MD5 algorithm.");
        }
    }

}

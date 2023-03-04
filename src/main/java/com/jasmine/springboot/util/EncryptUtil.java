package com.jasmine.springboot.util;

import java.security.MessageDigest;

/**
 * 加密工具类
 *
 * @author xieshanghan
 * @version EncryptUtil.java, v 0.1 2023年03月03日 11:54 xieshanghan
 */
public class EncryptUtil {

    private static final String MD5 = "MD5";

    /**
     * 计算给定message的Md5值
     *
     * @param message
     * @return
     */
    public static String computeMd5(String message) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance(MD5);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        char[] messageCharArray = message.toCharArray();
        byte[] byteArray = new byte[messageCharArray.length];
        for (int i = 0; i < byteArray.length; i++) {
            byteArray[i] = (byte) messageCharArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

}
package com.jasmine.springboot.util;

import java.nio.charset.Charset;

/**
 * MurmurHash工具类
 *
 * @author xieshanghan
 * @version MurmurHashUtil.java, v 0.1 2023年02月16日 13:39 xieshanghan
 */
public class MurmurHashUtil {

    private static final String UNDERLINE = "-";

    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private static final Charset GBK = Charset.forName("GBK");

    /**
     * 获取UTF-8编码格式的MurmurHash字符串：取Hash的后几位值
     *
     * @param sourceStr    源字符串，GBK编码
     * @param suffixLength 后几位的长度
     * @return
     */
    public static String getMurmurHashUtf8(String sourceStr, int suffixLength) {
        return getMurmurHash(sourceStr, suffixLength, UTF_8);
    }

    /**
     * 获取GBK编码格式的MurmurHash字符串：取Hash的后几位值
     *
     * @param sourceStr    源字符串，GBK编码
     * @param suffixLength 后几位的长度
     * @return
     */
    public static String getMurmurHashGBK(String sourceStr, int suffixLength) {
        return getMurmurHash(sourceStr, suffixLength, GBK);
    }

    private static String getMurmurHash(String sourceStr, int suffixLength, Charset charset) {
        byte[] bytes = null;
        try {
            bytes = sourceStr.getBytes(charset);
        } catch (NullPointerException e) {
            bytes = sourceStr.getBytes();
        }
        int baseValue = (int) Math.pow(10, Math.abs(suffixLength));
        int rehash = Math.abs(murmurHash32(bytes, bytes.length, 0))
                % baseValue;
        // 位数不足4位则补齐
        if (rehash < 10000) {
            rehash += 10000;
        }

        // 取后4位
        String rehashStr = Integer.toString(rehash);
        rehashStr = rehashStr.substring(rehashStr.length() - 4);

        // 拼接后4位，UNDER_SCORE和src
        String result = rehashStr + UNDERLINE + sourceStr;
        return result;
    }

    private static int murmurHash32(byte[] data, int length, int seed) {
        int m = 0x5bd1e995;
        int r = 24;

        int h = seed ^ length;

        int len_4 = length >> 2;

        for (int i = 0; i < len_4; i++) {
            int i_4 = i << 2;
            int k = data[i_4 + 3];
            k = k << 8;
            k = k | (data[i_4 + 2] & 0xff);
            k = k << 8;
            k = k | (data[i_4 + 1] & 0xff);
            k = k << 8;
            k = k | (data[i_4 + 0] & 0xff);
            k *= m;
            k ^= k >>> r;
            k *= m;
            h *= m;
            h ^= k;
        }

        // avoid calculating modulo
        int len_m = len_4 << 2;
        int left = length - len_m;

        if (left != 0) {
            if (left >= 3) {
                h ^= (int) data[length - 3] << 16;
            }
            if (left >= 2) {
                h ^= (int) data[length - 2] << 8;
            }
            if (left >= 1) {
                h ^= (int) data[length - 1];
            }

            h *= m;
        }

        h ^= h >>> 13;
        h *= m;
        h ^= h >>> 15;

        return h;
    }

}
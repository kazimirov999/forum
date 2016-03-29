package com.it.forum.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author : Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public class EncodeUtil {

    private EncodeUtil() {
    }

    public static String md5Encryption(String str) {
        String md5Hex = DigestUtils.md5Hex(str);

        return md5Hex;
    }

}

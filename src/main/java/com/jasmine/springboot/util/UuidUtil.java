package com.jasmine.springboot.util;

import java.util.UUID;

/**
 * @author xieshanghan
 * @version UuidUtil.java, v 0.1 2023年02月24日 14:57 xieshanghan
 */
public class UuidUtil {

    public static String generateUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
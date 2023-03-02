package com.jasmine.springboot.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;

/**
 * 网络工具类
 *
 * @author xieshanghan
 * @version NetworkUtil.java, v 0.1 2023年02月03日 13:35 xieshanghan
 */
public class NetworkUtil {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NetworkUtil.class);

    /** 本机IP地址 */
    private static volatile String ipAddress;

    /** 本机域名 */
    private static volatile String hostName;

    /**
     * 私有构造函数，屏蔽外围调用
     */
    private NetworkUtil() {
        // ignore
    }

    /**
     * 获取本机IP
     *
     * @return 如果获取成功返回本机IP，否则返回null
     */
    public static String getIp() {
        if (StringUtils.isBlank(ipAddress)) {
            initInetAddress();
        }
        return ipAddress;
    }

    /**
     * 获取本机域名
     *
     * @return
     */
    public static String getLocalHostName() {
        if (StringUtils.isBlank(hostName)) {
            initInetAddress();
        }
        return hostName;
    }

    /**
     * 获取IP地址信息相关对象
     *
     * @return
     */
    private static void initInetAddress() {
        InetAddress address = getInetAddress();
        if (address != null) {
            hostName = address.getHostName();
            ipAddress = address.getHostAddress();
        }
    }

    /**
     * 获取inetAddress对象
     *
     * @return
     */
    private static InetAddress getInetAddress() {
        try {
            return InetAddress.getLocalHost();
        } catch (Exception e) {
            return null;
        }
    }

}
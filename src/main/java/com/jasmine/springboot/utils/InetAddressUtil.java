package com.jasmine.springboot.utils;

import com.alibaba.common.lang.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;

/**
 * InetAddress工具类
 *
 * @author xieshanghan
 * @version InetAddressUtil.java, v 0.1 2023年02月03日 13:35 xieshanghan
 */
public class InetAddressUtil {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(InetAddressUtil.class);

    /** 横线  */
    public final static String HORIZONTAL_LINE  = "-";

    /** 本机IP地址 */
    private static volatile String ipAddress;

    /** 本机域名 */
    private static volatile String hostName;

    /**
     * 私有构造函数，屏蔽外围调用
     */
    private InetAddressUtil() {
        // ignore
    }

    /**
     * 获取本机IP
     *
     * @return 如果获取成功返回本机IP，否则返回null
     */
    public static String getServerIp() {
        if (StringUtil.isBlank(ipAddress)) {
            initInetAddress();
        }
        return ipAddress;
    }

    /**
     * 获取本机服务器名称
     *
     * @return
     */
    public static String getServerHostName() {
        if (StringUtil.isBlank(hostName)) {
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
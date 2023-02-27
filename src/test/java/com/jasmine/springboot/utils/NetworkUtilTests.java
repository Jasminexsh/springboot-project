package com.jasmine.springboot.utils;

import com.jasmine.springboot.util.NetworkUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 * @author xieshanghan
 * @version NetworkUtilTests.java, v 0.1 2023年02月15日 17:47 xieshanghan
 */
public class NetworkUtilTests {

    @Test
    public void testGetLocalIp() {
        String ipAddr = NetworkUtil.getIp();
        Assert.assertNotNull(ipAddr);
        System.out.println(ipAddr);
    }

    @Test
    public void testGetLocalHostName() {
        String hostName = NetworkUtil.getLocalHostName();
        Assert.assertNotNull(hostName);
        System.out.println(hostName);
    }

}

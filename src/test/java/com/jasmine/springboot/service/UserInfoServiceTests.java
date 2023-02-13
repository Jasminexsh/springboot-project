package com.jasmine.springboot.service;

import com.jasmine.springboot.model.user.UserInfo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xieshanghan
 * @version UserInfoServiceTests.java, v 0.1 2023年02月10日 17:46 xieshanghan
 */
@SpringBootTest
public class UserInfoServiceTests {

    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void testQueryByNickNameCn() {
        UserInfo userInfo = userInfoService.queryByNickNameCn("逸函");
        Assert.assertNotNull(userInfo);
    }

}
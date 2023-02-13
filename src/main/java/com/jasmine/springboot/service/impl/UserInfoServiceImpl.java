package com.jasmine.springboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.xxpt.gateway.shared.client.http.ExecutableClient;
import com.alibaba.xxpt.gateway.shared.client.http.GetClient;
import com.jasmine.springboot.model.user.UserInfo;
import com.jasmine.springboot.service.UserInfoService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * @author xieshanghan
 * @version UserInfoServiceImpl.java, v 0.1 2023年02月10日 15:52 xieshanghan
 */
@Service
public class UserInfoServiceImpl implements UserInfoService, InitializingBean {

    private static final String HTTPS = "https";

    private static final String NICK_NAME_CN = "nickNameCn";

    private static final String S_DOMAIN = "s-api.alibaba-inc.com";

    private static final String GET_USER_BY_NICKNAME_URI = "/rpc/enhancedUserQuery/getUserByNickNameCn.json";

    private ExecutableClient executableClient;

    private String accessKey;

    private String secretKey;

    @Override
    public UserInfo queryByNickNameCn(String nickNameCn) {
        GetClient getClient = executableClient.newGetClient(GET_USER_BY_NICKNAME_URI);
        getClient.addParameter(NICK_NAME_CN, nickNameCn);
        String userInfoJson = getClient.get();
        UserInfo userInfo = JSON.parseObject(userInfoJson, UserInfo.class);
        return userInfo;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        executableClient = new ExecutableClient();
        executableClient.setProtocal(HTTPS);
        accessKey = "zeroxrisk-I8ATmm2hH1Vl9sJzqeUb";
        secretKey = "UPTT0iQEiSx36T8dXsvTENth9FHJOIG4V7AfK7di";
        executableClient.setAccessKey(accessKey);
        executableClient.setSecretKey(secretKey);
        executableClient.setDomainName(S_DOMAIN);
        executableClient.init();
    }

}
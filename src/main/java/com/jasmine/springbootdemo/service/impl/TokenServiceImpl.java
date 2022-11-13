package com.jasmine.springbootdemo.service.impl;

import com.jasmine.springbootdemo.service.TokenService;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    private static final String WECHAT_API_URL_TEMPLATE = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    @Override
    public String getToken() {
        return String.format(WECHAT_API_URL_TEMPLATE, "1", "2");
    }

}

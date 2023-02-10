package com.jasmine.springboot.service;

import com.jasmine.springboot.model.user.UserInfo;

/**
 * @author xieshanghan
 * @version UserInfoService.java, v 0.1 2023年02月10日 15:37 xieshanghan
 */
public interface UserInfoService {

    UserInfo queryByNickNameCn(String nickNameCn);

}

package com.jasmine.springboot.service;

import java.util.Map;

/**
 * 人才引进信息获取服务
 *
 * @author xieshanghan
 * @date TalentInfoService.java, v 0.1, 2023年03月02日 22:48 xieshanghan
 */
public interface TalentInfoService {

    Map<String, Object> getInfoByZhihu(String zhiHuUrl);

    Map<String, Object> getInfoByWxMp(String wxMpUrl);

}
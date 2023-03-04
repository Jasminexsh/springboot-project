package com.jasmine.springboot.service;

import com.jasmine.springboot.model.talent.TalentInfo;

import java.util.List;

/**
 * 人才引进信息获取服务
 *
 * @author xieshanghan
 * @date TalentInfoService.java, v 0.1, 2023年03月02日 22:48 xieshanghan
 */
public interface TalentInfoService {

    List<TalentInfo> wxMpProvinceTalentInfo(String province);

    List<TalentInfo> wxMpCityTalentInfo(String city);

}
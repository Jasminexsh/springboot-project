package com.jasmine.springboot.service;

import java.util.Map;

/**
 * 知乎爬虫服务
 *
 * @author xieshanghan
 * @version ZhihuCrawlerService.java, v 0.1 2023年02月06日 14:24 xieshanghan
 */
public interface ZhihuCrawlerService {

    /**
     * 获取知乎专栏：浙江省高层次人才引进 对应城市公告信息
     *
     * @param city 浙江省内城市
     * @return
     */
    Map<String, String> getZJTalentInfo(String city);

    /**
     * 获取知乎专栏：浙江省高层次人才引进 对应城市 Top10公告信息
     *
     * @param city
     * @return
     */
    Map<String, String> getZJTop10TalentInfo(String city);

}
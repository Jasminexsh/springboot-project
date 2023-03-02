package com.jasmine.springboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jasmine.springboot.service.TalentInfoService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author xieshanghan
 * @date TalentInfoServiceImpl.java, v 0.1, 2023年03月02日 22:50 xieshanghan
 */
@Service
public class TalentInfoServiceImpl implements TalentInfoService {

    private static final String ZJ_WX_MP_URL = "https://mp.weixin.qq.com/mp/appmsgalbum?action=getalbum&__biz=Mzg3NjYyMjM5OA==&album_id=1964765046371041282&count=10&uin=&key=&pass_ticket=&wxtoken=&devicetype=&clientversion=&__biz=Mzg3NjYyMjM5OA==&appmsg_token=&x5=0&f=json";

    @Override
    public Map<String, Object> getInfoByZhihu(String zhiHuUrl) {
        return null;
    }

    @Override
    public Map<String, Object> getInfoByWxMp(String wxMpUrl) {
        String top10InfoUrl = ZJ_WX_MP_URL;
        JSONObject top10InfoJson = doHttpGetRequest(top10InfoUrl);
        JSONArray jsonArray = (JSONArray) ((JSONObject) top10InfoJson.get("getalbum_resp")).get("article_list");
        return null;
    }

    private JSONObject doHttpGetRequest(String url) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpEntity.getContent(), StandardCharsets.UTF_8));
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = bufferedReader.readLine()) != null) {
                buffer.append(line);
            }
            bufferedReader.close();
            String infoStr = buffer.toString();
            return JSON.parseObject(infoStr);
        } catch (Exception e) {
            throw new RuntimeException("do http get request exception.");
        }
    }

}
package com.jasmine.springboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jasmine.springboot.enums.TalentInfoOriginTypeEnum;
import com.jasmine.springboot.enums.WxMpTalentInfoTypeEnum;
import com.jasmine.springboot.model.talent.TalentInfo;
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
import java.util.*;

/**
 * @author xieshanghan
 * @date TalentInfoServiceImpl.java, v 0.1, 2023年03月02日 22:50 xieshanghan
 */
@Service
public class TalentInfoServiceImpl implements TalentInfoService {

    private static final int WX_MP_PAGE_SIZE = 20;

    private static Map<String, String> wxMpCity2AlbumId = new HashMap<>();

    private static Map<String, String> wxMpProvince2AlbumId = new HashMap<>();

    private static final String WX_MP_INITIAL_TEMPLATE = "https://mp.weixin.qq.com/mp/appmsgalbum?action=getalbum&__biz=Mzg3NjYyMjM5OA==&album_id=%s&count=20&uin=&key=&pass_ticket=&wxtoken=&devicetype=&clientversion=&__biz=Mzg3NjYyMjM5OA==&appmsg_token=&x5=0&f=json";

    private static final String WX_MP_ITERATION_TEMPLATE = "https://mp.weixin.qq.com/mp/appmsgalbum?action=getalbum&__biz=Mzg3NjYyMjM5OA==&album_id=%s&count=20&begin_msgid=%s&begin_itemidx=%s&uin=&key=&pass_ticket=&wxtoken=&devicetype=&clientversion=&__biz=Mzg3NjYyMjM5OA==&appmsg_token=&x5=0&f=json";

    static {
        wxMpProvince2AlbumId.put("浙江", "1958283018024828934");
        wxMpProvince2AlbumId.put("江苏", "");

        wxMpCity2AlbumId.put("杭州", "1964765046371041282");
        wxMpCity2AlbumId.put("宁波", "1959717058447720453");
    }

    @Override
    public List<TalentInfo> wxMpProvinceTalentInfo(String province) {
        if (!wxMpProvince2AlbumId.containsKey(province)) {
            //todo 日志打印
            return new ArrayList<>();
        }
        return getWxMpTalentInfoList(province, WxMpTalentInfoTypeEnum.PROVINCE.getCode());
    }

    @Override
    public List<TalentInfo> wxMpCityTalentInfo(String city) {
        if (!wxMpCity2AlbumId.containsKey(city)) {
            //todo 日志打印
            return new ArrayList<>();
        }
        return getWxMpTalentInfoList(city, WxMpTalentInfoTypeEnum.CITY.getCode());
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

    private List<TalentInfo> getWxMpArticleList(JSONObject talentInfoJson) {
        List<TalentInfo> talentInfoList = new ArrayList<>();
        JSONArray articleListJsonArray = (JSONArray) ((JSONObject) talentInfoJson.get("getalbum_resp")).get("article_list");
        for (Object obj : articleListJsonArray) {
            String publishTime = (String) ((JSONObject) obj).get("create_time");
            String articleTitle = (String)  ((JSONObject) obj).get("title");
            String url = (String)  ((JSONObject) obj).get("url");
            String originType = TalentInfoOriginTypeEnum.WX_MP.getCode();
            TalentInfo talentInfo = new TalentInfo();
            talentInfo.setPublishTime(publishTime);
            talentInfo.setArticleTitle(articleTitle);
            talentInfo.setUrl(url);
            talentInfo.setOriginType(originType);
            talentInfoList.add(talentInfo);
        }
        return talentInfoList;
    }

    private List<TalentInfo> getWxMpTalentInfoList(String region, String type) {
        List<TalentInfo> talentInfoListTotal = new ArrayList<>();
        String albumId;
        if (WxMpTalentInfoTypeEnum.CITY.getCode().equals(type)) {
            albumId = wxMpCity2AlbumId.get(region);
        } else {
            albumId = wxMpProvince2AlbumId.get(region);
        }
        String top20Url = String.format(WX_MP_INITIAL_TEMPLATE, albumId);

        JSONObject top20Json = doHttpGetRequest(top20Url);
        List<TalentInfo> talentInfoListTop20 = getWxMpArticleList(top20Json);
        talentInfoListTotal.addAll(talentInfoListTop20);
        int articleNum = Integer.valueOf((String) ((JSONObject)((JSONObject) top20Json.get("getalbum_resp")).get("base_info")).get("article_count"));
        int iterationNumber = (articleNum - WX_MP_PAGE_SIZE) / WX_MP_PAGE_SIZE;
        int iterationReminder = (articleNum - WX_MP_PAGE_SIZE) % WX_MP_PAGE_SIZE;
        if (iterationReminder != 0) {
            iterationNumber ++;
        }

        String[] key = ((String) ((JSONObject) ((JSONArray) ((JSONObject) top20Json.get("getalbum_resp")).get("article_list")).get(WX_MP_PAGE_SIZE - 1)).get("key")).split("_");
        String iterationMsgId = key[1];
        String iterationIndex = key[2];
        for (int i = 0; i < iterationNumber; i++) {
            String iterationUrl = String.format(WX_MP_ITERATION_TEMPLATE, albumId, iterationMsgId, iterationIndex);

            //debug
            System.out.println(iterationUrl);

            JSONObject iterationJson20 = doHttpGetRequest(iterationUrl);
            List<TalentInfo> talentInfoList = getWxMpArticleList(iterationJson20);
            talentInfoListTotal.addAll(talentInfoList);
            if (i != iterationNumber - 1) {
                key = ((String) ((JSONObject) ((JSONArray) ((JSONObject) iterationJson20.get("getalbum_resp")).get("article_list")).get(WX_MP_PAGE_SIZE - 1)).get("key")).split("_");
                iterationMsgId = key[1];
                iterationIndex = key[2];
            }
        }
        return talentInfoListTotal;
    }

}
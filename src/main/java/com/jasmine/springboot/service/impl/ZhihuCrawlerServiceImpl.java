package com.jasmine.springboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jasmine.springboot.service.ZhihuCrawlerService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xieshanghan
 * @version ZhihuCrawlerServiceImpl.java, v 0.1 2023年02月06日 14:27 xieshanghan
 */
@Service
public class ZhihuCrawlerServiceImpl implements ZhihuCrawlerService {

    private static final String ZJ_INFO_URL = "https://www.zhihu.com/api/v4/columns/c_1394774882090135552/items?limit=100&offset=0";

    private static final String ZJ_INFO_URL_TEMPLATE = "https://www.zhihu.com/api/v4/columns/c_1394774882090135552/items?limit=%d&offset=%d";

    private static Map<String, Map<String, String>> city2Info;

    private static int DEFAULT_PAGE_SIZE = 100;

    @Override
    public Map<String, String> getZJTalentInfo(String city) {
        try {
            String firstUrl = ZJ_INFO_URL;
            Map<String, String> infoResultMap = new HashMap<>();
            JSONObject firstInfoJson = doHttpGetRequest(firstUrl);
            JSONArray data = (JSONArray) firstInfoJson.get("data");
            for (Object object: data) {
                String title = (String) ((JSONObject) object).get("title");
                String url = (String) ((JSONObject) object).get("url");
                if (title.contains(city)) {
                    infoResultMap.put(title, url);
                }
            }
            int totals = (int) ((Map<String, Object>) firstInfoJson.get("paging")).get("totals");
            int iterRound = (totals - DEFAULT_PAGE_SIZE) / DEFAULT_PAGE_SIZE;
            int iterRoundRemain = (totals - DEFAULT_PAGE_SIZE) % DEFAULT_PAGE_SIZE;
            if (iterRoundRemain != 0) {
                iterRound ++;
            }
            for (int i=0; i<iterRound; i++){
                if (i != iterRound - 1) {
                    String infoUrl = String.format(ZJ_INFO_URL_TEMPLATE, DEFAULT_PAGE_SIZE, (i + 1) * DEFAULT_PAGE_SIZE);
                    JSONObject infoJson = doHttpGetRequest(infoUrl);
                    JSONArray infoData = (JSONArray) infoJson.get("data");
                    for (Object object: infoData) {
                        String infoDataTitle = (String) ((JSONObject) object).get("title");
                        String infoDataUrl = (String) ((JSONObject) object).get("url");
                        if (infoDataTitle.contains(city)) {
                            infoResultMap.put(infoDataTitle, infoDataUrl);
                        }
                    }
                } else {
                    String infoUrl = String.format(ZJ_INFO_URL_TEMPLATE, iterRoundRemain, (i + 1) * DEFAULT_PAGE_SIZE);
                    JSONObject infoJson = doHttpGetRequest(infoUrl);
                    JSONArray infoData = (JSONArray) infoJson.get("data");
                    for (Object object: infoData) {
                        String infoDataTitle = (String) ((JSONObject) object).get("title");
                        String infoDataUrl = (String) ((JSONObject) object).get("url");
                        if (infoDataTitle.contains(city)) {
                            infoResultMap.put(infoDataTitle, infoDataUrl);
                        }
                    }
                }
            }
            return infoResultMap;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Map<String, String> getZJTop10TalentInfo(String city) {
        try {
            String firstUrl = ZJ_INFO_URL;
            Map<String, String> infoResultMap = new HashMap<>();
            JSONObject firstInfoJson = doHttpGetRequest(firstUrl);
            JSONArray data = (JSONArray) firstInfoJson.get("data");
            for (Object object : data) {
                String title = (String) ((JSONObject) object).get("title");
                String url = (String) ((JSONObject) object).get("url");
                if (title.contains(city)) {
                    if (infoResultMap.size() < 10) {
                        infoResultMap.put(title, url);
                    } else {
                        break;
                    }
                }
            }
            int totals = (int) ((Map<String, Object>) firstInfoJson.get("paging")).get("totals");
            int iterRound = (totals - DEFAULT_PAGE_SIZE) / DEFAULT_PAGE_SIZE;
            int iterRoundRemain = (totals - DEFAULT_PAGE_SIZE) % DEFAULT_PAGE_SIZE;
            if (iterRoundRemain != 0) {
                iterRound++;
            }
            for (int i = 0; i < iterRound; i++) {
                if (i != iterRound - 1) {
                    String infoUrl = String.format(ZJ_INFO_URL_TEMPLATE, DEFAULT_PAGE_SIZE, (i + 1) * DEFAULT_PAGE_SIZE);
                    JSONObject infoJson = doHttpGetRequest(infoUrl);
                    JSONArray infoData = (JSONArray) infoJson.get("data");
                    for (Object object : infoData) {
                        String infoDataTitle = (String) ((JSONObject) object).get("title");
                        String infoDataUrl = (String) ((JSONObject) object).get("url");
                        if (infoDataTitle.contains(city)) {
                            if (infoResultMap.size() < 10) {
                                infoResultMap.put(infoDataTitle, infoDataUrl);
                            } else {
                                break;
                            }
                        }
                    }
                } else {
                    String infoUrl = String.format(ZJ_INFO_URL_TEMPLATE, iterRoundRemain, (i + 1) * DEFAULT_PAGE_SIZE);
                    JSONObject infoJson = doHttpGetRequest(infoUrl);
                    JSONArray infoData = (JSONArray) infoJson.get("data");
                    for (Object object : infoData) {
                        String infoDataTitle = (String) ((JSONObject) object).get("title");
                        String infoDataUrl = (String) ((JSONObject) object).get("url");
                        if (infoDataTitle.contains(city)) {
                            if (infoResultMap.size() < 10) {
                                infoResultMap.put(infoDataTitle, infoDataUrl);
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
            return infoResultMap;
        } catch(Exception e){
            throw new RuntimeException();
        }
    }

    /**
     * 执行HTTP Get请求，返回JSON对象
     *
     * @param url
     */
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
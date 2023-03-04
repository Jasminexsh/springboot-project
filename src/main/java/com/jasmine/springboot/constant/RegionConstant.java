package com.jasmine.springboot.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xieshanghan
 * @version RegionConstant.java, v 0.1 2023年03月04日 12:24 xieshanghan
 */
public class RegionConstant {

    public static Map<String, List<String>> province2City = new HashMap<>();

    public static Map<String, String> city2Province = new HashMap<>();

    static {
        List<String> zheJiangCitys = new ArrayList<>();
        zheJiangCitys.add("杭州");
        zheJiangCitys.add("宁波");
        zheJiangCitys.add("温州");

        province2City.put("浙江", zheJiangCitys);

        city2Province.put("杭州", "浙江");
        city2Province.put("宁波", "浙江");
        city2Province.put("温州", "浙江");
    }

}
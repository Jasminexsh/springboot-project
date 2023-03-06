package com.jasmine.springboot.web.controller.jdbc;

import com.jasmine.springboot.dal.daointerface.RegionInfoMapper;
import com.jasmine.springboot.dal.dataobject.RegionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author xieshanghan
 * @version JdbcController.java, v 0.1 2023年03月04日 15:59 xieshanghan
 */
@Controller
@RequestMapping("/jdbc")
public class JdbcController {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RegionInfoMapper regionInfoMapper;


    @RequestMapping("/regionInfo")
    @ResponseBody
    public List<Map<String, Object>> getRegionInfoByName(String regionName) {
        String querySql = String.format("SELECT * FROM region_info WHERE region_name = %s", regionName);
        List<Map<String, Object>> regionInfoMap = jdbcTemplate.queryForList(querySql);
        return regionInfoMap;
    }

    @RequestMapping("/region")
    @ResponseBody
    public RegionInfo getRegionInfoByInfoName(String name) {
        RegionInfo regionInfo = regionInfoMapper.selectByPrimaryKey(1L);
        return regionInfo;
    }

}
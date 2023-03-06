package com.jasmine.springboot;

import com.jasmine.springboot.model.talent.TalentInfo;
import com.jasmine.springboot.service.TalentInfoService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * Springboot应用测试类
 *
 * @author xieshanghan
 * @version SpringbootApplicationTests.java, v 0.1, 2023年01月17日 12:01 xieshanghan
 */
@SpringBootTest
class SpringbootApplicationTests {

	@Autowired
	private TalentInfoService talentInfoService;

	@Value("${enable_email}")
	private boolean enableEmail;


	@Test
	public void testTalentInfoService() {
		List<TalentInfo> wxMpHangZhouTalentInfoList = talentInfoService.wxMpCityTalentInfo("杭州");
		List<TalentInfo> wxMpZheJiangTalentInfoList = talentInfoService.wxMpProvinceTalentInfo("浙江");
		List<TalentInfo> wxMpJiangSuTalentInfoList = talentInfoService.wxMpProvinceTalentInfo("江苏");
		Assert.assertEquals(wxMpHangZhouTalentInfoList.size(), 256);
		Assert.assertEquals(wxMpZheJiangTalentInfoList.size(), 800);
		Assert.assertEquals(wxMpJiangSuTalentInfoList.size(), 999);
	}

}
package com.jasmine.springboot;

import com.jasmine.springboot.service.TalentInfoService;
import com.jasmine.springboot.service.ZhihuCrawlerService;
import jep.Interpreter;
import jep.SharedInterpreter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

/**
 * Springboot应用测试类
 *
 * @author xieshanghan
 * @version SpringbootApplicationTests.java, v 0.1, 2023年01月17日 12:01 xieshanghan
 */
@SpringBootTest
class SpringbootApplicationTests {

	@Autowired
	private ZhihuCrawlerService zhihuCrawlerService;

	@Autowired
	private TalentInfoService talentInfoService;

	@Value("${enable_email}")
	private boolean enableEmail;

	/**
	 * Win10测试Java调用Python
	 */
//	@Test
//	public void testWin10ExecutePython() {
//		Interpreter interp = new SharedInterpreter();
//		interp.exec("import pandas as pd");
//		// any of the following work, these are just pseudo-examples
//		// using exec(String) to invoke methods
//		interp.exec("x = []");
//		interp.exec("x.append(1)");
//		interp.exec("x.append(2)");
//		Object result1 = interp.getValue("x");
//		System.out.println(result1);
//	}

	@Test
	public void testGetZJProvinceTalentInfo() {
		String city = "杭州";
		Map<String, String> result = zhihuCrawlerService.getZJTalentInfo(city);
		for (Map.Entry<String, String> entry : result.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}

	@Test
	public void testGetZJProvinceTop10TalentInfo() {
		String city = "【浙江|杭州】";
		Map<String, String> result = zhihuCrawlerService.getZJTop10TalentInfo(city);
		for (Map.Entry<String, String> entry : result.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}

	@Test
	public void test() {
		talentInfoService.getInfoByWxMp("");
	}

}
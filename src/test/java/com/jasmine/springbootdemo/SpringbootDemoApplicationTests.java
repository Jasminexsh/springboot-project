package com.jasmine.springbootdemo;

import jep.Interpreter;
import jep.SharedInterpreter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootDemoApplicationTests {

	/**
	 * 测试Java调用Python
	 */
	@Test
	void testExecutePython() {
		Interpreter interp = new SharedInterpreter();
		interp.exec("import pandas as pd");
		// any of the following work, these are just pseudo-examples
		// using exec(String) to invoke methods
		interp.exec("x = []");
		interp.exec("x.append(1)");
		interp.exec("x.append(2)");
		Object result1 = interp.getValue("x");
		System.out.println(result1);
	}

}

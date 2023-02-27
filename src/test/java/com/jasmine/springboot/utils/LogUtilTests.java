package com.jasmine.springboot.utils;

import com.alipay.analyze.core.engine.perfcap.log.PerfCapDiagnoseLog;
import com.jasmine.springboot.util.LogUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xieshanghan
 * @version LogUtilTests.java, v 0.1 2023年02月20日 13:32 xieshanghan
 */
@SpringBootTest
public class LogUtilTests {

    @Test
    public void testLogger() {
        String ctuDiagnoseStrategy = "CTU-DIAGNOSE-STRATEGY";
        System.out.println(ctuDiagnoseStrategy.toLowerCase());
        Logger logger = LoggerFactory.getLogger("CTU-DIAGNOSE-STRATEGY");
        LogUtil.info(logger, "Info Message");
    }

    @Test
    public void testCtu() {
        PerfCapDiagnoseLog.info("ctu-strategy", "strategy");
    }

}

package com.jasmine.springboot.job;

import com.jasmine.springboot.model.mail.EmailModel;
import com.jasmine.springboot.model.mail.EmailSessionInfo;
import com.jasmine.springboot.service.EmailService;
import com.jasmine.springboot.service.ZhihuCrawlerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * 网易邮箱定时邮件Job
 *
 * @author xieshanghan
 * @version NetEaseEmailScheduledJob.java, v 0.1, 2023年01月04日 0:10 xieshanghan
 */
@Component
public class NetEaseEmailScheduledJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(NetEaseEmailScheduledJob.class);

    @Value("${enable_email}")
    private boolean enableEmail;

    private static final String SEND = "send";

    private static final String RECEIVE = "receive";

    private static final String TALENT_TEMPLATE = "浙江省高层次人才引进专栏信息爬取";

    @Autowired
    private EmailService emailService;

    @Autowired
    private ZhihuCrawlerService zhihuCrawlerService;

    /**
     * 邮件定时发送
     */
    @Scheduled(cron = "0 0/2 * * * ?")
    public void scheduledSendEmail() {
        if (enableEmail) {
            try {
                System.out.println("----开始发送邮件----");
                EmailSessionInfo sender = new EmailSessionInfo(SEND, "shanghanxie7@163.com", "OLEFWKKADDVZXRPU", "谢尚翰");
                EmailSessionInfo receiver = new EmailSessionInfo(RECEIVE, "1148177830@qq.com", "JAsmine");
                EmailModel emailModel = new EmailModel();
                emailModel.setSubject(TALENT_TEMPLATE);
                Map<String, String> top10Info = zhihuCrawlerService.getZJTop10TalentInfo("【浙江|杭州】");
                String content = "";
                for (Map.Entry<String, String> entry : top10Info.entrySet()) {
                    content = content + entry.getKey() + ": " + entry.getValue() + "\n";
                }
                System.out.println(content);
                emailModel.setContent(content);
                emailModel.setSendTime(new Date());
                emailService.sendEmail(sender, receiver, emailModel);
                System.out.println("----邮件发送完毕----");
            } catch (Exception ex) {
                LOGGER.error("发送邮件失败：{}", ex.getMessage());
            }
        }
    }

}
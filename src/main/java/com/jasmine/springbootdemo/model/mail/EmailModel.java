package com.jasmine.springbootdemo.model.mail;

import java.util.Date;

/**
 * 邮件信息
 *
 * @author xieshanghan
 * @version v0.1, 2023/1/4
 */
public class EmailModel {

    /** 邮件主题 */
    private String subject;

    /** 邮件内容 */
    private Object content;

    /** 发送时间 */
    private Date sendTime;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "EmailModel{" +
                "subject='" + subject + '\'' +
                ", content=" + content +
                ", sendTime=" + sendTime +
                '}';
    }

}

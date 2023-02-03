package com.jasmine.springboot.model.mail;

/**
 * 邮件会话信息
 *
 * @author xieshanghan
 * @version EmailSessionInfo.java, v 0.1, 2023年01月04日 11:17 xieshanghan
 */
public class EmailSessionInfo {

    /** 邮件类型：发送方、接收方 */
    private String emailType;

    /** 邮箱账号 */
    private String emailAccount;

    /** 授权码，用于发送方登陆 */
    private String accessCode;

    /** 邮箱用户名 */
    private String userName;

    public String getEmailType() {
        return emailType;
    }

    public EmailSessionInfo() {
    }

    public EmailSessionInfo(String emailType, String emailAccount, String userName) {
        this.emailType = emailType;
        this.emailAccount = emailAccount;
        this.userName = userName;
    }

    public EmailSessionInfo(String emailType, String emailAccount, String accessCode, String userName) {
        this.emailType = emailType;
        this.emailAccount = emailAccount;
        this.accessCode = accessCode;
        this.userName = userName;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    public String getEmailAccount() {
        return emailAccount;
    }

    public void setEmailAccount(String emailAccount) {
        this.emailAccount = emailAccount;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "EmailSessionInfo{" +
                "emailType='" + emailType + '\'' +
                ", emailAccount='" + emailAccount + '\'' +
                ", accessCode='" + accessCode + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

}
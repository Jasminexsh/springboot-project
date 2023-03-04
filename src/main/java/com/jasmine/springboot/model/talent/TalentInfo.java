package com.jasmine.springboot.model.talent;

/**
 * @author xieshanghan
 * @date TalentInfo.java, v 0.1, 2023年03月02日 23:11 xieshanghan
 */
public class TalentInfo {

    /**
     * 发表时间
     */
    private String publishTime;

    /**
     * 来源类型：知乎/微信公众号
     */
    private String originType;

    /**
     * 文章标题
     */

    private String articleTitle;

    /**
     * 详情链接
     */
    private String url;

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getOriginType() {
        return originType;
    }

    public void setOriginType(String originType) {
        this.originType = originType;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
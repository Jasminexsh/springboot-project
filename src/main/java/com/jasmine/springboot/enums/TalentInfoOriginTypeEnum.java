package com.jasmine.springboot.enums;

/**
 * @author xieshanghan
 * @version TalentInfoOriginTypeEnum.java, v 0.1 2023年03月03日 22:44 xieshanghan
 */
public enum TalentInfoOriginTypeEnum {

    WX_MP("WX_MP", "微信公众号"),

    ZHIHU("ZHIHU", "知乎");

    private final String code;

    private final String desc;

    private TalentInfoOriginTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public TalentInfoOriginTypeEnum getEnumByCode(String code) {
        for (TalentInfoOriginTypeEnum talentInfoOriginTypeEnum : TalentInfoOriginTypeEnum.values()) {
            if (talentInfoOriginTypeEnum.getCode().equals(code)) {
                return talentInfoOriginTypeEnum;
            }
        }
        return null;
    }

}
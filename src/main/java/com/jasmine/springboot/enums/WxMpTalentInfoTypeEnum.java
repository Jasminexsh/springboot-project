package com.jasmine.springboot.enums;

/**
 * @author xieshanghan
 * @version WxMpTalentInfoTypeEnum.java, v 0.1 2023年03月04日 11:42 xieshanghan
 */
public enum WxMpTalentInfoTypeEnum {

    CITY("city", "城市"),

    PROVINCE("province", "省份");

    private final String code;

    private final String desc;

    private WxMpTalentInfoTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public WxMpTalentInfoTypeEnum getEnumByCode(String code) {
        for (WxMpTalentInfoTypeEnum wxMpTalentInfoTypeEnum : WxMpTalentInfoTypeEnum.values()) {
            if (wxMpTalentInfoTypeEnum.getCode().equals(code)) {
                return wxMpTalentInfoTypeEnum;
            }
        }
        return null;
    }

}
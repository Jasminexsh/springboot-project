package com.jasmine.springboot.common.model.errors;

/**
 * 默认错误码
 *
 * @author xieshanghan
 * @version DefaultErrorCode.java, v 0.1 2023年02月16日 13:51 xieshanghan
 */
public class DefaultErrorCode implements ErrorCode {

    /** 错误码 */
    private final String code;

    /** 错误详细信息 */
    private final String message;

    /**
     * 构造方法
     *
     * @param code
     * @param message
     */
    public DefaultErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 静态构造方法
     *
     * @param code
     * @param message
     * @return
     */
    public static DefaultErrorCode build(String code, String message) {
        return new DefaultErrorCode(code, message);
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
package com.jasmine.springboot.common.errors;

/**
 * 错误码接口
 *
 * @author xieshanghan
 * @version ErrorCode.java, v 0.1 2023年02月16日 13:50 xieshanghan
 */
public interface ErrorCode {

    /** 获取错误码 */
    String getCode();

    /** 获取错误码详细信息 */
    String getMessage();

}

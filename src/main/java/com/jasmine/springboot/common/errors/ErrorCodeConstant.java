package com.jasmine.springboot.common.errors;

/**
 * 错误码常量
 *
 * @author xieshanghan
 * @version ErrorCodeConstant.java, v 0.1 2023年02月16日 13:54 xieshanghan
 */
public class ErrorCodeConstant {

    public static final ErrorCode E0000 = DefaultErrorCode.build("0000", "success");

    public static final ErrorCode E0001 = DefaultErrorCode.build("0001", "fail");

    public static final ErrorCode E0002 = DefaultErrorCode.build("0002", "retry");

    public static final ErrorCode E0003 = DefaultErrorCode.build("0003", "client error");

    public static final ErrorCode E0004 = DefaultErrorCode.build("0004", "not login");

    public static final ErrorCode E0005 = DefaultErrorCode.build("0005", "no permission");

    public static final ErrorCode E0006 = DefaultErrorCode.build("0006", "illegal argument");

    public static final ErrorCode E0007 = DefaultErrorCode.build("0007", "server error");

    public static final ErrorCode E0008 = DefaultErrorCode.build("0008", "remote service error");

}
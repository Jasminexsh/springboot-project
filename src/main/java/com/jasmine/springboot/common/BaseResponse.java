package com.jasmine.springboot.common;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author xieshanghan
 * @version BaseResponse.java, v 0.1 2023年02月16日 13:36 xieshanghan
 */
public class BaseResponse<T> implements Serializable {

    /** 是否成功 */
    private boolean success;

    /** 结果码 */
    private String code;

    /** 结果描述 */
    private String message;

    /** 错误展示类型：0 silent; 1 message.warn; 2 message.error; 4 notification; 9 page */
    private int showType;

    /** 返回数据 */
    private T data;

    public BaseResponse(Builder<T> builder) {
        this.success = builder.success;
        this.code = builder.code;
        this.message = builder.message;
        this.showType = builder.showType;
        this.data = builder.data;
    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    /**
     * 返回成功的Response，附带data信息
     *
     * @param data payload data
     * @return
     * @param <T>
     */
    public static <T> BaseResponse<T> success(T data) {
        return BaseResponse.<T>builder().success(true).data(data).build();
    }

    /**
     * 返回成功的Response，不附带data信息
     *
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> success() {
        return BaseResponse.<T>builder().success(true).build();
    }

    /**
     * 返回失败的response，附带错误描述信息
     *
     * @param message 错误描述信息
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> fail(String message) {
        return BaseResponse.<T>builder().success(false).message(message).build();
    }

    public static class Builder<T> {

        private Boolean success;

        private String code;

        private String message;

        private int showType;

        private T data;

        public Builder<T> success(Boolean success) {
            this.success = success;
            return this;
        }

        public Builder<T> code(String code) {
            this.code = code;
            return this;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> showType(int showType) {
            this.showType = showType;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public BaseResponse<T> build() {
            Objects.requireNonNull(success, "response status is required");
            return new BaseResponse<>(this);
        }

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getShowType() {
        return showType;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
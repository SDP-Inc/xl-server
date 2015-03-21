package org.xl.service;

import org.xl.utils.ExceptionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by meulmees on 3/21/2015.
 */
public interface Service {

    /**
     * The Service.Void is a Result Response which has no data attribute.
     * This is usefule for things like DELETE requests where we don't have any
     * data to return.
     *
     * Other Results extend this so that all Results have a consistent schema
     */
    public static class Void {
        protected boolean success = false;
        protected String message = "";
        protected int errorCode = 0;
        protected String errorMessage;
        protected String errorDetails;
        protected Map<String, Object> attributes = new HashMap<>();

        public Void() {
            setSuccess(true);
        }

        public Void(boolean success, String message) {
            setSuccess(success);
            setMessage(message);
        }

        public Void(String message, Throwable e) {
            setSuccess(false);
            setMessage(message);
            setErrorMessage(e.getMessage());
            setErrorDetails(ExceptionUtils.getStackTrace(e));
        }

        public Void(Throwable e) {
            this("Application Error", e);
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getErrorDetails() {
            return errorDetails;
        }

        public void setErrorDetails(String errorDetails) {
            this.errorDetails = errorDetails;
        }

        public Map<String, Object> getAttributes() {
            return attributes;
        }

        public void setAttributes(Map<String, Object> attributes) {
            this.attributes = attributes;
        }

        public void addAttribute(String key, Object value) {
            attributes.put(key, value);
        }
    }

    public static class Result<T> extends Void {

        protected T data;

        public Result() {
            super();
        }

        public Result(T data) {
            super();
            setData(data);
        }

        public Result(T data, boolean success, String message) {
            super(success, message);
            setData(data);
        }

        public Result(String message, Throwable e) {
            super(message, e);
        }

        public Result(Throwable e) {
            super(e);
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }
}

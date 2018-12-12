package com.bababadboy.dealermng.exception;

/**
 * @author Ash
 * @date 2018/11/29 11:01
 */
public class RestServiceError {
    private String code;
    private String message;

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

    public static RestServiceError build (Type errorType, String message) {
        RestServiceError error = new RestServiceError();
        error.code = errorType.getCode();
        error.message = errorType.getMessage();
        return error;
    }


    public enum Type {
        /**
         * 404
         */
        BAD_REQUEST("ERROR.BAD_REQUEST", "BAD REQUEST ERROR"),
        /**
         * 500
         */
        INTERNAL_SERVER_ERROR("ERROR.INTERNAL_SERVER", "UNEXPECTED SERVER ERROR"),
        /**
         * 401
         */
        VALIDATION_ERROR("ERROR.VALIDATION", "FOUND VALIDATION ISSUES");

        private String code;
        private String message;

        Type(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
}

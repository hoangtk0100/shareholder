package com.project.shareholder.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * RestApiJsonContainer
 */
public class RestApiJsonContainer implements Serializable {

    public RestApiJsonContainer() {
        errorCode = 0;
        message = "success";
    }

    @JsonProperty("data")
    private Object data;

    @JsonProperty("errorCode")
    private int errorCode;

    @JsonProperty("message")
    private String message;

    @JsonProperty("version")
    private String version;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

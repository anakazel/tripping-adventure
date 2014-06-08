package com.composition.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alexg
 */
public final class Operation {

    private String baseUrl;
    private String location;
    private String httpMethod;
    /**
     * Used in GET & DELETE requests
     */
    private List<String> params = new ArrayList<>();
    private String requestContentType;
    private String responseContentType;
    private String url;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getRequestContentType() {
        return requestContentType;
    }

    public void setRequestContentType(String requestContentType) {
        this.requestContentType = requestContentType;
    }

    public String getResponseContentType() {
        return responseContentType;
    }

    public void setResponseContentType(String responseContentType) {
        this.responseContentType = responseContentType;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }

    public String getUrl(){
        return getBaseUrl() + getLocation();
    }
}

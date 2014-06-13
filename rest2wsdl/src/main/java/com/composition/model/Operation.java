package com.composition.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alexg
 */
public final class Operation {

    private String baseUrl;
    private String serviceName;
    private String location;
    private String httpMethod;
    private String name;

    /**
     * Used in GET & DELETE requests
     */
    private List<String> params = new ArrayList<String>();
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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "baseUrl='" + baseUrl + '\'' +
                ", location='" + location + '\'' +
                ", httpMethod='" + httpMethod + '\'' +
                ", params=" + params +
                ", requestContentType='" + requestContentType + '\'' +
                ", responseContentType='" + responseContentType + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

}
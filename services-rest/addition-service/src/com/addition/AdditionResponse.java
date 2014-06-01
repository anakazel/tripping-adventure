package com.addition;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by alexg on 10.05.2014.
 */
@XmlRootElement
public final class AdditionResponse {
    private String operationId;
    private Double result;
    private String elapsedTime;

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public String getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(String elapsedTime) {
        this.elapsedTime = elapsedTime;
    }
}

package com.addition;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by alexg on 31.05.2014.
 */
@XmlRootElement
public class AdditionRequest {

    private Double firstParam;
    private Double secondParam;

    public Double getFirstParam() {
        return firstParam;
    }

    public void setFirstParam(Double firstParam) {
        this.firstParam = firstParam;
    }

    public Double getSecondParam() {
        return secondParam;
    }

    public void setSecondParam(Double secondParam) {
        this.secondParam = secondParam;
    }

}

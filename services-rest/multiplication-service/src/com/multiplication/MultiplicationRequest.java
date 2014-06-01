package com.multiplication;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by alexg on 01.06.2014.
 */
@XmlRootElement
public class MultiplicationRequest {

    private Double firstParam;
    private Double secondParam;

    public Double getSecondParam() {
        return secondParam;
    }

    public void setSecondParam(Double secondParam) {
        this.secondParam = secondParam;
    }

    public Double getFirstParam() {
        return firstParam;
    }

    public void setFirstParam(Double firstParam) {
        this.firstParam = firstParam;
    }
}

package com.exponential;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by alexg on 01.06.2014.
 */
@XmlRootElement
public class LogRequest {

    private Double param;

    public Double getParam() {
        return param;
    }

    public void setParam(Double param) {
        this.param = param;
    }

}

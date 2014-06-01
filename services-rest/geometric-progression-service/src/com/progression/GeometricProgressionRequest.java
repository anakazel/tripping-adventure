package com.progression;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by alexg on 01.06.2014.
 */
@XmlRootElement
public class GeometricProgressionRequest {

    private Integer n;
    private Integer r;
    private Integer a;

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public Integer getR() {
        return r;
    }

    public void setR(Integer r) {
        this.r = r;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

}

package com.csv;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by alexg on 01.06.2014.
 */
@XmlRootElement
public class CSVFileRequest {

    private String path;
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}

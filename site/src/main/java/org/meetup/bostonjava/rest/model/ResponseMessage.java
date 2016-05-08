package org.meetup.bostonjava.rest.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by macharya on 1/15/2016.
 */
public class ResponseMessage implements Serializable {

    private int code;
    private String description;
    private Map<String,Object> data;

    public ResponseMessage(){

    }
    public ResponseMessage(int code, String desc){
        this.code = code;
        this.description = desc;

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Object> getData() {
        if(data==null){
            data = new HashMap();

        }
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}

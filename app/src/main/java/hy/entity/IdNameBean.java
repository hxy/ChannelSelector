package hy.entity;

import java.io.Serializable;

import static android.R.attr.priority;

/**
 * Created by huangyue on 2016/6/3.
 */
public class IdNameBean{
    private String name;
    private String id;

    public IdNameBean(){}

    public IdNameBean(String id,String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String catname) {
        this.name = catname;
    }

    public String getId() {
        return id;
    }

    public void setId(String catid) {
        this.id = catid;
    }

    public int getPriority() {
        return priority;
    }
}

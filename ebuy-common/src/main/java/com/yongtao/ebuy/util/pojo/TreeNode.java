package com.yongtao.ebuy.util.pojo;

import java.io.Serializable;

public class TreeNode implements Serializable
{
    private long id;
    private String text;
    private String state;

    public TreeNode() {
    }


    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getState() {
        return state;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setState(String state) {
        this.state = state;
    }
}

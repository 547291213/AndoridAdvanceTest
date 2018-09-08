package com.example.xkfeng.andoridadvancetest.Model;

/**
 * Created by initializing on 2018/9/4.
 */

public class Message {

    private String data ;

    public Message(String data)
    {
        this.data = data ;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data;
    }
}

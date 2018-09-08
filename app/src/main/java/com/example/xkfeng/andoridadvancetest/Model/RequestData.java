package com.example.xkfeng.andoridadvancetest.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by initializing on 2018/9/2.
 */

public class RequestData {

    private int code ;

    @SerializedName("data")
    private IpModel data ;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public IpModel getData() {
        return data;
    }

    public void setData(IpModel data) {
        this.data = data;
    }
}

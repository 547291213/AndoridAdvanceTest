package com.example.xkfeng.andoridadvancetest.Interface;

import com.example.xkfeng.andoridadvancetest.Model.IpModel;
import com.example.xkfeng.andoridadvancetest.Model.RequestData;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by initializing on 2018/9/2.
 */

public interface IpServiceForPost {

    @GET("getIpInfo.php")
    Observable<RequestData>getIpMsp(@Query("ip") String data) ;

}

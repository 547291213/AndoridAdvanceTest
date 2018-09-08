package com.example.xkfeng.andoridadvancetest.Interface;

import com.example.xkfeng.andoridadvancetest.Model.Gradle;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by initializing on 2018/9/4.
 */

public interface GradleServiceData {
    @GET("versions/all")
    Observable<List<Gradle>>getGradleData() ;

}

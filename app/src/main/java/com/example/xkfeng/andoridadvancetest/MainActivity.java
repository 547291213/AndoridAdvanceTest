package com.example.xkfeng.andoridadvancetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xkfeng.andoridadvancetest.Interface.GradleServiceData;
import com.example.xkfeng.andoridadvancetest.Interface.IpServiceForPost;
import com.example.xkfeng.andoridadvancetest.Interface.RxBus;
import com.example.xkfeng.andoridadvancetest.Model.Gradle;
import com.example.xkfeng.andoridadvancetest.Model.Message;
import com.example.xkfeng.andoridadvancetest.Model.RequestData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    /*
      ButterKnife的使用范围
      1 Activity
      2 Fragment
      3 Adapter

      ButterKnife的基本使用
      1 绑定View
      2 绑定资源
      3 绑定监听
      4 事件绑定
      5 设置多个View属性
      6 使用注意事项

      ButterKnife的使用和注意事项
      1 再Activity钟绑定，必须在setContentView之后使用 ButterKnife.bind(this) ，且如果父类绑定之后子类不需要绑定
      2 再非Activity钟绑定（Fragment ，Adapter中绑定，绑定形式为  ButterKnife.bind(this ,view) ;this不能替换为getActivity）
      3 再Activity中不需要做解绑的操作，再Fragment中需要完成一系列的解绑操作  onDestoryView
      4 使用Butterknife绑定控件不能被修饰为private or  static
      5 setContentView不能通过注解实现




      Rxjava2.x Back-Pressure(背压)
      被观察者发送消息太快以至于他的操作符或者订阅者不能及时处理相关的消息，从而造成消息阻塞的现象


     */


    @BindView(R.id.ll_layout)
    LinearLayout llLayout;

    @BindView(R.id.tv_text)
    TextView tvText;

    @BindView(R.id.bt_OkhttpRxJava)
    Button btOkhttpRxJava;

    @BindView(R.id.bt_RetrofitRxJava)
    Button btRetrofitRxJava;


    private static final String TAG = "MainActivity";
    @BindView(R.id.bt_GradleRequest)
    Button btGradleRequest;
    @BindView(R.id.lv_GradleList)
    ListView lvGradleList;
    @BindView(R.id.bt_toSecondAct)
    Button btToSecondAct;

    private OkHttpClient okHttpClient;

    private SimpleAdapter simpleAdapter;

    private List<Map<String, String>> mapList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        init();

    }

    private void init() {

        mapList = new ArrayList<>();

        /*
            RxBus 实例
         */
        RxBus.getInstance().tObservable(Message.class)
                .subscribe(new Consumer<Message>() {
                    @Override
                    public void accept(Message message) throws Exception {
                        tvText.setText(message.toString());
                    }
                });

    }


    @OnClick(R.id.bt_toSecondAct)
    public void setBtToSecondAct(View view)
    {
        startActivity(new Intent(MainActivity.this , SecondActivity.class));
    }

    @OnClick(R.id.tv_text)
    public void textClick(View view) {
        Toast.makeText(this, "TextClick", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.bt_OkhttpRxJava)
    public void OkhttpRequest(View view) {
        postAsyncHttp("59.108.54.37");
    }

    @OnClick(R.id.bt_RetrofitRxJava)
    public void RetrofitRequest(View view) {
        postIpInfoRetrofit("59.108.54.37");
    }

    @OnClick(R.id.bt_GradleRequest)
    public void GradleRequest(View view) {
        postGrdleData("https://services.gradle.org/");
    }

    /*
             Gradle version and buildTime       "https://services.gradle.org/versions/all"
     */
    private void postGrdleData(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GradleServiceData gradleServiceData = retrofit.create(GradleServiceData.class);

        gradleServiceData
                .getGradleData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Gradle>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Gradle> gradles) {

                        for (int i = 0; i < gradles.size(); i++) {
                            Map<String, String> map = new HashMap<>();
                            map.put("version", gradles.get(i).getVersion());
                            map.put("buildtime", gradles.get(i).getBuildTime());

                            mapList.add(map);


                        }
                        Toast.makeText(MainActivity.this, "Success" + gradles.get(0).getVersion(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                        Toast.makeText(MainActivity.this, "Eoor" + e.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                        simpleAdapter = new SimpleAdapter(MainActivity.this, mapList, R.layout.listitem, new String[]{"version", "buildtime"},
                                new int[]{R.id.tv_version, R.id.tv_buildtime});

                        lvGradleList.setAdapter(simpleAdapter);
                    }
                });


    }


    private void postIpInfoRetrofit(String ip) {

        String url = "http://ip.taobao.com/service/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        IpServiceForPost ipServiceForPost = retrofit.create(IpServiceForPost.class);

        ipServiceForPost
                .getIpMsp(ip)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RequestData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RequestData ipModel) {
                        tvText.setText(ipModel.getData().getCountry());
                        Toast.makeText(MainActivity.this, "Request success " + ipModel.getData().getIp(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    private void postAsyncHttp(String size) {
        getObservable(size).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                        Toast.makeText(MainActivity.this, "Request success  :" + s, Toast.LENGTH_SHORT).show();
                        tvText.setText(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "Request Error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    private Observable<String> getObservable(final String ip) {
        Observable observable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(final ObservableEmitter emitter) throws Exception {
                okHttpClient = new OkHttpClient();
                RequestBody requestBodye = new FormBody.Builder()
                        .add("ip", ip)
                        .build();
                Request request = new Request.Builder()
                        .url("http://ip.taobao.com/service/getIpInfo.php")
                        .post(requestBodye)
                        .build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        emitter.onError(new Exception("OkHttp Error"));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String str = response.body().string();
                        emitter.onNext(str);
                        emitter.onComplete();
                    }
                });
            }
        });

        return observable;
    }
}

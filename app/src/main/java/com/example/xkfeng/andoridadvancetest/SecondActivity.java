package com.example.xkfeng.andoridadvancetest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.xkfeng.andoridadvancetest.Interface.RxBus;
import com.example.xkfeng.andoridadvancetest.Model.Message;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by initializing on 2018/9/4.
 */

public class SecondActivity extends AppCompatActivity {


    @BindView(R.id.bt_changeText)
    Button btChangeText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_main);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.bt_changeText)
    public void ChangeTextClick(View view)
    {
        RxBus.getInstance().post(new Message("Hello World ; for Second Activity Bt click "));
    }


}

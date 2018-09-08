package com.example.xkfeng.andoridadvancetest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by initializing on 2018/9/2.
 */

public class ButterKnifeFragment extends Fragment {


    @BindView(R.id.tv_text)
    TextView tvText;
    private View view;
    private TextView textView ;

    //解绑操作
    private Unbinder unbinder ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fg_layout, container, false);
        unbinder = ButterKnife.bind(this, view);

        textView = (TextView)view.findViewById(R.id.tv_text) ;
        textView.setText("Hello textView");
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

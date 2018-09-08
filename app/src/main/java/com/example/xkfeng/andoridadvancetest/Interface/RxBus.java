package com.example.xkfeng.andoridadvancetest.Interface;

import android.animation.ObjectAnimator;

import io.reactivex.Observable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import io.reactivex.subscribers.SerializedSubscriber;

/**
 * Created by initializing on 2018/9/4.
 */

public class RxBus {

    private final Subject<Object> subject = PublishSubject.create().toSerialized() ;
    private static volatile  RxBus rxBus ;
    private RxBus(){}


    public static RxBus getInstance()
    {
        if (rxBus == null)
        {
            synchronized (RxBus.class)
            {
                if (rxBus == null)
                {
                    rxBus = new RxBus() ;
                }
            }
        }

        return rxBus ;
    }

    public void post(Object object)
    {
        subject.onNext(object);
    }

    public <T>Observable<T> tObservable(Class<T> tClass)
    {
        return subject.ofType(tClass) ;
    }

}

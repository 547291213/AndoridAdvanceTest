package com.example.xkfeng.andoridadvancetest.Interface;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * Created by initializing on 2018/9/4.
 */

public class RxBusForBackPressure {

    private static volatile  RxBusForBackPressure rxbus ;

    private final FlowableProcessor<Object> processor = PublishProcessor.create().toSerialized() ;

    private RxBusForBackPressure()
    {
    }

    public static RxBusForBackPressure getInstance()
    {
        if (rxbus == null)
        {
            synchronized (RxBusForBackPressure.class)
            {
                if (rxbus == null)
                {
                    rxbus = new RxBusForBackPressure() ;
                }
            }
        }

        return rxbus ;
    }

    public void post(Object object)
    {
        processor.onNext(object);
    }

    public <T>Flowable<T> tObservable(Class<T> tClass)
    {
        return processor.ofType(tClass) ;
    }
}

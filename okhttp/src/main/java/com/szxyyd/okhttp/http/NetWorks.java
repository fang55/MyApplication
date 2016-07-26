package com.szxyyd.okhttp.http;

import com.szxyyd.okhttp.inter.HomeInterface;
import com.szxyyd.okhttp.modle.SvrFun;
import com.szxyyd.okhttp.modle.svrCal;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jq on 2016/7/25.
 */
public class NetWorks extends RetrofitUtils{
    protected static final HomeInterface service = getRetrofit().create(HomeInterface.class);
    //POST请求
    public static void verfacationCodePost(String tel, String pass,Observer<svrCal> observer){
        setSubscribe(service.RxContributors(tel, pass),observer);
    }

   public static void lodeMoreData(){

    }
    /**
     * 插入观察者
     * @param observable
     * @param observer
     * @param <T>
     */
    public static <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
                 observable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())//子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }
}

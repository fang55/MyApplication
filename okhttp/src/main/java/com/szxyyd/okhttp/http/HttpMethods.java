package com.szxyyd.okhttp.http;

import android.util.Log;
import android.widget.Toast;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.szxyyd.okhttp.inter.HomeInterface;
import com.szxyyd.okhttp.modle.HomeRequest;
import com.szxyyd.okhttp.modle.PriceAndLvl;
import com.szxyyd.okhttp.modle.SvrFun;
import com.szxyyd.okhttp.modle.svrCal;

import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func3;
import rx.schedulers.Schedulers;

/**
 *
 */
public class HttpMethods {
    public static final String TAG = "HttpMethods";
    public static final String BASE_URL = "http://183.232.35.71:8080/xyhl/";
    private static final int DEFAULT_TIMEOUT = 5;
    private  Retrofit retrofit;
    public HomeInterface homeInterface;
    private static OkHttpClient mOkHttpClient;
    //构造方法私有
    private HttpMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(10, TimeUnit.SECONDS)//设置写入超时时间;
                .addNetworkInterceptor(new StethoInterceptor());
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        homeInterface = retrofit.create(HomeInterface.class);
    }
    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final HttpMethods INSTANCE = new HttpMethods();
    }
    //获取单例
    public static HttpMethods getInstance(){
        return SingletonHolder.INSTANCE;
    }
    /**
     * 用于获取数据
     * @param subscriber  由调用者传过来的观察者对象
     */
    public void getData(Subscriber<svrCal> subscriber){
        Observable observable = homeInterface.RxContributors("svrCal","1000");
        toSubscribe(observable, subscriber);
    }
    /**
     * 用于同时请求多条数据
     */
    public void lodeMoreData(Subscriber<HomeRequest> homneObservable){
        Observable<svrCal> svrCalObservable = homeInterface
                .RxContributors("svrCal","1000")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observable<SvrFun> svrFunCalObservable = homeInterface
                .RxSvrFunData("svrFun","1000")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observable<PriceAndLvl> lvlFunCalObservable = homeInterface
                .RxPriceAndLvlData("getPriceAndLvl","1000","440300")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observable.zip(svrCalObservable, svrFunCalObservable, lvlFunCalObservable,
                new Func3<svrCal, SvrFun, PriceAndLvl, HomeRequest>() {
                    @Override
                    public HomeRequest call(svrCal svrCal, SvrFun svrFun, PriceAndLvl priceAndLvl) {
                        HomeRequest request = new HomeRequest();
                        request.setSvrCal(svrCal);
                        request.setSvrFun(svrFun);
                        request.setPriceAndLvl(priceAndLvl);
                        return request;
                    }
                })
                .subscribe(homneObservable);
    }

    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s){
         o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }
    private <T> Observable toMoreSubscribe(Observable<T> observable){
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }
    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T>   Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
   /* private class HttpResultFunc<T> implements Func1<HttpResult<T>, T>{

        @Override
        public T call(HttpResult<T> httpResult) {
            if (httpResult.getCount() == 0) {
                throw new ApiException(100);
            }
            return httpResult.getSubjects();
        }
    }
*/
}

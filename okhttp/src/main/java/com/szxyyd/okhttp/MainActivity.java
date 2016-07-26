package com.szxyyd.okhttp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.szxyyd.okhttp.http.HttpMethods;
import com.szxyyd.okhttp.inter.HomeInterface;
import com.szxyyd.okhttp.inter.SubscriberOnNextListener;
import com.szxyyd.okhttp.modle.HomeRequest;
import com.szxyyd.okhttp.modle.PriceAndLvl;
import com.szxyyd.okhttp.modle.ProgressSubscriber;
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

public class MainActivity extends Activity {
    public static String TAG = "MainActivity";
    Button clickMeBN;
    public static String baseUrl = "http://183.232.35.71:8080/xyhl/";
    //public static String svrCalUrl = "svr?a=svrCal&svrid=1000";
    // public static String svrFunUrl = "svr?a=svrFun&svrid=1000";
    // public static String getPriceAndLvlUrl = "svr?a=getPriceAndLvl&svrid=1000&city=440300";
    // map.put("svrid",String.valueOf(svrid));  svr?a=svrFun
    private SubscriberOnNextListener getTopMovieOnNext;
    private SubscriberOnNextListener getMoreOnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clickMeBN = (Button) findViewById(R.id.click_me_BN);
        clickMeBN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  lodeData();
                getMoreData();
            }
        });
        getTopMovieOnNext = new SubscriberOnNextListener<svrCal>() {
            @Override
            public void onNext(svrCal nurseType) {
                List<svrCal.SvrCalBean> list = nurseType.getSvrCal();
                for(int i = 0 ;i< list.size();i++){
                    Log.e("onNext", "getLvl()==" + list.get(i).getLvl());
                }
            }
        };
        getMoreOnNext = new SubscriberOnNextListener<HomeRequest>() {
            @Override
            public void onNext(HomeRequest homeRequest) {
                svrCal svr = homeRequest.getSvrCal();
                List<svrCal.SvrCalBean> svrList = svr.getSvrCal();
                for(int i = 0; i < svrList.size() ;i++){
                    Log.e("onNext", "getLvl()==" + svrList.get(i).getLvl());
                }
            }
        };
    }
    /**
     * 加载数据
     */
    private void lodeData() {
        Log.e("MainActivity", "lodeData()==");
        HttpMethods.getInstance().getData(new ProgressSubscriber(getTopMovieOnNext, MainActivity.this));
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5, TimeUnit.SECONDS);
        builder.addNetworkInterceptor(new StethoInterceptor());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://183.232.35.71:8080/xyhl/")
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        HomeInterface homeInterface =  retrofit.create(HomeInterface.class);
        homeInterface.RxContributors("svrCal","1000")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<svrCal>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(MainActivity.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(svrCal nurseType) {
                       Log.e("MainActivity", "nurseTypes.size()==" + nurseType.getSvrCal());
                        List<svrCal.SvrCalBean> list = nurseType.getSvrCal();
                        for(int i = 0 ;i< list.size();i++){
                            Log.e("MainActivity", "getLvl()==" + list.get(i).getLvl());
                        }
                    }
                });
    }

    /**
     * 同时请求多个接口
     */
    private void getMoreData(){
        HttpMethods.getInstance().lodeMoreData(new ProgressSubscriber(getMoreOnNext, MainActivity.this));
      /* OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(8, TimeUnit.SECONDS);
        builder.addNetworkInterceptor(new StethoInterceptor());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://183.232.35.71:8080/xyhl/")
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        Observable<svrCal> svrCalObservable = retrofit
                .create(HomeInterface.class)
                .RxContributors("svrCal","1000")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observable<SvrFun> svrFunCalObservable = retrofit
                .create(HomeInterface.class)
                .RxSvrFunData("svrFun","1000")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observable<PriceAndLvl> lvlFunCalObservable = retrofit
                .create(HomeInterface.class)
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
          .subscribe(new Subscriber<HomeRequest>() {
           @Override
           public void onCompleted() {
               Toast.makeText(MainActivity.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
           }
           @Override
           public void onError(Throwable e) {
               Log.e(TAG, "e.getMessage()--" + e.getMessage().toString());
           }
           @Override
           public void onNext(HomeRequest request) {
               Log.e(TAG, "getSvrCal--" + request.getSvrCal().toString());
               Log.e(TAG, "getSvrFun--" + request.getSvrFun().toString());
               Log.e(TAG, "getPriceAndLvl--" + request.getPriceAndLvl().toString());
               svrCal svr = request.getSvrCal();
               SvrFun svrfun = request.getSvrFun();
               PriceAndLvl priceAndLvl = request.getPriceAndLvl();
               List<svrCal.SvrCalBean> svrList = svr.getSvrCal();
               for(int i = 0; i < svrList.size() ;i++){
                   Log.e("onNext", "getLvl()==" + svrList.get(i).getLvl());
               }
           }
       });*/
    }
}

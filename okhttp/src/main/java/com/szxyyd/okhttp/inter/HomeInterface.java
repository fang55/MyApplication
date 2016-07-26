package com.szxyyd.okhttp.inter;

import com.szxyyd.okhttp.modle.PriceAndLvl;
import com.szxyyd.okhttp.modle.SvrFun;
import com.szxyyd.okhttp.modle.svrCal;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jq on 2016/7/21.
 */
public interface HomeInterface {
     @GET("svr")
     Observable<svrCal> RxContributors(@Query("a") String svr, @Query("svrid") String owner);
     @GET("svr")
     Observable<SvrFun> RxSvrFunData(@Query("a") String svr, @Query("svrid") String owner);
     @GET("svr")
     Observable<PriceAndLvl> RxPriceAndLvlData(@Query("a") String svr, @Query("svrid") String owner,@Query("city") String city);
}

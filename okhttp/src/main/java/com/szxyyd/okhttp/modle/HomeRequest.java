package com.szxyyd.okhttp.modle;

/**
 * Created by jq on 2016/7/22.
 */
public class HomeRequest {
    public svrCal svrCal;
    public SvrFun svrFun;
    public PriceAndLvl priceAndLvl;
    public HomeRequest() {
        super();
    }

    public HomeRequest(svrCal svrCal, SvrFun svrFun, PriceAndLvl priceAndLvl){
           this.svrCal = svrCal;
           this.svrFun = svrFun;
        this.priceAndLvl = priceAndLvl;
    }

    public com.szxyyd.okhttp.modle.svrCal getSvrCal() {
        return svrCal;
    }

    public void setSvrCal(com.szxyyd.okhttp.modle.svrCal svrCal) {
        this.svrCal = svrCal;
    }

    public SvrFun getSvrFun() {
        return svrFun;
    }

    public void setSvrFun(SvrFun svrFun) {
        this.svrFun = svrFun;
    }

    public PriceAndLvl getPriceAndLvl() {
        return priceAndLvl;
    }

    public void setPriceAndLvl(PriceAndLvl priceAndLvl) {
        this.priceAndLvl = priceAndLvl;
    }
}

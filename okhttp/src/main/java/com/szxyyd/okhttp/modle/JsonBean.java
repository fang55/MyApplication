package com.szxyyd.okhttp.modle;

import java.util.List;

/**
 * Created by jq on 2016/7/4.
 */
public class JsonBean {
    private List<svrCal> svr;  //首页

    public JsonBean() {
        super();
    }

    public List<svrCal> getSvr() {
        return svr;
    }
    public void setSvr(List<svrCal> svr) {
        this.svr = svr;
    }

}

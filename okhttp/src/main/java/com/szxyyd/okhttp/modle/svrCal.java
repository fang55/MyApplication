package com.szxyyd.okhttp.modle;

import java.util.List;

/**
 * Created by jq on 2016/6/12.
 */
public class svrCal{

    /**
     * id : 1
     * unt : 心悦云端信息技术有限公司
     * app : 母婴护理交易云系统
     * lvl : 一级
     * idx : 1
     * remark : 月套餐服务4次
     * name : 月套餐服务
     * method : *0+1380
     * svrid : 产后修复师
     * type : 套餐
     */

    private List<SvrCalBean> svrCal;
    public List<SvrCalBean> getSvrCal() {
        return svrCal;
    }

    public void setSvrCal(List<SvrCalBean> svrCal) {
        this.svrCal = svrCal;
    }

    public static class SvrCalBean {
        private String id;
        private String unt;
        private String app;
        private String lvl;
        private String idx;
        private String remark;
        private String name;
        private String method;
        private String svrid;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUnt() {
            return unt;
        }

        public void setUnt(String unt) {
            this.unt = unt;
        }

        public String getApp() {
            return app;
        }

        public void setApp(String app) {
            this.app = app;
        }

        public String getLvl() {
            return lvl;
        }

        public void setLvl(String lvl) {
            this.lvl = lvl;
        }

        public String getIdx() {
            return idx;
        }

        public void setIdx(String idx) {
            this.idx = idx;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getSvrid() {
            return svrid;
        }

        public void setSvrid(String svrid) {
            this.svrid = svrid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}

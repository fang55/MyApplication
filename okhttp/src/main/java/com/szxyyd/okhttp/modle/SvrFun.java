package com.szxyyd.okhttp.modle;

import java.util.List;

/**
 * Created by jq on 2016/7/22.
 */
public class SvrFun {

    /**
     * id : 1
     * unt : 心悦云端信息技术有限公司
     * app : 母婴护理交易云系统
     * lvl : 一级
     * idx : 6
     * status : 正常
     * name : 营养检查2、营养搭配调2、饮食护理指导2
     * svrid : 产后修复师
     */

    private List<SvrFunBean> svrFun;

    public List<SvrFunBean> getSvrFun() {
        return svrFun;
    }

    public void setSvrFun(List<SvrFunBean> svrFun) {
        this.svrFun = svrFun;
    }

    public static class SvrFunBean {
        private String id;
        private String unt;
        private String app;
        private String lvl;
        private String idx;
        private String status;
        private String name;
        private String svrid;

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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSvrid() {
            return svrid;
        }

        public void setSvrid(String svrid) {
            this.svrid = svrid;
        }
    }
}

package com.szxyyd.okhttp.modle;

import java.util.List;

/**
 * Created by jq on 2016/7/22.
 */
public class PriceAndLvl {

    /**
     * content : A套餐：1.产后中药祛风洗头按摩 2.面部美肤淡化妊娠斑护理 2.胸腹部修护护理 3.上下肢修护护理
     * unt : 8
     * id : 2
     * app : 200
     * lvl : 1000
     * price : 400
     * name : A套餐
     * dscid : 6
     * svrid : 1000
     * lvlid : 2
     * ifgb : 1
     * city : 440300
     */
    private List<YxvPriceLvlBean> yxvPriceLvl;
    public List<YxvPriceLvlBean> getYxvPriceLvl() {
        return yxvPriceLvl;
    }
    public void setYxvPriceLvl(List<YxvPriceLvlBean> yxvPriceLvl) {
        this.yxvPriceLvl = yxvPriceLvl;
    }

    public static class YxvPriceLvlBean {
        private String content;
        private String unt;
        private String id;
        private String app;
        private String lvl;
        private String price;
        private String name;
        private String dscid;
        private String svrid;
        private String lvlid;
        private String ifgb;
        private String city;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUnt() {
            return unt;
        }

        public void setUnt(String unt) {
            this.unt = unt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDscid() {
            return dscid;
        }

        public void setDscid(String dscid) {
            this.dscid = dscid;
        }

        public String getSvrid() {
            return svrid;
        }

        public void setSvrid(String svrid) {
            this.svrid = svrid;
        }

        public String getLvlid() {
            return lvlid;
        }

        public void setLvlid(String lvlid) {
            this.lvlid = lvlid;
        }

        public String getIfgb() {
            return ifgb;
        }

        public void setIfgb(String ifgb) {
            this.ifgb = ifgb;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }
}

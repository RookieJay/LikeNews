package pers.ll.likenews.model;

import java.util.List;

public class WhetherResult {

    /**
     * code : 200
     * msg : 成功!
     * data : {"yesterday":{"date":"14日星期五","high":"高温 7℃","fx":"无持续风向","low":"低温 4℃","fl":"","type":"阴"},"city":"成都","aqi":"89","forecast":[{"date":"15日星期六","high":"高温 10℃","fengli":"","low":"低温 2℃","fengxiang":"无持续风向","type":"多云"},{"date":"16日星期天","high":"高温 15℃","fengli":"","low":"低温 3℃","fengxiang":"无持续风向","type":"晴"},{"date":"17日星期一","high":"高温 14℃","fengli":"","low":"低温 5℃","fengxiang":"无持续风向","type":"晴"},{"date":"18日星期二","high":"高温 11℃","fengli":"","low":"低温 6℃","fengxiang":"无持续风向","type":"多云"},{"date":"19日星期三","high":"高温 12℃","fengli":"","low":"低温 6℃","fengxiang":"无持续风向","type":"多云"}],"ganmao":"昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。","wendu":"9"}
     */

    private int code;
    private String msg;
    private Whether data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Whether getData() {
        return data;
    }

    public void setData(Whether data) {
        this.data = data;
    }


}

package pers.ll.likenews.base;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;

public interface Const {

    interface URL {
        String BASE_URL = "http://is.snssdk.com/api/news/feed/v51/?";
    }

    interface Param {
//        热点	news_hot
//        视频	video
//        社会	news_society
//        娱乐	news_entertainment
//        问答	question_and_answer
//        图片	组图
//        科技	news_tech
//        汽车	news_car
//        体育	news_sport
//        财经	news_finance
//        军事	news_military
//        国际	news_world
        String CATEGORY = "category";   //新闻类别
        String REFER = "refer"; //固定值1
        String COUNT = "count";   //返回数量，默认为20
    }

    interface KeyWords {
        String news_hot = "news_hot";
        String video = "video";
        String news_society = "news_society";
        String news_entertainment = "news_entertainment";
        String question_and_answer = "question_and_answer";
        String news_pictures = "组图";
        String news_tech = "news_tech";
        String news_car = "news_car";
        String news_sport = "news_sport";
        String news_finance = "news_finance";
        String news_military = "news_military";
        String news_world = "news_world";
    }

    @SuppressLint("SimpleDateFormat")
    interface DateFormat {

        SimpleDateFormat WITH_HMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat WITHOUT_HMS = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat WITHOUT_HMS_00 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat HHMM = new SimpleDateFormat("HH:mm");
        SimpleDateFormat HMM = new SimpleDateFormat("H:mm");
        SimpleDateFormat MMDDHHmm = new SimpleDateFormat("MM-dd HH:mm");
        SimpleDateFormat CN_M_D = new SimpleDateFormat("M月d日");
        SimpleDateFormat CN_MM_DD = new SimpleDateFormat("MM月dd日");
        SimpleDateFormat CN_MD_H_m = new SimpleDateFormat("M月d日 H时m分");
        SimpleDateFormat CN_WITHOUT_HMS = new SimpleDateFormat("yyyy年MM月dd日");
    }
}

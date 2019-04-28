package pers.ll.likenews.consts;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;

public interface Const {

    interface URL {
        String BASE_URL_NEWS = "http://is.snssdk.com/api/news/";
        String BASE_URL_MUSIC = "https://api.itooi.cn/music/netease/";
    }

    interface Key{
        String KEY_NEWS = "KEY_NEWS";
        String KEY_MUSIC_LIST = "KEY_MUSIC_LIST";
        String KEY_MUSIC = "KEY_MUSIC";
    }

    interface Param {
        //新闻
        String CATEGORY = "category";   //新闻类别
        String REFER = "refer"; //固定值1
        String COUNT = "count";   //返回数量，默认为20
        //音乐
        String key = "key"; //  请求秘钥，QQ群号	579621905
        String s = "s";     //  搜索关键词
        String type = "type";   //搜索类型	默认为 song
        String limit = "limit"; //请求数量	默认为 100
        String offset = "offset";   //	分页	默认第1页
    }

    interface KeyWords_News {
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


    interface Search_Type_Music {
        String song = "song";
        String singer = "singer";
        String album = "album";
        String list = "list";
        String video = "video";
        String radio = "radio";
        String user = "user";
        String lrc = "lrc";

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

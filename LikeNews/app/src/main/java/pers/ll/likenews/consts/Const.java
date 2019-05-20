package pers.ll.likenews.consts;

import android.annotation.SuppressLint;


import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;

public interface Const {

    interface URL {
        String BASE_URL_NEWS = "http://is.snssdk.com/api/news/";
        String BASE_URL_MUSIC = "https://api.itooi.cn/music/netease/"; //旧版音乐接口，2019年6月30日停止维护
        String BASE_URL_MUSIC_NEW = "https://v1.itooi.cn/netease/"; //新版音乐接口
        String BASE_URL_MUSIC_XIAOWEI = "https://api.mlwei.com/music/api/"; //小伟博客网易云音乐wy
        String BASE_URL_MOVIE = "https://api.douban.com/v2/movie/";
        String BASE_URL_WHETHER = "https://www.apiopen.top/";
        String BING_DAILY_PIC = "https://api.mlwei.com/bing.php"; //必应每日一图
        String BASE_URL_OPEN_API = "https://api.apiopen.top/";
        String BASE_URL_MXWHETHER = "http://aider.meizu.com/app/weather/";
    }

    interface Key{
        String KEY_NEWS = "KEY_NEWS";
        String KEY_MUSIC_LIST = "KEY_MUSIC_LIST";
        String KEY_MUSIC = "KEY_MUSIC";
        String KEY_MOVIE = "KEY_MOVIE";
        String KEY_MOVIE_URL = "KEY_MOVIE_URL";
        String START_TYPE = "START_TYPE";
        String KEY_FILMER = "KEY_FILMER";
        String KEY_MSG = "KEY_MSG";
        String KEY_WHETHER = "KEY_WHETHER";
        String KEY_POSITION = "KEY_POSITION";
        String KEY_TYPE = "KEY_TYPE";
        String KEY_WHETHER_BACKGROUND = "KEY_WHETHER_BACKGROUND";
        String KEY_CITY = "KEY_CITY";
    }

    interface Type {
        int TYPE_NEWS = 1;
        int TYPE_MUSIC = 2;
        int TYPE_MOVIE = 3;
        int VIEW_TYPE_CAT = 1;
        int VIEW_TYPE_SUB_CAT = 2;
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
        String netease = "netease"; // 网易云
        String tencent = "tencent"; //QQ音乐
        String kugou = "kugou"; //酷狗
        String kuwo = "kuwo"; //酷我
        //电影
        String start = "start"; //start : 数据的开始项
        String count = "count"; //count：单页条数
        String city = "city";   //city：城市
        String subject = "subject"; //电影
        String apikey = "0df993c66c0c636e29ecbb5344252a4a"; //豆瓣电影apikey，没有限制调用10次/分钟，有的话40次/分钟。
        //小伟博客网易云音乐
//        type	必需	解析类型：song 单曲，songlist 歌单，so 搜索，url 链接，pic 专辑图，lrc 歌词
    }

    interface News_Type {
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
        SimpleDateFormat MMSS = new SimpleDateFormat("mm:ss");
    }

    interface RESULT_CODE {
        int Args_Success = 0;
        int Args_Failure = 1;
        int Args_Empty = 2;
        int CODE_SEARCH = 1;
    }
}

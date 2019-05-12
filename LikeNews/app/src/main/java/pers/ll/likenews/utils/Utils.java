package pers.ll.likenews.utils;

public class Utils {

    /**
     * //正则表达式过滤中文
     */
    public static String get_None_CN_Str(String str) {
        return str.replaceAll("[\u4e00-\u9fa5]+", "");
    }
}

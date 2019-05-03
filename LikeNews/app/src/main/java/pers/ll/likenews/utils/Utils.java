package pers.ll.likenews.utils;

public class Utils {

    public static String get_None_CN_Str(String str) {
        //待过滤的字符串
        return str.replaceAll("[\u4e00-\u9fa5]+", "");
    }
}

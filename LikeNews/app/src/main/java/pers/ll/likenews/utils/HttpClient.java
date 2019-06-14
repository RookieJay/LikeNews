package pers.ll.likenews.utils;

import okhttp3.OkHttpClient;

public class HttpClient {

    private static final OkHttpClient.Builder builder = new OkHttpClient.Builder();

    public static OkHttpClient.Builder getInstance() {
        return builder;
    }


}

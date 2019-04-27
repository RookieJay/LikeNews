package pers.ll.likenews.api

import pers.ll.likenews.model.Music
import pers.ll.likenews.model.NewsResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface Api {

     /**
      * 头条新闻
      */
    @GET("feed/v51/")
    fun getNews(@QueryMap params : LinkedHashMap<String, Any>) : Call<NewsResult>

     /**
      *  搜索音乐/专辑/歌词/歌单/MV/用户/歌手/电台搜索
      */
     @GET("/search")
     fun searchMusic(@QueryMap params : LinkedHashMap<String, Any>) : Call<Result<Music>>
 }

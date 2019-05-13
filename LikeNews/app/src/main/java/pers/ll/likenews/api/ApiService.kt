package pers.ll.likenews.api

import pers.ll.likenews.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

     /**
      * 头条新闻
      */
    @GET("feed/v51/")
    fun getNews(@QueryMap params : LinkedHashMap<String, Any>) : Call<NewsResult>

     /**
      *  搜索音乐/专辑/歌词/歌单/MV/用户/歌手/电台搜索
      */
     @GET("search")
     fun searchMusic(@QueryMap params : LinkedHashMap<String, Any>) : Call<MusicResult<Music>>

    /**
     *专辑
     */
    @GET("album")
    fun getAlbum(@QueryMap params: LinkedHashMap<String, Any>) : Call<MusicResult<Album>>

    /**
     * 豆瓣热映电影 nowplaying
     * start : 数据的开始项
     * count：单页条数
     * city：城市
     */
    @GET("in_theaters")
    fun getHotMovie(@QueryMap params: LinkedHashMap<String, Any>) : Call<MovieResult>

    /**
     * 获取电影详情
     */
    @GET("subject/{id}")
    fun getMovie(@Path("id") id : String, @QueryMap params: LinkedHashMap<String, Any>) : Call<Movie>

    /**
     * 校验url是否可播放
     */
    @GET("url")
    fun validateMusic(@QueryMap params: LinkedHashMap<String, Any?>) : Call<MusicResult<String>>

    /**
     * 天气
     */
    @GET("weatherApi")
    fun getWhether(@Query("city") city : String ) : Call<WhetherResult>

    /**
     * 获取某一类型歌单（全部，华语，粤语...）   https://api.itooi.cn/music/netease/highQualitySongList?key=579621905&cat=全部&limit=100
     */


    /**
     * 小伟博客
     *  搜索音乐/专辑/歌词/歌单/MV/用户/歌手/电台搜索
     */
    @GET("wy")
    fun searchMusicXW(@QueryMap params : LinkedHashMap<String, Any>) : Call<XWMusicResult>


    /**
     * 小伟博客
     * 校验url是否可播放
     */
    @POST
    fun validateMusicXW(@Url url : String) : Call<String>

    /**
     * 豆瓣电影top250
     */
    @GET("top250")
    fun topMovie250(@QueryMap params : LinkedHashMap<String, Any>) : Call<MovieResult>

}


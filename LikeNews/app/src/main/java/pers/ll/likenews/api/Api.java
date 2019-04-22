package pers.ll.likenews.api;

import pers.ll.likenews.base.Const;
import pers.ll.likenews.model.NewsWrapper;
import retrofit2.http.GET;

public interface Api {

    @GET(Const.KeyWords.news_hot)
    NewsWrapper getHotNews();


}

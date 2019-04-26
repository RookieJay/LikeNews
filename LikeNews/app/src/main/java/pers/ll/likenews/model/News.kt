package pers.ll.likenews.model

import android.os.Parcel
import android.os.Parcelable

class News() : Parcelable{

    /**
     * read_count : 55940
     * media_name : 中国文明网
     * ban_comment : 0
     * abstract : 4月6日至9日，中共中央政治局委员、中央书记处书记、中宣部部长刘奇葆在江苏和安徽调研。现在就一起来看看奇葆部长都去了哪些地方，说了哪些话？
     * image_list : [{"url":"http://p1.pstatp.com/list/300x196/1b7f00034ecc8b3a776a.webp","width":310,"url_list":[{"url":"http://p1.pstatp.com/list/300x196/1b7f00034ecc8b3a776a.webp"},{"url":"http://pb3.pstatp.com/list/300x196/1b7f00034ecc8b3a776a.webp"},{"url":"http://p.pstatp.com/list/300x196/1b7f00034ecc8b3a776a.webp"}],"uri":"list/1b7f00034ecc8b3a776a","height":174},{"url":"http://p3.pstatp.com/list/300x196/1b850000248c71c90db8.webp","width":256,"url_list":[{"url":"http://p3.pstatp.com/list/300x196/1b850000248c71c90db8.webp"},{"url":"http://pb2.pstatp.com/list/300x196/1b850000248c71c90db8.webp"},{"url":"http://p.pstatp.com/list/300x196/1b850000248c71c90db8.webp"}],"uri":"list/1b850000248c71c90db8","height":144},{"url":"http://p3.pstatp.com/list/300x196/1b8700002294eca4e1fb.webp","width":256,"url_list":[{"url":"http://p3.pstatp.com/list/300x196/1b8700002294eca4e1fb.webp"},{"url":"http://pb2.pstatp.com/list/300x196/1b8700002294eca4e1fb.webp"},{"url":"http://p.pstatp.com/list/300x196/1b8700002294eca4e1fb.webp"}],"uri":"list/1b8700002294eca4e1fb","height":144}]
     * ban_bury : 1
     * has_video : false
     * article_type : 0
     * tag : government
     * has_m3u8_video : 0
     * keywords : 张家港市,蚌埠市,张家港,熊出没,宿州,苏州博物馆,十二生肖,社会主义核心价值观之歌
     * rid : 201704131850540100040422019618F1
     * show_portrait_article : false
     * user_verified : 1
     * aggr_type : 1
     * cell_type : 0
     * article_sub_type : 0
     * bury_count : 0
     * title : 刘奇葆在江苏安徽看了什么？说了什么？
     * ignore_web_transform : 1
     * source_icon_style : 1
     * tip : 0
     * hot : 0
     * share_url : http://toutiao.com/a6408400842151936257/?iid=9457760980&app=news_article
     * has_mp4_video : 0
     * source : 中国文明网
     * comment_count : 0
     * article_url : http://toutiao.com/group/6408400842151936257/
     * filter_words : [{"id":"8:0","name":"重复、旧闻","is_selected":false},{"id":"9:1","name":"内容质量差","is_selected":false},{"id":"5:9301143","name":"来源:中国文明网","is_selected":false},{"id":"2:31137536","name":"正能量","is_selected":false},{"id":"6:16150","name":"江苏","is_selected":false},{"id":"6:46174","name":"安徽","is_selected":false}]
     * share_count : 51
     * publish_time : 1492073499
     * action_list : [{"action":1,"extra":{},"desc":""},{"action":3,"extra":{},"desc":""},{"action":7,"extra":{},"desc":""},{"action":9,"extra":{},"desc":""}]
     * gallary_image_count : 16
     * cell_layout_style : 1
     * tag_id : 6408400842151936257
     * action_extra : {"channel_id": 3189398996}
     * video_style : 0
     * verified_content : 中国文明网官方头条号
     * display_url : http://toutiao.com/group/6408400842151936257/
     * large_image_list : []
     * item_id : 6408406880250495489
     * is_subject : false
     * show_portrait : false
     * repin_count : 238
     * cell_flag : 11
     * user_info : {"verified_content":"中国文明网官方头条号","avatar_url":"http://p3.pstatp.com/thumb/7757/8375560541","user_id":4533512808,"name":"中国文明网","follower_count":0,"follow":false,"user_auth_info":"{\"auth_type\": \"0\", \"auth_info\": \"中国文明网官方头条号\"}","user_verified":true,"description":"中国文明网官方平台，中央宣传部、中央文明办主办。"}
     * source_open_url : sslocal://profile?uid=4533512808
     * level : 0
     * digg_count : 0
     * behot_time : 1492080054
     * cursor : 1492080054999
     * url : http://toutiao.com/group/6408400842151936257/
     * preload_web : 0
     * user_repin : 0
     * has_image : true
     * item_version : 0
     * media_info : {"user_id":4533512808,"verified_content":"","avatar_url":"http://p2.pstatp.com/large/7757/8375560541","media_id":4533512808,"name":"中国文明网","recommend_type":0,"follow":false,"recommend_reason":"","is_star_user":false,"user_verified":true}
     * group_id : 6408400842151936257
     * middle_image : {"url":"http://p1.pstatp.com/list/300x196/1b7f00034ecc8b3a776a.webp","width":310,"url_list":[{"url":"http://p1.pstatp.com/list/300x196/1b7f00034ecc8b3a776a.webp"},{"url":"http://pb3.pstatp.com/list/300x196/1b7f00034ecc8b3a776a.webp"},{"url":"http://p.pstatp.com/list/300x196/1b7f00034ecc8b3a776a.webp"}],"uri":"list/1b7f00034ecc8b3a776a","height":174}
     */

    var read_count: Int = 0
    var media_name: String? = null
    var ban_comment: Int = 0
    @com.google.gson.annotations.SerializedName("abstract")
    var abstractX: String? = null
    var ban_bury: Int = 0
    var isHas_video: Boolean = false
    var article_type: Int = 0
    var tag: String? = null
    var rid: String? = null
    var isShow_portrait_article: Boolean = false
    var user_verified: Int = 0
    var aggr_type: Int = 0
    var cell_type: Int = 0
    var article_sub_type: Int = 0
    var bury_count: Int = 0
    var title: String? = null
    var ignore_web_transform: Int = 0
    var source_icon_style: Int = 0
    var tip: Int = 0
    var hot: Int = 0
    var share_url: String? = null
    var has_mp4_video: Int = 0
    var source: String? = null
    var comment_count: Int = 0
    var article_url: String? = null
    var share_count: Int = 0
    var publish_time: Int = 0
    var gallary_image_count: Int = 0
    var cell_layout_style: Int = 0
    var tag_id: Long = 0
    var action_extra: String? = null
    var video_style: Int = 0
    var verified_content: String? = null
    var display_url: String? = null
    var item_id: Long = 0
    var isIs_subject: Boolean = false
    var isShow_portrait: Boolean = false
    var repin_count: Int = 0
    var cell_flag: Int = 0
    var user_info: UserInfoBean? = null
    var source_open_url: String? = null
    var level: Int = 0
    var digg_count: Int = 0
    var behot_time: Int = 0
    var cursor: Long = 0
    var url: String? = null
    var preload_web: Int = 0
    var user_repin: Int = 0
    var isHas_image: Boolean = false
    var item_version: Int = 0
    var media_info: MediaInfoBean? = null
    var group_id: Long = 0
    var middle_image: MiddleImageBean? = null
    var image_list: List<ImageListBean>? = null
    var filter_words: List<FilterWordsBean>? = null
    var action_list: List<ActionListBean>? = null
    var large_image_list: List<*>? = null

    constructor(parcel: Parcel) : this() {
        read_count = parcel.readInt()
        media_name = parcel.readString()
        ban_comment = parcel.readInt()
        abstractX = parcel.readString()
        ban_bury = parcel.readInt()
        isHas_video = parcel.readByte() != 0.toByte()
        article_type = parcel.readInt()
        tag = parcel.readString()
        rid = parcel.readString()
        isShow_portrait_article = parcel.readByte() != 0.toByte()
        user_verified = parcel.readInt()
        aggr_type = parcel.readInt()
        cell_type = parcel.readInt()
        article_sub_type = parcel.readInt()
        bury_count = parcel.readInt()
        title = parcel.readString()
        ignore_web_transform = parcel.readInt()
        source_icon_style = parcel.readInt()
        tip = parcel.readInt()
        hot = parcel.readInt()
        share_url = parcel.readString()
        has_mp4_video = parcel.readInt()
        source = parcel.readString()
        comment_count = parcel.readInt()
        article_url = parcel.readString()
        share_count = parcel.readInt()
        publish_time = parcel.readInt()
        gallary_image_count = parcel.readInt()
        cell_layout_style = parcel.readInt()
        tag_id = parcel.readLong()
        action_extra = parcel.readString()
        video_style = parcel.readInt()
        verified_content = parcel.readString()
        display_url = parcel.readString()
        item_id = parcel.readLong()
        isIs_subject = parcel.readByte() != 0.toByte()
        isShow_portrait = parcel.readByte() != 0.toByte()
        repin_count = parcel.readInt()
        cell_flag = parcel.readInt()
        source_open_url = parcel.readString()
        level = parcel.readInt()
        digg_count = parcel.readInt()
        behot_time = parcel.readInt()
        cursor = parcel.readLong()
        url = parcel.readString()
        preload_web = parcel.readInt()
        user_repin = parcel.readInt()
        isHas_image = parcel.readByte() != 0.toByte()
        item_version = parcel.readInt()
        group_id = parcel.readLong()
    }

    class UserInfoBean {

        /**
         * verified_content : 中国文明网官方头条号
         * avatar_url : http://p3.pstatp.com/thumb/7757/8375560541
         * user_id : 4533512808
         * name : 中国文明网
         * follower_count : 0
         * follow : false
         * user_auth_info : {"auth_type": "0", "auth_info": "中国文明网官方头条号"}
         * user_verified : true
         * description : 中国文明网官方平台，中央宣传部、中央文明办主办。
         */

        var verified_content: String? = null
        var avatar_url: String? = null
        var user_id: Long = 0
        var name: String? = null
        var follower_count: Int = 0
        var isFollow: Boolean = false
        var user_auth_info: String? = null
        var isUser_verified: Boolean = false
        var description: String? = null
    }

    class MediaInfoBean {

        /**
         * user_id : 4533512808
         * verified_content :
         * avatar_url : http://p2.pstatp.com/large/7757/8375560541
         * media_id : 4533512808
         * name : 中国文明网
         * recommend_type : 0
         * follow : false
         * recommend_reason :
         * is_star_user : false
         * user_verified : true
         */

        var user_id: Long = 0
        var verified_content: String? = null
        var avatar_url: String? = null
        var media_id: Long = 0
        var name: String? = null
        var recommend_type: Int = 0
        var isFollow: Boolean = false
        var recommend_reason: String? = null
        var isIs_star_user: Boolean = false
        var isUser_verified: Boolean = false
    }

    class MiddleImageBean {

        /**
         * url : http://p1.pstatp.com/list/300x196/1b7f00034ecc8b3a776a.webp
         * width : 310
         * url_list : [{"url":"http://p1.pstatp.com/list/300x196/1b7f00034ecc8b3a776a.webp"},{"url":"http://pb3.pstatp.com/list/300x196/1b7f00034ecc8b3a776a.webp"},{"url":"http://p.pstatp.com/list/300x196/1b7f00034ecc8b3a776a.webp"}]
         * uri : list/1b7f00034ecc8b3a776a
         * height : 174
         */

        var url: String? = null
        var width: Int = 0
        var uri: String? = null
        var height: Int = 0
        var url_list: List<UrlListBean>? = null

        class UrlListBean {

            /**
             * url : http://p1.pstatp.com/list/300x196/1b7f00034ecc8b3a776a.webp
             */

            var url: String? = null
        }
    }

    class ImageListBean {

        /**
         * url : http://p1.pstatp.com/list/300x196/1b7f00034ecc8b3a776a.webp
         * width : 310
         * url_list : [{"url":"http://p1.pstatp.com/list/300x196/1b7f00034ecc8b3a776a.webp"},{"url":"http://pb3.pstatp.com/list/300x196/1b7f00034ecc8b3a776a.webp"},{"url":"http://p.pstatp.com/list/300x196/1b7f00034ecc8b3a776a.webp"}]
         * uri : list/1b7f00034ecc8b3a776a
         * height : 174
         */

        var url: String? = null
        var width: Int = 0
        var uri: String? = null
        var height: Int = 0
        var url_list: List<UrlListBeanX>? = null

        class UrlListBeanX {

            /**
             * url : http://p1.pstatp.com/list/300x196/1b7f00034ecc8b3a776a.webp
             */

            var url: String? = null
        }
    }

    class FilterWordsBean {

        /**
         * id : 8:0
         * name : 重复、旧闻
         * is_selected : false
         */

        var id: String? = null
        var name: String? = null
        var isIs_selected: Boolean = false
    }

    class ActionListBean {

        /**
         * action : 1
         * extra : {}
         * desc :
         */

        var action: Int = 0
        var extra: ExtraBean? = null
        var desc: String? = null

        class ExtraBean
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(read_count)
        parcel.writeString(media_name)
        parcel.writeInt(ban_comment)
        parcel.writeString(abstractX)
        parcel.writeInt(ban_bury)
        parcel.writeByte(if (isHas_video) 1 else 0)
        parcel.writeInt(article_type)
        parcel.writeString(tag)
        parcel.writeString(rid)
        parcel.writeByte(if (isShow_portrait_article) 1 else 0)
        parcel.writeInt(user_verified)
        parcel.writeInt(aggr_type)
        parcel.writeInt(cell_type)
        parcel.writeInt(article_sub_type)
        parcel.writeInt(bury_count)
        parcel.writeString(title)
        parcel.writeInt(ignore_web_transform)
        parcel.writeInt(source_icon_style)
        parcel.writeInt(tip)
        parcel.writeInt(hot)
        parcel.writeString(share_url)
        parcel.writeInt(has_mp4_video)
        parcel.writeString(source)
        parcel.writeInt(comment_count)
        parcel.writeString(article_url)
        parcel.writeInt(share_count)
        parcel.writeInt(publish_time)
        parcel.writeInt(gallary_image_count)
        parcel.writeInt(cell_layout_style)
        parcel.writeLong(tag_id)
        parcel.writeString(action_extra)
        parcel.writeInt(video_style)
        parcel.writeString(verified_content)
        parcel.writeString(display_url)
        parcel.writeLong(item_id)
        parcel.writeByte(if (isIs_subject) 1 else 0)
        parcel.writeByte(if (isShow_portrait) 1 else 0)
        parcel.writeInt(repin_count)
        parcel.writeInt(cell_flag)
        parcel.writeString(source_open_url)
        parcel.writeInt(level)
        parcel.writeInt(digg_count)
        parcel.writeInt(behot_time)
        parcel.writeLong(cursor)
        parcel.writeString(url)
        parcel.writeInt(preload_web)
        parcel.writeInt(user_repin)
        parcel.writeByte(if (isHas_image) 1 else 0)
        parcel.writeInt(item_version)
        parcel.writeLong(group_id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<News> {
        override fun createFromParcel(parcel: Parcel): News {
            return News(parcel)
        }

        override fun newArray(size: Int): Array<News?> {
            return arrayOfNulls(size)
        }
    }
}

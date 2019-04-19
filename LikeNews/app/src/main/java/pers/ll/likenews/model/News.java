package pers.ll.likenews.model;

import java.util.List;

public class News {

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

    private int read_count;
    private String media_name;
    private int ban_comment;
    @com.google.gson.annotations.SerializedName("abstract")
    private String abstractX;
    private int ban_bury;
    private boolean has_video;
    private int article_type;
    private String tag;
    private int has_m3u8_video;
    private String keywords;
    private String rid;
    private boolean show_portrait_article;
    private int user_verified;
    private int aggr_type;
    private int cell_type;
    private int article_sub_type;
    private int bury_count;
    private String title;
    private int ignore_web_transform;
    private int source_icon_style;
    private int tip;
    private int hot;
    private String share_url;
    private int has_mp4_video;
    private String source;
    private int comment_count;
    private String article_url;
    private int share_count;
    private int publish_time;
    private int gallary_image_count;
    private int cell_layout_style;
    private long tag_id;
    private String action_extra;
    private int video_style;
    private String verified_content;
    private String display_url;
    private long item_id;
    private boolean is_subject;
    private boolean show_portrait;
    private int repin_count;
    private int cell_flag;
    private UserInfoBean user_info;
    private String source_open_url;
    private int level;
    private int digg_count;
    private int behot_time;
    private long cursor;
    private String url;
    private int preload_web;
    private int user_repin;
    private boolean has_image;
    private int item_version;
    private MediaInfoBean media_info;
    private long group_id;
    private MiddleImageBean middle_image;
    private List<ImageListBean> image_list;
    private List<FilterWordsBean> filter_words;
    private List<ActionListBean> action_list;
    private List<?> large_image_list;

    public int getRead_count() { return read_count;}

    public void setRead_count(int read_count) { this.read_count = read_count;}

    public String getMedia_name() { return media_name;}

    public void setMedia_name(String media_name) { this.media_name = media_name;}

    public int getBan_comment() { return ban_comment;}

    public void setBan_comment(int ban_comment) { this.ban_comment = ban_comment;}

    public String getAbstractX() { return abstractX;}

    public void setAbstractX(String abstractX) { this.abstractX = abstractX;}

    public int getBan_bury() { return ban_bury;}

    public void setBan_bury(int ban_bury) { this.ban_bury = ban_bury;}

    public boolean isHas_video() { return has_video;}

    public void setHas_video(boolean has_video) { this.has_video = has_video;}

    public int getArticle_type() { return article_type;}

    public void setArticle_type(int article_type) { this.article_type = article_type;}

    public String getTag() { return tag;}

    public void setTag(String tag) { this.tag = tag;}

    public int getHas_m3u8_video() { return has_m3u8_video;}

    public void setHas_m3u8_video(int has_m3u8_video) { this.has_m3u8_video = has_m3u8_video;}

    public String getKeywords() { return keywords;}

    public void setKeywords(String keywords) { this.keywords = keywords;}

    public String getRid() { return rid;}

    public void setRid(String rid) { this.rid = rid;}

    public boolean isShow_portrait_article() { return show_portrait_article;}

    public void setShow_portrait_article(
            boolean show_portrait_article) { this.show_portrait_article = show_portrait_article;}

    public int getUser_verified() { return user_verified;}

    public void setUser_verified(int user_verified) { this.user_verified = user_verified;}

    public int getAggr_type() { return aggr_type;}

    public void setAggr_type(int aggr_type) { this.aggr_type = aggr_type;}

    public int getCell_type() { return cell_type;}

    public void setCell_type(int cell_type) { this.cell_type = cell_type;}

    public int getArticle_sub_type() { return article_sub_type;}

    public void setArticle_sub_type(int article_sub_type) { this.article_sub_type = article_sub_type;}

    public int getBury_count() { return bury_count;}

    public void setBury_count(int bury_count) { this.bury_count = bury_count;}

    public String getTitle() { return title;}

    public void setTitle(String title) { this.title = title;}

    public int getIgnore_web_transform() { return ignore_web_transform;}

    public void setIgnore_web_transform(int ignore_web_transform) { this.ignore_web_transform = ignore_web_transform;}

    public int getSource_icon_style() { return source_icon_style;}

    public void setSource_icon_style(int source_icon_style) { this.source_icon_style = source_icon_style;}

    public int getTip() { return tip;}

    public void setTip(int tip) { this.tip = tip;}

    public int getHot() { return hot;}

    public void setHot(int hot) { this.hot = hot;}

    public String getShare_url() { return share_url;}

    public void setShare_url(String share_url) { this.share_url = share_url;}

    public int getHas_mp4_video() { return has_mp4_video;}

    public void setHas_mp4_video(int has_mp4_video) { this.has_mp4_video = has_mp4_video;}

    public String getSource() { return source;}

    public void setSource(String source) { this.source = source;}

    public int getComment_count() { return comment_count;}

    public void setComment_count(int comment_count) { this.comment_count = comment_count;}

    public String getArticle_url() { return article_url;}

    public void setArticle_url(String article_url) { this.article_url = article_url;}

    public int getShare_count() { return share_count;}

    public void setShare_count(int share_count) { this.share_count = share_count;}

    public int getPublish_time() { return publish_time;}

    public void setPublish_time(int publish_time) { this.publish_time = publish_time;}

    public int getGallary_image_count() { return gallary_image_count;}

    public void setGallary_image_count(int gallary_image_count) { this.gallary_image_count = gallary_image_count;}

    public int getCell_layout_style() { return cell_layout_style;}

    public void setCell_layout_style(int cell_layout_style) { this.cell_layout_style = cell_layout_style;}

    public long getTag_id() { return tag_id;}

    public void setTag_id(long tag_id) { this.tag_id = tag_id;}

    public String getAction_extra() { return action_extra;}

    public void setAction_extra(String action_extra) { this.action_extra = action_extra;}

    public int getVideo_style() { return video_style;}

    public void setVideo_style(int video_style) { this.video_style = video_style;}

    public String getVerified_content() { return verified_content;}

    public void setVerified_content(String verified_content) { this.verified_content = verified_content;}

    public String getDisplay_url() { return display_url;}

    public void setDisplay_url(String display_url) { this.display_url = display_url;}

    public long getItem_id() { return item_id;}

    public void setItem_id(long item_id) { this.item_id = item_id;}

    public boolean isIs_subject() { return is_subject;}

    public void setIs_subject(boolean is_subject) { this.is_subject = is_subject;}

    public boolean isShow_portrait() { return show_portrait;}

    public void setShow_portrait(boolean show_portrait) { this.show_portrait = show_portrait;}

    public int getRepin_count() { return repin_count;}

    public void setRepin_count(int repin_count) { this.repin_count = repin_count;}

    public int getCell_flag() { return cell_flag;}

    public void setCell_flag(int cell_flag) { this.cell_flag = cell_flag;}

    public UserInfoBean getUser_info() { return user_info;}

    public void setUser_info(UserInfoBean user_info) { this.user_info = user_info;}

    public String getSource_open_url() { return source_open_url;}

    public void setSource_open_url(String source_open_url) { this.source_open_url = source_open_url;}

    public int getLevel() { return level;}

    public void setLevel(int level) { this.level = level;}

    public int getDigg_count() { return digg_count;}

    public void setDigg_count(int digg_count) { this.digg_count = digg_count;}

    public int getBehot_time() { return behot_time;}

    public void setBehot_time(int behot_time) { this.behot_time = behot_time;}

    public long getCursor() { return cursor;}

    public void setCursor(long cursor) { this.cursor = cursor;}

    public String getUrl() { return url;}

    public void setUrl(String url) { this.url = url;}

    public int getPreload_web() { return preload_web;}

    public void setPreload_web(int preload_web) { this.preload_web = preload_web;}

    public int getUser_repin() { return user_repin;}

    public void setUser_repin(int user_repin) { this.user_repin = user_repin;}

    public boolean isHas_image() { return has_image;}

    public void setHas_image(boolean has_image) { this.has_image = has_image;}

    public int getItem_version() { return item_version;}

    public void setItem_version(int item_version) { this.item_version = item_version;}

    public MediaInfoBean getMedia_info() { return media_info;}

    public void setMedia_info(MediaInfoBean media_info) { this.media_info = media_info;}

    public long getGroup_id() { return group_id;}

    public void setGroup_id(long group_id) { this.group_id = group_id;}

    public MiddleImageBean getMiddle_image() { return middle_image;}

    public void setMiddle_image(MiddleImageBean middle_image) { this.middle_image = middle_image;}

    public List<ImageListBean> getImage_list() { return image_list;}

    public void setImage_list(List<ImageListBean> image_list) { this.image_list = image_list;}

    public List<FilterWordsBean> getFilter_words() { return filter_words;}

    public void setFilter_words(List<FilterWordsBean> filter_words) { this.filter_words = filter_words;}

    public List<ActionListBean> getAction_list() { return action_list;}

    public void setAction_list(List<ActionListBean> action_list) { this.action_list = action_list;}

    public List<?> getLarge_image_list() { return large_image_list;}

    public void setLarge_image_list(List<?> large_image_list) { this.large_image_list = large_image_list;}

    public static class UserInfoBean {

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

        private String verified_content;
        private String avatar_url;
        private long user_id;
        private String name;
        private int follower_count;
        private boolean follow;
        private String user_auth_info;
        private boolean user_verified;
        private String description;

        public String getVerified_content() { return verified_content;}

        public void setVerified_content(String verified_content) { this.verified_content = verified_content;}

        public String getAvatar_url() { return avatar_url;}

        public void setAvatar_url(String avatar_url) { this.avatar_url = avatar_url;}

        public long getUser_id() { return user_id;}

        public void setUser_id(long user_id) { this.user_id = user_id;}

        public String getName() { return name;}

        public void setName(String name) { this.name = name;}

        public int getFollower_count() { return follower_count;}

        public void setFollower_count(int follower_count) { this.follower_count = follower_count;}

        public boolean isFollow() { return follow;}

        public void setFollow(boolean follow) { this.follow = follow;}

        public String getUser_auth_info() { return user_auth_info;}

        public void setUser_auth_info(String user_auth_info) { this.user_auth_info = user_auth_info;}

        public boolean isUser_verified() { return user_verified;}

        public void setUser_verified(boolean user_verified) { this.user_verified = user_verified;}

        public String getDescription() { return description;}

        public void setDescription(String description) { this.description = description;}
    }

    public static class MediaInfoBean {

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

        private long user_id;
        private String verified_content;
        private String avatar_url;
        private long media_id;
        private String name;
        private int recommend_type;
        private boolean follow;
        private String recommend_reason;
        private boolean is_star_user;
        private boolean user_verified;

        public long getUser_id() { return user_id;}

        public void setUser_id(long user_id) { this.user_id = user_id;}

        public String getVerified_content() { return verified_content;}

        public void setVerified_content(String verified_content) { this.verified_content = verified_content;}

        public String getAvatar_url() { return avatar_url;}

        public void setAvatar_url(String avatar_url) { this.avatar_url = avatar_url;}

        public long getMedia_id() { return media_id;}

        public void setMedia_id(long media_id) { this.media_id = media_id;}

        public String getName() { return name;}

        public void setName(String name) { this.name = name;}

        public int getRecommend_type() { return recommend_type;}

        public void setRecommend_type(int recommend_type) { this.recommend_type = recommend_type;}

        public boolean isFollow() { return follow;}

        public void setFollow(boolean follow) { this.follow = follow;}

        public String getRecommend_reason() { return recommend_reason;}

        public void setRecommend_reason(String recommend_reason) { this.recommend_reason = recommend_reason;}

        public boolean isIs_star_user() { return is_star_user;}

        public void setIs_star_user(boolean is_star_user) { this.is_star_user = is_star_user;}

        public boolean isUser_verified() { return user_verified;}

        public void setUser_verified(boolean user_verified) { this.user_verified = user_verified;}
    }

    public static class MiddleImageBean {

        /**
         * url : http://p1.pstatp.com/list/300x196/1b7f00034ecc8b3a776a.webp
         * width : 310
         * url_list : [{"url":"http://p1.pstatp.com/list/300x196/1b7f00034ecc8b3a776a.webp"},{"url":"http://pb3.pstatp.com/list/300x196/1b7f00034ecc8b3a776a.webp"},{"url":"http://p.pstatp.com/list/300x196/1b7f00034ecc8b3a776a.webp"}]
         * uri : list/1b7f00034ecc8b3a776a
         * height : 174
         */

        private String url;
        private int width;
        private String uri;
        private int height;
        private List<UrlListBean> url_list;

        public String getUrl() { return url;}

        public void setUrl(String url) { this.url = url;}

        public int getWidth() { return width;}

        public void setWidth(int width) { this.width = width;}

        public String getUri() { return uri;}

        public void setUri(String uri) { this.uri = uri;}

        public int getHeight() { return height;}

        public void setHeight(int height) { this.height = height;}

        public List<UrlListBean> getUrl_list() { return url_list;}

        public void setUrl_list(List<UrlListBean> url_list) { this.url_list = url_list;}

        public static class UrlListBean {

            /**
             * url : http://p1.pstatp.com/list/300x196/1b7f00034ecc8b3a776a.webp
             */

            private String url;

            public String getUrl() { return url;}

            public void setUrl(String url) { this.url = url;}
        }
    }

    public static class ImageListBean {

        /**
         * url : http://p1.pstatp.com/list/300x196/1b7f00034ecc8b3a776a.webp
         * width : 310
         * url_list : [{"url":"http://p1.pstatp.com/list/300x196/1b7f00034ecc8b3a776a.webp"},{"url":"http://pb3.pstatp.com/list/300x196/1b7f00034ecc8b3a776a.webp"},{"url":"http://p.pstatp.com/list/300x196/1b7f00034ecc8b3a776a.webp"}]
         * uri : list/1b7f00034ecc8b3a776a
         * height : 174
         */

        private String url;
        private int width;
        private String uri;
        private int height;
        private List<UrlListBeanX> url_list;

        public String getUrl() { return url;}

        public void setUrl(String url) { this.url = url;}

        public int getWidth() { return width;}

        public void setWidth(int width) { this.width = width;}

        public String getUri() { return uri;}

        public void setUri(String uri) { this.uri = uri;}

        public int getHeight() { return height;}

        public void setHeight(int height) { this.height = height;}

        public List<UrlListBeanX> getUrl_list() { return url_list;}

        public void setUrl_list(List<UrlListBeanX> url_list) { this.url_list = url_list;}

        public static class UrlListBeanX {

            /**
             * url : http://p1.pstatp.com/list/300x196/1b7f00034ecc8b3a776a.webp
             */

            private String url;

            public String getUrl() { return url;}

            public void setUrl(String url) { this.url = url;}
        }
    }

    public static class FilterWordsBean {

        /**
         * id : 8:0
         * name : 重复、旧闻
         * is_selected : false
         */

        private String id;
        private String name;
        private boolean is_selected;

        public String getId() { return id;}

        public void setId(String id) { this.id = id;}

        public String getName() { return name;}

        public void setName(String name) { this.name = name;}

        public boolean isIs_selected() { return is_selected;}

        public void setIs_selected(boolean is_selected) { this.is_selected = is_selected;}
    }

    public static class ActionListBean {

        /**
         * action : 1
         * extra : {}
         * desc :
         */

        private int action;
        private ExtraBean extra;
        private String desc;

        public int getAction() { return action;}

        public void setAction(int action) { this.action = action;}

        public ExtraBean getExtra() { return extra;}

        public void setExtra(ExtraBean extra) { this.extra = extra;}

        public String getDesc() { return desc;}

        public void setDesc(String desc) { this.desc = desc;}

        public static class ExtraBean { }
    }
}

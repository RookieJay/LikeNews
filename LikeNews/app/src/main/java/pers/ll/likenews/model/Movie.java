package pers.ll.likenews.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 电影
 */
public class Movie implements Parcelable {

    /**
     * rating : {"max":10,"average":8.8,"stars":"45","min":0}
     * genres : ["动作","科幻","奇幻"]
     * title : 复仇者联盟4：终局之战
     * casts : [{"alt":"https://movie.douban.com/celebrity/1016681/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p56339.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p56339.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p56339.webp"},"name":"小罗伯特·唐尼","id":"1016681"},{"alt":"https://movie.douban.com/celebrity/1017885/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1359877729.49.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1359877729.49.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1359877729.49.webp"},"name":"克里斯·埃文斯","id":"1017885"},{"alt":"https://movie.douban.com/celebrity/1040505/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p15885.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p15885.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p15885.webp"},"name":"马克·鲁弗洛","id":"1040505"}]
     * collect_count : 424217
     * original_title : Avengers: Endgame
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1321812/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1555672594.77.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1555672594.77.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1555672594.77.webp"},"name":"安东尼·罗素","id":"1321812"},{"alt":"https://movie.douban.com/celebrity/1320870/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1525505053.79.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1525505053.79.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1525505053.79.webp"},"name":"乔·罗素","id":"1320870"}]
     * year : 2019
     * images : {"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2552058346.webp","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2552058346.webp","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2552058346.webp"}
     * alt : https://movie.douban.com/subject/26100958/
     * id : 26100958
     */

    private Rating rating; //评分信息
    private String title;
    private int collect_count;
    private String original_title;
    private String subtype;
    private String year;    //电影年份
    private Images images; //电影图url
    private String alt;
    private String id;
    private String summary; //简介
    private String mobile_url; //手机访问地址
    private List<String> genres; //电影类型
    private List<Cast> casts; //主演列表
    private List<Director> directors; //导演列表
    private List<String> countries; //国家/地区

    public Rating getRating() { return rating;}

    public void setRating(Rating rating) { this.rating = rating;}

    public String getTitle() { return title;}

    public void setTitle(String title) { this.title = title;}

    public int getCollect_count() { return collect_count;}

    public void setCollect_count(int collect_count) { this.collect_count = collect_count;}

    public String getOriginal_title() { return original_title;}

    public void setOriginal_title(String original_title) { this.original_title = original_title;}

    public String getSubtype() { return subtype;}

    public void setSubtype(String subtype) { this.subtype = subtype;}

    public String getYear() { return year;}

    public void setYear(String year) { this.year = year;}

    public Images getImages() { return images;}

    public void setImages(Images images) { this.images = images;}

    public String getAlt() { return alt;}

    public void setAlt(String alt) { this.alt = alt;}

    public String getId() { return id;}

    public void setId(String id) { this.id = id;}

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public List<String> getGenres() { return genres;}

    public void setGenres(List<String> genres) { this.genres = genres;}

    public List<Cast> getCasts() { return casts;}

    public void setCasts(List<Cast> casts) { this.casts = casts;}

    public List<Director> getDirectors() { return directors;}

    public void setDirectors(List<Director> directors) { this.directors = directors;}

    public static class Rating implements Parcelable {

        /**
         * max : 10
         * average : 8.8
         * stars : 45
         * min : 0
         */

        private int max;
        private double average;
        private String stars;
        private int min;

        public int getMax() { return max;}

        public void setMax(int max) { this.max = max;}

        public double getAverage() { return average;}

        public void setAverage(double average) { this.average = average;}

        public String getStars() { return stars;}

        public void setStars(String stars) { this.stars = stars;}

        public int getMin() { return min;}

        public void setMin(int min) { this.min = min;}

        @Override
        public int describeContents() { return 0; }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.max);
            dest.writeDouble(this.average);
            dest.writeString(this.stars);
            dest.writeInt(this.min);
        }

        public Rating() {}

        protected Rating(Parcel in) {
            this.max = in.readInt();
            this.average = in.readDouble();
            this.stars = in.readString();
            this.min = in.readInt();
        }

        public static final Creator<Rating> CREATOR = new Creator<Rating>() {
            @Override
            public Rating createFromParcel(Parcel source) {return new Rating(source);}

            @Override
            public Rating[] newArray(int size) {return new Rating[size];}
        };
    }

    public static class Images implements Parcelable {

        /**
         * small : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2552058346.webp
         * large : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2552058346.webp
         * medium : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2552058346.webp
         */

        private String small;
        private String large;
        private String medium;

        public String getSmall() { return small;}

        public void setSmall(String small) { this.small = small;}

        public String getLarge() { return large;}

        public void setLarge(String large) { this.large = large;}

        public String getMedium() { return medium;}

        public void setMedium(String medium) { this.medium = medium;}

        @Override
        public int describeContents() { return 0; }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.small);
            dest.writeString(this.large);
            dest.writeString(this.medium);
        }

        public Images() {}

        protected Images(Parcel in) {
            this.small = in.readString();
            this.large = in.readString();
            this.medium = in.readString();
        }

        public static final Creator<Images> CREATOR = new Creator<Images>() {
            @Override
            public Images createFromParcel(Parcel source) {return new Images(source);}

            @Override
            public Images[] newArray(int size) {return new Images[size];}
        };
    }

    public static class Cast implements Parcelable {

        /**
         * alt : https://movie.douban.com/celebrity/1016681/
         * avatars : {"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p56339.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p56339.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p56339.webp"}
         * name : 小罗伯特·唐尼
         * id : 1016681
         */

        private String alt;
        private Avatars avatars;
        private String name;
        private String id;

        public String getAlt() { return alt;}

        public void setAlt(String alt) { this.alt = alt;}

        public Avatars getAvatars() { return avatars;}

        public void setAvatars(Avatars avatars) { this.avatars = avatars;}

        public String getName() { return name;}

        public void setName(String name) { this.name = name;}

        public String getId() { return id;}

        public void setId(String id) { this.id = id;}

        public static class Avatars implements Parcelable {

            /**
             * small : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p56339.webp
             * large : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p56339.webp
             * medium : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p56339.webp
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() { return small;}

            public void setSmall(String small) { this.small = small;}

            public String getLarge() { return large;}

            public void setLarge(String large) { this.large = large;}

            public String getMedium() { return medium;}

            public void setMedium(String medium) { this.medium = medium;}

            @Override
            public int describeContents() { return 0; }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.small);
                dest.writeString(this.large);
                dest.writeString(this.medium);
            }

            public Avatars() {}

            protected Avatars(Parcel in) {
                this.small = in.readString();
                this.large = in.readString();
                this.medium = in.readString();
            }

            public static final Creator<Avatars> CREATOR = new Creator<Avatars>() {
                @Override
                public Avatars createFromParcel(Parcel source) {return new Avatars(source);}

                @Override
                public Avatars[] newArray(int size) {return new Avatars[size];}
            };
        }

        @Override
        public int describeContents() { return 0; }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.alt);
            dest.writeParcelable(this.avatars, flags);
            dest.writeString(this.name);
            dest.writeString(this.id);
        }

        public Cast() {}

        protected Cast(Parcel in) {
            this.alt = in.readString();
            this.avatars = in.readParcelable(Avatars.class.getClassLoader());
            this.name = in.readString();
            this.id = in.readString();
        }

        public static final Creator<Cast> CREATOR = new Creator<Cast>() {
            @Override
            public Cast createFromParcel(Parcel source) {return new Cast(source);}

            @Override
            public Cast[] newArray(int size) {return new Cast[size];}
        };
    }

    public static class Director implements Parcelable {

        /**
         * alt : https://movie.douban.com/celebrity/1321812/
         * avatars : {"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1555672594.77.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1555672594.77.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1555672594.77.webp"}
         * name : 安东尼·罗素
         * id : 1321812
         */

        private String alt;
        private AvatarsBeanX avatars;
        private String name;
        private String id;

        public String getAlt() { return alt;}

        public void setAlt(String alt) { this.alt = alt;}

        public AvatarsBeanX getAvatars() { return avatars;}

        public void setAvatars(AvatarsBeanX avatars) { this.avatars = avatars;}

        public String getName() { return name;}

        public void setName(String name) { this.name = name;}

        public String getId() { return id;}

        public void setId(String id) { this.id = id;}

        public static class AvatarsBeanX implements Parcelable {

            /**
             * small : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1555672594.77.webp
             * large : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1555672594.77.webp
             * medium : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1555672594.77.webp
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() { return small;}

            public void setSmall(String small) { this.small = small;}

            public String getLarge() { return large;}

            public void setLarge(String large) { this.large = large;}

            public String getMedium() { return medium;}

            public void setMedium(String medium) { this.medium = medium;}

            @Override
            public int describeContents() { return 0; }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.small);
                dest.writeString(this.large);
                dest.writeString(this.medium);
            }

            public AvatarsBeanX() {}

            protected AvatarsBeanX(Parcel in) {
                this.small = in.readString();
                this.large = in.readString();
                this.medium = in.readString();
            }

            public static final Creator<AvatarsBeanX> CREATOR = new Creator<AvatarsBeanX>() {
                @Override
                public AvatarsBeanX createFromParcel(Parcel source) {return new AvatarsBeanX(source);}

                @Override
                public AvatarsBeanX[] newArray(int size) {return new AvatarsBeanX[size];}
            };
        }

        @Override
        public int describeContents() { return 0; }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.alt);
            dest.writeParcelable(this.avatars, flags);
            dest.writeString(this.name);
            dest.writeString(this.id);
        }

        public Director() {}

        protected Director(Parcel in) {
            this.alt = in.readString();
            this.avatars = in.readParcelable(AvatarsBeanX.class.getClassLoader());
            this.name = in.readString();
            this.id = in.readString();
        }

        public static final Creator<Director> CREATOR = new Creator<Director>() {
            @Override
            public Director createFromParcel(Parcel source) {return new Director(source);}

            @Override
            public Director[] newArray(int size) {return new Director[size];}
        };
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.rating, flags);
        dest.writeString(this.title);
        dest.writeInt(this.collect_count);
        dest.writeString(this.original_title);
        dest.writeString(this.subtype);
        dest.writeString(this.year);
        dest.writeParcelable(this.images, flags);
        dest.writeString(this.alt);
        dest.writeString(this.id);
        dest.writeStringList(this.genres);
        dest.writeList(this.casts);
        dest.writeList(this.directors);
        dest.writeString(this.mobile_url);
        dest.writeString(this.summary);
        dest.writeList(this.countries);
    }

    public Movie() {}

    protected Movie(Parcel in) {
        this.rating = in.readParcelable(Rating.class.getClassLoader());
        this.title = in.readString();
        this.collect_count = in.readInt();
        this.original_title = in.readString();
        this.subtype = in.readString();
        this.year = in.readString();
        this.images = in.readParcelable(Images.class.getClassLoader());
        this.alt = in.readString();
        this.id = in.readString();
        this.genres = in.createStringArrayList();
        this.casts = new ArrayList<Cast>();
        in.readList(this.casts, Cast.class.getClassLoader());
        this.directors = new ArrayList<Director>();
        in.readList(this.directors, Director.class.getClassLoader());
        this.mobile_url = in.readString();
        this.summary = in.readString();
        this.countries = in.createStringArrayList();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {return new Movie(source);}

        @Override
        public Movie[] newArray(int size) {return new Movie[size];}
    };
}

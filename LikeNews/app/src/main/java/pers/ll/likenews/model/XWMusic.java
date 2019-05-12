package pers.ll.likenews.model;

import android.os.Parcel;
import android.os.Parcelable;

public class XWMusic implements Parcelable {
    /**
     * id : 1357652429
     * title : Boy With Luv feat. Halsey
     * author : BTS (防弹少年团)
     * url : https://api.mlwei.com/music/api/wy/?key=523077333&cache=0&type=url&id=1357652429
     * pic : https://api.mlwei.com/music/api/wy/?key=523077333&cache=0&type=pic&id=1357652429
     * lrc : https://api.mlwei.com/music/api/wy/?key=523077333&cache=0&type=lrc&id=1357652429
     */

    private int id;
    private String title;
    private String author;
    private String url;
    private String pic;
    private String lrc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getLrc() {
        return lrc;
    }

    public void setLrc(String lrc) {
        this.lrc = lrc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.author);
        dest.writeString(this.url);
        dest.writeString(this.pic);
        dest.writeString(this.lrc);
    }

    public XWMusic() {
    }

    protected XWMusic(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.author = in.readString();
        this.url = in.readString();
        this.pic = in.readString();
        this.lrc = in.readString();
    }

    public static final Parcelable.Creator<XWMusic> CREATOR = new Parcelable.Creator<XWMusic>() {
        @Override
        public XWMusic createFromParcel(Parcel source) {
            return new XWMusic(source);
        }

        @Override
        public XWMusic[] newArray(int size) {
            return new XWMusic[size];
        }
    };
}
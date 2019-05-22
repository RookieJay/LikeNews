package pers.ll.likenews.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class SongList implements Parcelable {

    /**
     * description : ·歌单选曲均为歌词向·
     你说，他们是在写歌还是在写诗？
     如果说我心中最好的词人，大概就是姬赓、黄伟文、梁伟文。
     那些漫不经心的笔调，词语错落着拼凑的凡常的意象和情感。
     无论是万能青年旅店的《揪心的玩笑和漫长的白日梦》里那句
     “用无限适用于未来的方法，热爱聚合又离散的鸟群。”
     还是王菲《约定》里的
     “就算会与你分离，凄绝的戏，要决心忘记我便记不起。”

     你一听，就知道那是真正活过和爱过。

     想起一句歌词

     “感谢永远有歌，把心境道破。哪论动或静，谁也有情，情绪有它抚摸。”

     感谢永远有歌，把心境道破。
     * privacy : 0
     * trackNumberUpdateTime : 1558456185793
     * shareCount : 3
     * trackCount : 56
     * adType : 0
     * coverImgId_str : 109951164036167491
     * specialType : 0
     * id : 2776636409
     * alg : alg_sq_featured
     * totalDuration : 0
     * ordered : true
     * creator : {"birthday":631123200000,"detailDescription":"","backgroundUrl":"http://p1.music.126.net/malkStSfgRTrNrRdRNWT8A==/109951164091229919.jpg","gender":0,"city":1010000,"signature":"","description":"","accountStatus":0,"avatarImgId":109951162942435865,"defaultAvatar":false,"backgroundImgIdStr":"109951164091229919","avatarImgIdStr":"109951162942435865","province":1000000,"nickname":"摇滚纪事","djStatus":10,"avatarUrl":"http://p1.music.126.net/Qgx8u8GTAUuIi9jR7RkWDw==/109951162942435865.jpg","authStatus":0,"vipType":11,"followed":false,"userId":430637111,"mutual":false,"avatarImgId_str":"109951162942435865","authority":0,"userType":0,"backgroundImgId":109951164091229919}
     * subscribers : [{"birthday":-2209017600000,"detailDescription":"","backgroundUrl":"http://p1.music.126.net/rioT9y5BnxVLFCjSIecIeA==/109951163245769206.jpg","gender":2,"city":1010000,"signature":"I have nothing","description":"","accountStatus":0,"avatarImgId":109951164091177495,"defaultAvatar":false,"backgroundImgIdStr":"109951163245769206","avatarImgIdStr":"109951164091177495","province":1000000,"nickname":"julyletter","djStatus":0,"avatarUrl":"http://p1.music.126.net/6FV-X6yy7l9--zRfoV3lGw==/109951164091177495.jpg","authStatus":0,"vipType":11,"followed":false,"userId":14058921,"mutual":false,"avatarImgId_str":"109951164091177495","authority":0,"userType":0,"backgroundImgId":109951163245769206}]
     * commentThreadId : A_PL_0_2776636409
     * highQuality : false
     * updateTime : 1558456185793
     * trackUpdateTime : 1558505119763
     * userId : 430637111
     * tags : ["华语","流行","另类/独立"]
     * anonimous : false
     * commentCount : 20
     * cloudTrackCount : 0
     * coverImgUrl : http://p1.music.126.net/fw7OFFIasHrZJoHIkrMrTQ==/109951164036167491.jpg
     * playCount : 154071
     * coverImgId : 109951164036167491
     * createTime : 1556589424125
     * name : 你的歌词看上去很美
     * subscribedCount : 620
     * status : 0
     * newImported : false
     */

    private String description;
    private int privacy;
    private long trackNumberUpdateTime;
    private int shareCount;
    private int trackCount;
    private int adType;
    private String coverImgId_str;
    private int specialType;
    private long id;
    private String alg;
    private int totalDuration;
    private boolean ordered;
    private Creater creator;
    private String commentThreadId;
    private boolean highQuality;
    private long updateTime;
    private long trackUpdateTime;
    private int userId;
    private boolean anonimous;
    private int commentCount;
    private int cloudTrackCount;
    private String coverImgUrl;
    private int playCount;
    private long coverImgId;
    private long createTime;
    private String name;
    private int subscribedCount;
    private int status;
    private boolean newImported;
    private List<Subscribers> subscribers;
    private List<String> tags;

    public String getDescription() { return description;}

    public void setDescription(String description) { this.description = description;}

    public int getPrivacy() { return privacy;}

    public void setPrivacy(int privacy) { this.privacy = privacy;}

    public long getTrackNumberUpdateTime() { return trackNumberUpdateTime;}

    public void setTrackNumberUpdateTime(
            long trackNumberUpdateTime) { this.trackNumberUpdateTime = trackNumberUpdateTime;}

    public int getShareCount() { return shareCount;}

    public void setShareCount(int shareCount) { this.shareCount = shareCount;}

    public int getTrackCount() { return trackCount;}

    public void setTrackCount(int trackCount) { this.trackCount = trackCount;}

    public int getAdType() { return adType;}

    public void setAdType(int adType) { this.adType = adType;}

    public String getCoverImgId_str() { return coverImgId_str;}

    public void setCoverImgId_str(String coverImgId_str) { this.coverImgId_str = coverImgId_str;}

    public int getSpecialType() { return specialType;}

    public void setSpecialType(int specialType) { this.specialType = specialType;}

    public long getId() { return id;}

    public void setId(long id) { this.id = id;}

    public String getAlg() { return alg;}

    public void setAlg(String alg) { this.alg = alg;}

    public int getTotalDuration() { return totalDuration;}

    public void setTotalDuration(int totalDuration) { this.totalDuration = totalDuration;}

    public boolean isOrdered() { return ordered;}

    public void setOrdered(boolean ordered) { this.ordered = ordered;}

    public Creater getCreator() { return creator;}

    public void setCreater(Creater creator) { this.creator = creator;}

    public String getCommentThreadId() { return commentThreadId;}

    public void setCommentThreadId(String commentThreadId) { this.commentThreadId = commentThreadId;}

    public boolean isHighQuality() { return highQuality;}

    public void setHighQuality(boolean highQuality) { this.highQuality = highQuality;}

    public long getUpdateTime() { return updateTime;}

    public void setUpdateTime(long updateTime) { this.updateTime = updateTime;}

    public long getTrackUpdateTime() { return trackUpdateTime;}

    public void setTrackUpdateTime(long trackUpdateTime) { this.trackUpdateTime = trackUpdateTime;}

    public int getUserId() { return userId;}

    public void setUserId(int userId) { this.userId = userId;}

    public boolean isAnonimous() { return anonimous;}

    public void setAnonimous(boolean anonimous) { this.anonimous = anonimous;}

    public int getCommentCount() { return commentCount;}

    public void setCommentCount(int commentCount) { this.commentCount = commentCount;}

    public int getCloudTrackCount() { return cloudTrackCount;}

    public void setCloudTrackCount(int cloudTrackCount) { this.cloudTrackCount = cloudTrackCount;}

    public String getCoverImgUrl() { return coverImgUrl;}

    public void setCoverImgUrl(String coverImgUrl) { this.coverImgUrl = coverImgUrl;}

    public int getPlayCount() { return playCount;}

    public void setPlayCount(int playCount) { this.playCount = playCount;}

    public long getCoverImgId() { return coverImgId;}

    public void setCoverImgId(long coverImgId) { this.coverImgId = coverImgId;}

    public long getCreateTime() { return createTime;}

    public void setCreateTime(long createTime) { this.createTime = createTime;}

    public String getName() { return name;}

    public void setName(String name) { this.name = name;}

    public int getSubscribedCount() { return subscribedCount;}

    public void setSubscribedCount(int subscribedCount) { this.subscribedCount = subscribedCount;}

    public int getStatus() { return status;}

    public void setStatus(int status) { this.status = status;}

    public boolean isNewImported() { return newImported;}

    public void setNewImported(boolean newImported) { this.newImported = newImported;}

    public List<Subscribers> getSubscribers() { return subscribers;}

    public void setSubscribers(List<Subscribers> subscribers) { this.subscribers = subscribers;}

    public List<String> getTags() { return tags;}

    public void setTags(List<String> tags) { this.tags = tags;}

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.description);
        dest.writeInt(this.privacy);
        dest.writeLong(this.trackNumberUpdateTime);
        dest.writeInt(this.shareCount);
        dest.writeInt(this.trackCount);
        dest.writeInt(this.adType);
        dest.writeString(this.coverImgId_str);
        dest.writeInt(this.specialType);
        dest.writeLong(this.id);
        dest.writeString(this.alg);
        dest.writeInt(this.totalDuration);
        dest.writeByte(this.ordered ? (byte)1 : (byte)0);
        dest.writeParcelable(this.creator, flags);
        dest.writeString(this.commentThreadId);
        dest.writeByte(this.highQuality ? (byte)1 : (byte)0);
        dest.writeLong(this.updateTime);
        dest.writeLong(this.trackUpdateTime);
        dest.writeInt(this.userId);
        dest.writeByte(this.anonimous ? (byte)1 : (byte)0);
        dest.writeInt(this.commentCount);
        dest.writeInt(this.cloudTrackCount);
        dest.writeString(this.coverImgUrl);
        dest.writeInt(this.playCount);
        dest.writeLong(this.coverImgId);
        dest.writeLong(this.createTime);
        dest.writeString(this.name);
        dest.writeInt(this.subscribedCount);
        dest.writeInt(this.status);
        dest.writeByte(this.newImported ? (byte)1 : (byte)0);
        dest.writeTypedList(this.subscribers);
        dest.writeStringList(this.tags);
    }

    public SongList() {}

    protected SongList(Parcel in) {
        this.description = in.readString();
        this.privacy = in.readInt();
        this.trackNumberUpdateTime = in.readLong();
        this.shareCount = in.readInt();
        this.trackCount = in.readInt();
        this.adType = in.readInt();
        this.coverImgId_str = in.readString();
        this.specialType = in.readInt();
        this.id = in.readLong();
        this.alg = in.readString();
        this.totalDuration = in.readInt();
        this.ordered = in.readByte() != 0;
        this.creator = in.readParcelable(Creater.class.getClassLoader());
        this.commentThreadId = in.readString();
        this.highQuality = in.readByte() != 0;
        this.updateTime = in.readLong();
        this.trackUpdateTime = in.readLong();
        this.userId = in.readInt();
        this.anonimous = in.readByte() != 0;
        this.commentCount = in.readInt();
        this.cloudTrackCount = in.readInt();
        this.coverImgUrl = in.readString();
        this.playCount = in.readInt();
        this.coverImgId = in.readLong();
        this.createTime = in.readLong();
        this.name = in.readString();
        this.subscribedCount = in.readInt();
        this.status = in.readInt();
        this.newImported = in.readByte() != 0;
        this.subscribers = in.createTypedArrayList(Subscribers.CREATOR);
        this.tags = in.createStringArrayList();
    }

    public static final Creator<SongList> CREATOR = new Creator<SongList>() {
        @Override
        public SongList createFromParcel(Parcel source) {return new SongList(source);}

        @Override
        public SongList[] newArray(int size) {return new SongList[size];}
    };
}

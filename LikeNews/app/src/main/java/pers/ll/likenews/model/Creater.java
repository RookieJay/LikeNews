package pers.ll.likenews.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Creater implements Parcelable {

    /**
     * birthday : 631123200000
     * detailDescription :
     * backgroundUrl : http://p1.music.126.net/malkStSfgRTrNrRdRNWT8A==/109951164091229919.jpg
     * gender : 0
     * city : 1010000
     * signature :
     * description :
     * accountStatus : 0
     * avatarImgId : 109951162942435865
     * defaultAvatar : false
     * backgroundImgIdStr : 109951164091229919
     * avatarImgIdStr : 109951162942435865
     * province : 1000000
     * nickname : 摇滚纪事
     * djStatus : 10
     * avatarUrl : http://p1.music.126.net/Qgx8u8GTAUuIi9jR7RkWDw==/109951162942435865.jpg
     * authStatus : 0
     * vipType : 11
     * followed : false
     * userId : 430637111
     * mutual : false
     * avatarImgId_str : 109951162942435865
     * authority : 0
     * userType : 0
     * backgroundImgId : 109951164091229919
     */

    private long birthday;
    private String detailDescription;
    private String backgroundUrl;
    private int gender;
    private int city;
    private String signature;
    private String description;
    private int accountStatus;
    private long avatarImgId;
    private boolean defaultAvatar;
    private String backgroundImgIdStr;
    private String avatarImgIdStr;
    private int province;
    private String nickname;
    private int djStatus;
    private String avatarUrl;
    private int authStatus;
    private int vipType;
    private boolean followed;
    private int userId;
    private boolean mutual;
    private String avatarImgId_str;
    private int authority;
    private int userType;
    private long backgroundImgId;

    public long getBirthday() { return birthday;}

    public void setBirthday(long birthday) { this.birthday = birthday;}

    public String getDetailDescription() { return detailDescription;}

    public void setDetailDescription(String detailDescription) { this.detailDescription = detailDescription;}

    public String getBackgroundUrl() { return backgroundUrl;}

    public void setBackgroundUrl(String backgroundUrl) { this.backgroundUrl = backgroundUrl;}

    public int getGender() { return gender;}

    public void setGender(int gender) { this.gender = gender;}

    public int getCity() { return city;}

    public void setCity(int city) { this.city = city;}

    public String getSignature() { return signature;}

    public void setSignature(String signature) { this.signature = signature;}

    public String getDescription() { return description;}

    public void setDescription(String description) { this.description = description;}

    public int getAccountStatus() { return accountStatus;}

    public void setAccountStatus(int accountStatus) { this.accountStatus = accountStatus;}

    public long getAvatarImgId() { return avatarImgId;}

    public void setAvatarImgId(long avatarImgId) { this.avatarImgId = avatarImgId;}

    public boolean isDefaultAvatar() { return defaultAvatar;}

    public void setDefaultAvatar(boolean defaultAvatar) { this.defaultAvatar = defaultAvatar;}

    public String getBackgroundImgIdStr() { return backgroundImgIdStr;}

    public void setBackgroundImgIdStr(
            String backgroundImgIdStr) { this.backgroundImgIdStr = backgroundImgIdStr;}

    public String getAvatarImgIdStr() { return avatarImgIdStr;}

    public void setAvatarImgIdStr(String avatarImgIdStr) { this.avatarImgIdStr = avatarImgIdStr;}

    public int getProvince() { return province;}

    public void setProvince(int province) { this.province = province;}

    public String getNickname() { return nickname;}

    public void setNickname(String nickname) { this.nickname = nickname;}

    public int getDjStatus() { return djStatus;}

    public void setDjStatus(int djStatus) { this.djStatus = djStatus;}

    public String getAvatarUrl() { return avatarUrl;}

    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl;}

    public int getAuthStatus() { return authStatus;}

    public void setAuthStatus(int authStatus) { this.authStatus = authStatus;}

    public int getVipType() { return vipType;}

    public void setVipType(int vipType) { this.vipType = vipType;}

    public boolean isFollowed() { return followed;}

    public void setFollowed(boolean followed) { this.followed = followed;}

    public int getUserId() { return userId;}

    public void setUserId(int userId) { this.userId = userId;}

    public boolean isMutual() { return mutual;}

    public void setMutual(boolean mutual) { this.mutual = mutual;}

    public String getAvatarImgId_str() { return avatarImgId_str;}

    public void setAvatarImgId_str(String avatarImgId_str) { this.avatarImgId_str = avatarImgId_str;}

    public int getAuthority() { return authority;}

    public void setAuthority(int authority) { this.authority = authority;}

    public int getUserType() { return userType;}

    public void setUserType(int userType) { this.userType = userType;}

    public long getBackgroundImgId() { return backgroundImgId;}

    public void setBackgroundImgId(long backgroundImgId) { this.backgroundImgId = backgroundImgId;}

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.birthday);
        dest.writeString(this.detailDescription);
        dest.writeString(this.backgroundUrl);
        dest.writeInt(this.gender);
        dest.writeInt(this.city);
        dest.writeString(this.signature);
        dest.writeString(this.description);
        dest.writeInt(this.accountStatus);
        dest.writeLong(this.avatarImgId);
        dest.writeByte(this.defaultAvatar ? (byte)1 : (byte)0);
        dest.writeString(this.backgroundImgIdStr);
        dest.writeString(this.avatarImgIdStr);
        dest.writeInt(this.province);
        dest.writeString(this.nickname);
        dest.writeInt(this.djStatus);
        dest.writeString(this.avatarUrl);
        dest.writeInt(this.authStatus);
        dest.writeInt(this.vipType);
        dest.writeByte(this.followed ? (byte)1 : (byte)0);
        dest.writeInt(this.userId);
        dest.writeByte(this.mutual ? (byte)1 : (byte)0);
        dest.writeString(this.avatarImgId_str);
        dest.writeInt(this.authority);
        dest.writeInt(this.userType);
        dest.writeLong(this.backgroundImgId);
    }

    public Creater() {}

    protected Creater(Parcel in) {
        this.birthday = in.readLong();
        this.detailDescription = in.readString();
        this.backgroundUrl = in.readString();
        this.gender = in.readInt();
        this.city = in.readInt();
        this.signature = in.readString();
        this.description = in.readString();
        this.accountStatus = in.readInt();
        this.avatarImgId = in.readLong();
        this.defaultAvatar = in.readByte() != 0;
        this.backgroundImgIdStr = in.readString();
        this.avatarImgIdStr = in.readString();
        this.province = in.readInt();
        this.nickname = in.readString();
        this.djStatus = in.readInt();
        this.avatarUrl = in.readString();
        this.authStatus = in.readInt();
        this.vipType = in.readInt();
        this.followed = in.readByte() != 0;
        this.userId = in.readInt();
        this.mutual = in.readByte() != 0;
        this.avatarImgId_str = in.readString();
        this.authority = in.readInt();
        this.userType = in.readInt();
        this.backgroundImgId = in.readLong();
    }

    public static final Parcelable.Creator<Creater> CREATOR = new Parcelable.Creator<Creater>() {
        @Override
        public Creater createFromParcel(Parcel source) {return new Creater(source);}

        @Override
        public Creater[] newArray(int size) {return new Creater[size];}
    };
}

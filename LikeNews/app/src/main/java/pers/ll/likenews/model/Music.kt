package pers.ll.likenews.model

import android.os.Parcel
import android.os.Parcelable

class Music : Parcelable {
    /**
     * id : 471403427
     * name : 我喜欢上你时的内心活动
     * singer : 陈绮贞
     * pic : http://p1.music.126.net/AyyxC4stCu-Pm5qa8gaqDQ==/18762066418246617.jpg?param=400y400
     * lrc : https://api.itooi.cn/music/netease/lrc?id=471403427&key=579621905
     * url : https://api.itooi.cn/music/netease/url?id=471403427&key=579621905
     * time : 225
     */

    var id: String? = null

    var name: String? = null

    var singer: String? = null

    var pic: String? = null

    var lrc: String? = null

    var url: String? = null

    var time: Int = 0

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Music> = object : Parcelable.Creator<Music> {
            override fun createFromParcel(source: Parcel): Music = Music(source)
            override fun newArray(size: Int): Array<Music?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel): this(
    ){
        this.id = source.readString()
        this.name = source.readString()
        this.singer = source.readString()
        this.pic = source.readString()
        this.lrc = source.readString()
        this.url = source.readString()
        this.time = source.readInt()
    }

    constructor()


    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(name)
        writeString(singer)
        writeString(pic)
        writeString(lrc)
        writeString(url)
        writeInt(time)
    }
}

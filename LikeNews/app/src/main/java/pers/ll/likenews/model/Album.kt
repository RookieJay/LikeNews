package pers.ll.likenews.model

import android.os.Parcel
import android.os.Parcelable

class Album(
        val id: String,
        val lrc: String,
        val name: String,
        val pic: String,
        val singer: String,
        val time: Int,
        val url: String
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(lrc)
        writeString(name)
        writeString(pic)
        writeString(singer)
        writeInt(time)
        writeString(url)
    }


    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Album> = object : Parcelable.Creator<Album> {
            override fun createFromParcel(source: Parcel): Album = Album(source)
            override fun newArray(size: Int): Array<Album?> = arrayOfNulls(size)
        }
    }
}
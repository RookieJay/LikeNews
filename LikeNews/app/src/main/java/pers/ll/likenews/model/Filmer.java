package pers.ll.likenews.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Filmer implements Parcelable {

    private List<Movie.Director> directors;
    private List<Movie.Cast> castList;

    public List<Movie.Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Movie.Director> directors) {
        this.directors = directors;
    }

    public List<Movie.Cast> getCastList() {
        return castList;
    }

    public void setCastList(List<Movie.Cast> castList) {
        this.castList = castList;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.directors);
        dest.writeTypedList(this.castList);
    }

    public Filmer() {}

    protected Filmer(Parcel in) {
        this.directors = in.createTypedArrayList(Movie.Director.CREATOR);
        this.castList = in.createTypedArrayList(Movie.Cast.CREATOR);
    }

    public static final Parcelable.Creator<Filmer> CREATOR = new Parcelable.Creator<Filmer>() {
        @Override
        public Filmer createFromParcel(Parcel source) {return new Filmer(source);}

        @Override
        public Filmer[] newArray(int size) {return new Filmer[size];}
    };
}

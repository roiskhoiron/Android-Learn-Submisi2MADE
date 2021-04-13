package com.example.submisi2made.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movies implements Parcelable {

    private String title, year, director, overview, photo;

    public Movies(String title, String year, String director, String overview, String photo) {
        this.title = title;
        this.year = year;
        this.director = director;
        this.overview = overview;
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Movies() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.year);
        dest.writeString(this.director);
        dest.writeString(this.overview);
        dest.writeString(this.photo);
    }

    protected Movies(Parcel in) {
        this.title = in.readString();
        this.year = in.readString();
        this.director = in.readString();
        this.overview = in.readString();
        this.photo = in.readString();
    }

    public static final Creator<Movies> CREATOR = new Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel source) {
            return new Movies(source);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };
}

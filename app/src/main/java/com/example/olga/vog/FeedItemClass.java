package com.example.olga.vog;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class FeedItemClass implements FeedItem, Parcelable {

    @Expose
    private int ID;

    @Expose
    private String bodyType;

    @Expose
    private String mediaLocation;

    @Expose
    //wont be serialized if null
    private String text;

    private Set <UserClass> users;

    public FeedItemClass () {

    }

    public FeedItemClass (int id, String type) {
        this.ID = id;
        this.bodyType = type;

    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public void setMediaLocation(String mediaLocation) {
        this.mediaLocation = mediaLocation;
    }

    public void setUsers(Set<UserClass> users) {
        this.users = users;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public String getBodyType() {
        return bodyType;
    }

    @Override
    public String getMediaLocation() {
        return mediaLocation;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

  //  @Override
    public Set <UserClass> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FeedItemClass{");
        sb.append("ID=").append(ID);
        sb.append(", bodyType='").append(bodyType).append('\'');
        if (mediaLocation!=null) {
            sb.append(", mediaLocation='").append(mediaLocation).append('\'');
        } else if (text!=null) {
            sb.append(", text='").append(text).append('\'');
        }
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedItemClass that = (FeedItemClass) o;
        return ID == that.ID;
    }

    @Override
    public int hashCode() {

        return Objects.hash(ID, bodyType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}

package com.example.olga.vog;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

//This is a concrete implementation of the User interface. We are using the " @Expose" annotations to mark the fields
//have to be presented in the JSON object.
public class FeedItemClass implements FeedItem, Serializable {

    @Expose
    private int ID;

    @Expose
    private String bodyType;

    @Expose
    //will not be serialized if null
    private String mediaLocation;

    @Expose
    //will not be serialized if null
    private String text;

    private Set <UserClass> users;

    //Empty public constructor is required acording to Java documentation to use Gson to serialize and deserialize Java
    // and JSON objects
    public FeedItemClass () {

    }


    public FeedItemClass (int id, String type) {
        this.ID = id;
        this.bodyType = type;
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

    public Set <UserClass> getUsers() {
        return users;
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
    public String toString() {
        //This method is required to represent the object in the UI
        final StringBuilder sb = new StringBuilder("FeedItemClass{");
        sb.append("ID=").append(getID());
        sb.append(", bodyType='").append(getBodyType()).append('\'');
        if (getMediaLocation()!=null) {
            sb.append(", mediaLocation='").append(getMediaLocation()).append('\'');
        } else if (getText()!=null) {
            sb.append(", text='").append(getText()).append('\'');
        }
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        //As we are going to process the collections of the objects of this class, we need to override equals
        // //and hashCode methods.
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedItemClass that = (FeedItemClass) o;
        return ID == that.ID;
    }

    @Override
    public int hashCode() {

        return Objects.hash(ID, bodyType);
    }

}

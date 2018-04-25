package com.example.olga.vog;

import com.google.gson.annotations.Expose;

import java.util.Objects;
import java.util.Set;

public class UserClass implements User {

    @Expose
    private String ID;

    @Expose
    private String userName;

    @Expose
    private Set<FeedItemClass> body;

    public UserClass () {

    }

    public UserClass (String id, String name) {
        this.ID = id;
        this.userName = name;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public String getUserName() {
        return userName;
    }

   // @Override
    public Set<FeedItemClass> getBody() {
        return body;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setBody(Set<FeedItemClass> body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserClass userClass = (UserClass) o;
        return Objects.equals(ID, userClass.ID) &&
                Objects.equals(userName, userClass.userName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(ID, userName);
    }
}

package com.example.olga.vog;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

//This is a concrete implementation of the User interface. We are using the " @Expose" annotations to mark the fields
//have to be presented in the JSON object.

public class UserClass implements User, Serializable {
    //In some cases in is better to implement Parselable instead of Serializable to store Java objects in the intent and to
    //transfer between the components

    @Expose
    private String ID;

    @Expose
    private String userName;

    @Expose
    private Set<FeedItemClass> body;

    //Empty public constructor is required acording to Java documentation to use Gson to serialize and deserialize Java
    // and JSON objects
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
        //As we are going to process the collections of the objects of this class, we need to override equals
        // //and hashCode methods.
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserClass userClass = (UserClass) o;
        return Objects.equals(ID, userClass.ID);
    }

    @Override
    public int hashCode() {

        return Objects.hash(ID, userName);
    }
}

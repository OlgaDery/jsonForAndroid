package com.example.olga.vog;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestClasses {
  //  private static final String TAG = TestClasses.class.getSimpleName();

    @Test
    public void testJSON() {

        FeedItemClass item1 = new FeedItemClass(22, "video");
        item1.setMediaLocation("http:///");

        FeedItemClass item2 = new FeedItemClass(24, "video");
        item2.setMediaLocation("http:///");

        assertNotEquals(item1, item2);

        FeedItemClass item3 = new FeedItemClass(24, "text");
        item3.setText("bla");
        System.out.print(item3.toString());

        assertTrue(!item3.toString().contains("mediaLocation"));

        UserClass user1 = new UserClass("abc", "User1");
        Set<FeedItemClass> set = new HashSet<>();
        set.add(item1);
        set.add(item2);
        user1.setBody(set);
        UserClass user2 = new UserClass("abcd", "User1");

        assertNotEquals(user1, user2);
        Set<User> users = new HashSet<>();
        users.add(user1);
        users.add(user2);


    }
}
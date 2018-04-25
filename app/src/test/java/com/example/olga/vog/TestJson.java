package com.example.olga.vog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestJson {
  //  private static final String TAG = TestClasses.class.getSimpleName();

    @Test
    public void testJSON() {

        FeedItemClass item1 = new FeedItemClass(22, "video");
        item1.setMediaLocation("http:///");

        FeedItemClass item2 = new FeedItemClass(24, "text");
        item1.setMediaLocation("Hello");

        UserClass user1 = new UserClass("abc", "User1");
        Set<FeedItemClass> set = new HashSet<>();
        set.add(item1);
        set.add(item2);
        user1.setBody(set);
        UserClass user2 = new UserClass("abcd", "User2");
        Set<User> users = new HashSet<>();
        users.add(user1);
        users.add(user2);

        Gson gson = new GsonBuilder().
                excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(users);
        Type type = new TypeToken<List<UserClass>>() {}.getType();

        List<UserClass> fromJson = gson.fromJson(json, type);
        for (UserClass u : fromJson) {
            if (u.getUserName().equals("User1")) {
                assertTrue(u.getBody().size()== 2);
            }
        }
    }
}
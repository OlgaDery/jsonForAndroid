package com.example.olga.vog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TestActivity {

    Context appContext;
    ShowDataActivity activity;

    @Rule
    public final ActivityTestRule<ShowDataActivity> activityRule =
            new ActivityTestRule<>(ShowDataActivity.class);

    @Before
    public void setUp () {
        appContext  = InstrumentationRegistry.getTargetContext();
        activity = activityRule.getActivity();
    }


    @Test
    public void checkActivity() {
        // Checking the app workflow - how to app starts, calls the service, service method is parsing the json and returns
        //the result to ShowDataActivity. Here we are registering the same broadcast receiver to receive the data and assert
        //the size of the arraylist.

        BroadcastReceiver receiver = new BroadcastReceiver () {
            @Override
            public void onReceive(Context context, Intent intent) {
                ArrayList<FeedItemClass> items = (ArrayList<FeedItemClass>)
                        intent.getSerializableExtra(GetDataService.DATA);
                assertTrue(items.size()==2);
            };

        };
        IntentFilter intentFilter =
                new IntentFilter();
        intentFilter.addAction(GetDataService.DATA_PARSED);

        InstrumentationRegistry.getTargetContext().registerReceiver(receiver,
                intentFilter);

    }
}

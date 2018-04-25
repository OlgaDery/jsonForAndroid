package com.example.olga.vog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

public class ShowDataActivity extends AppCompatActivity {
    //This is a simple representation of the UI methods. In "onCreate()" method we are calling the IntentService to start
    //JSON parsing in a separate helper thread (what is the best practice for such operations). When JSON has been serialized to
    //Java objects, we are sending the broadcast with the List of FeedItemClass objects back to this Activity. Then the Items are
    //represented in the "displayFeed" method.

    private BroadcastReceiver receiver;

    private static final String TAG = ShowDataActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "enter onCreate(Bundle savedInstanceState)");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        //Creating the intent to start the service to parse JSON.
        Intent startIntent = new Intent(this, GetDataService.class);
        startIntent.setAction(GetDataService.START_PARSING);
        startService(startIntent);

        //Registering the receiver to get the list of Java classes back
        receiver = new BroadcastReceiver () {
            @Override
            public void onReceive(Context context, Intent intent) {
                //On receive should be called very rarely, onle the current location is significantly changed. In our case, it is calling
                //onle once
                Log.d(TAG, "enter onReceive(Context context, Intent intent)");

                //Getting the data from intent which is being broadcasted from the service
                if (intent.getSerializableExtra(GetDataService.DATA)!=null) {
                    ArrayList<FeedItemClass> items = (ArrayList<FeedItemClass>)
                            intent.getSerializableExtra(GetDataService.DATA);
                    displayFeed(items);
                }
                Log.d(TAG, "exit onReceive(Context context, Intent intent)");
        };
        };
        Log.d(TAG, "exit onCreate(Bundle savedInstanceState)");

    }

    @Override
    protected void onPause() {
        super.onPause();
        //register receiver
        this.unregisterReceiver(this.receiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //unregister receiver
        registerReceiver();
    }

    private void registerReceiver() {
        Log.d(TAG, "enter registerReceiver()");
        // Create an intent filter
        IntentFilter intentFilter =
                new IntentFilter();
        intentFilter.addAction(GetDataService.DATA_PARSED);

        // Register the receiver and the intent filter.
        registerReceiver(receiver,
                intentFilter);
        Log.d(TAG, "exit registerReceiver()");
    }


    private void displayFeed (ArrayList<FeedItemClass> items) {
        Log.d(TAG, "enter displayFeed (ArrayList<FeedItemClass> items)");
        for (FeedItemClass item: items) {
            Log.i(TAG, item.toString());
        }
        Log.d(TAG, "exit displayFeed (ArrayList<FeedItemClass> items)");

    }
}

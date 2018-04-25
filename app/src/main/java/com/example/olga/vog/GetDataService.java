package com.example.olga.vog;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class GetDataService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String START_PARSING = "START_PARSING";
    public static final String DATA_PARSED = "DATA_PARSED";
    public static final String DATA = "DATA";
    private static final String TAG = GetDataService.class.getSimpleName();

    public GetDataService() {

        super("GetDataService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //This method is required to handle the intent. Here we are calling the "parseData()" method to parse the JSON.
        Log.d(TAG, "enter onHandleIntent(Intent intent)");
        if (intent != null) {
            final String action = intent.getAction();
            if (START_PARSING.equals(action)) {
                parseData();
            }
        }
        Log.d(TAG, "exit onHandleIntent(Intent intent)");
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void parseData() {
        Log.d(TAG, "enter parseData()");
        List<FeedItem> items = new ArrayList<>();

        //To parse JSON, we are using Gson library which allows to map JSONs to Java objects. To avoid adding unnecessary data,
        //we are using excludeFieldsWithoutExposeAnnotation options.
        Gson gson = new GsonBuilder().
                excludeFieldsWithoutExposeAnnotation().create();

        Type type = new TypeToken<List<UserClass>>() {}.getType();
        List<UserClass> fromJson = gson.fromJson(JSON.json, type);

        //Getting Set<FeedItemClass> (as currently they are the properties of UserClass objects) and storing them to show up
        //on the front end
        for (UserClass u : fromJson) {
            if (u.getBody()!=null) {
                items.addAll(u.getBody());
            }
        }
        //Creating the intent to send the data back to the activity
        Intent intent = new Intent();
        intent.setAction(DATA_PARSED);
        intent.putExtra(DATA, (Serializable) items);
        sendBroadcast(intent);

        Log.d(TAG, "exit parseData()");

    }
}

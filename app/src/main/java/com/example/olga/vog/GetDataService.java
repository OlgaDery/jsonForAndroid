package com.example.olga.vog;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

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
    private static final String START_PARSING = "START_PARSING";
    private static final String DATA_PARSED = "DATA_PARSED";
    private static final String DATA = "DATA";

    public GetDataService() {

        super("GetDataService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (START_PARSING.equals(action)) {

                parseData();
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void parseData() {
        // TODO: Handle action Foo
        List<FeedItem> items = new ArrayList<>();
        Gson gson = new GsonBuilder().
                excludeFieldsWithoutExposeAnnotation().create();
        Type type = new TypeToken<List<UserClass>>() {}.getType();
        List<UserClass> fromJson = gson.fromJson(JSON.json, type);
        for (UserClass u : fromJson) {
            if (u.getBody()!=null) {
                items.addAll(u.getBody());
            }
        }
        Intent intent = new Intent();
        intent.setAction(DATA_PARSED);
        intent.putExtra(DATA, (Parcelable)items);
        sendBroadcast(intent);

    }

}

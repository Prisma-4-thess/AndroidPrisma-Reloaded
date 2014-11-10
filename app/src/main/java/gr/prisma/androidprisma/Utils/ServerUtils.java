package gr.prisma.androidprisma.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaActionSound;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import gr.prisma.androidprisma.Fragments.ArrayListFragment;
import gr.prisma.androidprisma.MainActivity;

/**
 * Created by dimitris on 2/11/2014.
 * All functions to communicate with server
 */
public class ServerUtils {

    private String curQuery;
    private Future<JsonObject> loading;
    private Context context;
    private final static String prefsUsername = "mUsername";

    public ServerUtils(Context context) {
        this.context = context;
    }

    public String getCurQuery() {
        return curQuery;
    }

    public void cancel(){
        loading.cancel();
    }

    public void setCurQuery(String curQuery) {
        this.curQuery = curQuery;
    }

    /**
     * Function for querying site for ada
     *
     */
    public void loadDecisions(final ArrayListFragment fragment) {
        if (loading != null && !loading.isDone() && !loading.isCancelled())
            return;
        fragment.clearAdapterFragment();
        loading = Ion.with(context)
                .load(String.format("https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=%s&imgsz=medium", Uri.encode(curQuery)))
                .addHeader("Accept", "application/json")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        Log.d("SERVER", "completed");
                        try {
                            if (e != null)
                                throw e;
//                            Log.d("SERVER",result.toString());
                            JsonArray results = result.getAsJsonObject("responseData").getAsJsonArray("results");
                            for (int i = 0; i < results.size(); i++) {
                                Log.d("SERVER", results.get(i).getAsJsonObject().get("title").getAsString());
                                fragment.addToAdapterFragment(results.get(i).getAsJsonObject().get("title").getAsString());
                            }
                            fragment.readyToShow();
                        } catch (Exception ex) {
                            Log.d("SERVER", ex.toString());
                        }
                    }
                });
    }


    /**
     * Function that covers user's login
     *
     * @param username User's username
     * @param password User's password
     * @return True if login was successful, false otherwise
     */
    public boolean login(String username, String password) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(prefsUsername, username);
        editor.apply();
        return true;
    }

    /**
     * Function that covers user's register
     *
     * @param username User's username
     * @param password User's password
     * @param email    User's email
     * @return True if register was successful, false otherwise
     */
    public boolean register(String username, String password, String email) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(prefsUsername, username);
        editor.apply();
        return true;
    }



}


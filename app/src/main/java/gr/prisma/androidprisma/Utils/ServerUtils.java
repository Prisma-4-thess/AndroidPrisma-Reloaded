package gr.prisma.androidprisma.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.Future;

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

    public void setCurQuery(String curQuery) {
        this.curQuery = curQuery;
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


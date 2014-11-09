package gr.prisma.androidprisma;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.koushikdutta.ion.Ion;

/**
 * Created by dimitris on 29/10/2014.
 * The Application Class for the Beer App
 */
public class PrismaApplication extends Application {

    private static final String SETTING_FIRST_RUN = "first_run", SETTING_RATED = "is_rated";
    private static SharedPreferences prefs;
    private static SharedPreferences.Editor editor;

    @Override
    public void onCreate() {
        super.onCreate();
        Ion.getDefault(this).configure().setLogging("ion-sample", Log.DEBUG);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }

    public static boolean isRated() {
        return prefs.getBoolean(SETTING_RATED, false);
    }

    public static void setRated(boolean v) {
        editor.putBoolean(SETTING_RATED, v);
        editor.apply();
    }
    public static boolean isFirstRun() {
        return prefs.getBoolean(SETTING_FIRST_RUN, true);
    }

    public static void setFirstRun(boolean b) {
        editor.putBoolean(SETTING_FIRST_RUN, b);
        editor.apply();

    }

}

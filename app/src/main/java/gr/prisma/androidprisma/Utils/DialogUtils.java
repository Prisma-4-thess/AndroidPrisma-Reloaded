package gr.prisma.androidprisma.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

import java.util.Random;

import gr.prisma.androidprisma.PrismaApplication;
import gr.prisma.androidprisma.R;

/**
 * Created by spiros on 11/2/14.
 */
public class DialogUtils{


    private static final String TAG = DialogUtils.class.getSimpleName();

    public static void showRateDialog(final Activity currentActivity){
        if (PrismaApplication.isFirstRun() || PrismaApplication.isRated()) return;
        if ((new Random()).nextInt(10) < 2) {
            AlertDialog.Builder builder = new AlertDialog.Builder(currentActivity);
            // Add the buttons
            builder.setTitle(currentActivity.getResources().getString(R.string.please_rate));
            builder.setMessage(R.string.please_rate_text);
            builder.setIcon(android.R.drawable.btn_star);
            builder.setCancelable(true);
            builder.setPositiveButton(R.string.yeaaah, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("market://details?id=" + currentActivity.getApplication().getPackageName()));
                    currentActivity.startActivity(intent);
                    PrismaApplication.setRated(true);
                }
            });
            builder.setNegativeButton(R.string.naaaah, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                    PrismaApplication.setRated(false);
                }
            });
            AlertDialog dialog = builder.create();

            dialog.show();
        }
    }

}

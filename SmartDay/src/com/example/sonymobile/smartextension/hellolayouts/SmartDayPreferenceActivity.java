package com.example.sonymobile.smartextension.hellolayouts;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;


/**
 * The sample control preference activity handles the preferences for the sample
 * control extension.
 */
public class SmartDayPreferenceActivity extends PreferenceActivity {

    private static final int DIALOG_READ_ME = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;

        switch (id) {
            case DIALOG_READ_ME:
                dialog = createReadMeDialog();
                break;
            default:
                Log.w(SmartDayExtensionService.LOG_TAG, "Not a valid dialog id: " + id);
                break;
        }

        return dialog;
    }

    /**
     * Create the Read me dialog.
     *
     * @return the Dialog
     */
    private Dialog createReadMeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.preference_option_read_me_txt)
                .setTitle(R.string.preference_option_read_me)
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton(android.R.string.ok, new OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        return builder.create();
    }

}

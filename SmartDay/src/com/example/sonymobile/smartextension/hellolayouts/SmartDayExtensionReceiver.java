package com.example.sonymobile.smartextension.hellolayouts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * The extension receiver receives the extension intents and starts the
 * extension service when they arrive.
 */
public class SmartDayExtensionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {

        Log.d(SmartDayExtensionService.LOG_TAG, "onReceive: " + intent.getAction());
        intent.setClass(context, SmartDayExtensionService.class);
        context.startService(intent);
    }
}

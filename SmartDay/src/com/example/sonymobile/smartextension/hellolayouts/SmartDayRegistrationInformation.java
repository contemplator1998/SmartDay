package com.example.sonymobile.smartextension.hellolayouts;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.sonyericsson.extras.liveware.aef.registration.Registration;
import com.sonyericsson.extras.liveware.extension.util.ExtensionUtils;
import com.sonyericsson.extras.liveware.extension.util.registration.RegistrationInformation;

import java.util.UUID;

/**
 * Provides information needed during extension registration.
 */
public class SmartDayRegistrationInformation extends RegistrationInformation {

    final Context mContext;
    private String extensionKey;
    private static final String EXTENSION_KEY_PREF = "EXTENSION_KEY_PREF";


    /**
     * Creates a control registration object.
     *
     * @param context The context.
     */
    protected SmartDayRegistrationInformation(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context == null");
        }
        mContext = context;
    }

    @Override
    public int getRequiredControlApiVersion() {
        // This extension supports all accessories from Control API level 1 and
        // up.
        return 1;
    }

    @Override
    public int getTargetControlApiVersion() {
        return 2;
    }

    @Override
    public int getRequiredSensorApiVersion() {
        return API_NOT_REQUIRED;
    }

    @Override
    public int getRequiredNotificationApiVersion() {
        return API_NOT_REQUIRED;
    }

    @Override
    public int getRequiredWidgetApiVersion() {
        return API_NOT_REQUIRED;
    }

    /**
     * Return the extension registration information. Specify the properties of
     * the extension that will be used when it is registered.
     *
     * @return The registration configuration.
     */
    @Override
    public ContentValues getExtensionRegistrationConfiguration() {
        String iconHostapp = ExtensionUtils.getUriString(mContext, R.drawable.ic_launcher);
        String iconExtension = ExtensionUtils.getUriString(mContext, R.drawable.ic_launcher);
        String iconExtension48 = ExtensionUtils.getUriString(mContext, R.drawable.ic_launcher);

        ContentValues values = new ContentValues();

        values.put(Registration.ExtensionColumns.CONFIGURATION_ACTIVITY,
                SmartDayPreferenceActivity.class.getName());
        values.put(Registration.ExtensionColumns.CONFIGURATION_TEXT,
                mContext.getString(R.string.configuration_text));
        values.put(Registration.ExtensionColumns.NAME, mContext.getString(R.string.extension_name));
        values.put(Registration.ExtensionColumns.EXTENSION_KEY, getExtensionKey());
        values.put(Registration.ExtensionColumns.HOST_APP_ICON_URI, iconHostapp);
        values.put(Registration.ExtensionColumns.EXTENSION_ICON_URI, iconExtension);
        values.put(Registration.ExtensionColumns.EXTENSION_48PX_ICON_URI, iconExtension48);
        values.put(Registration.ExtensionColumns.NOTIFICATION_API_VERSION,
                getRequiredNotificationApiVersion());
        values.put(Registration.ExtensionColumns.PACKAGE_NAME, mContext.getPackageName());

        return values;
    }

    @Override
    public boolean isDisplaySizeSupported(int width, int height) {
        return (width == SmartDayControl.getSupportedControlWidth(mContext) && height == SmartDayControl
                .getSupportedControlHeight(mContext));
    }

    /**
     * A basic implementation of getExtensionKey
     * Returns and saves a random string based on UUID.randomUUID()
     *
     * Note that this implementation doesn't guarantee random numbers on Android 4.3 and older. See <a href="https://android-developers.blogspot.com/2013/08/some-securerandom-thoughts.html">https://android-developers.blogspot.com/2013/08/some-securerandom-thoughts.html</a>
     *
     * @return A saved key if it exists, otherwise a randomly generated one.
     * @see com.sonyericsson.extras.liveware.extension.util.registration.RegistrationInformation#getExtensionKey()
     */
    @Override
    public synchronized String getExtensionKey() {
        if (TextUtils.isEmpty(extensionKey)) {
            // Retrieve key from preferences
            SharedPreferences pref = mContext.getSharedPreferences(EXTENSION_KEY_PREF,
                    Context.MODE_PRIVATE);
            extensionKey = pref.getString(EXTENSION_KEY_PREF, null);
            if (TextUtils.isEmpty(extensionKey)) {
                // Generate a random key if not found
                extensionKey = UUID.randomUUID().toString();
                pref.edit().putString(EXTENSION_KEY_PREF, extensionKey).commit();
            }
        }
        return extensionKey;
    }
}

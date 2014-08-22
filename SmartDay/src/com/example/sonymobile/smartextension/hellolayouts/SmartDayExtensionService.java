package com.example.sonymobile.smartextension.hellolayouts;

import android.os.Handler;
import android.util.Log;

import com.sonyericsson.extras.liveware.extension.util.ExtensionService;
import com.sonyericsson.extras.liveware.extension.util.control.ControlExtension;
import com.sonyericsson.extras.liveware.extension.util.registration.DeviceInfoHelper;
import com.sonyericsson.extras.liveware.extension.util.registration.RegistrationInformation;

/**
 * The Sample Extension Service handles registration and keeps track of all
 * controls on all accessories.
 */
public class SmartDayExtensionService extends ExtensionService {

    public static final String LOG_TAG = "HelloLayouts";

    public SmartDayExtensionService() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(SmartDayExtensionService.LOG_TAG, "onCreate: HelloLayoutsExtensionService");
    }

    @Override
    protected RegistrationInformation getRegistrationInformation() {
        return new SmartDayRegistrationInformation(this);
    }

    @Override
    protected boolean keepRunningWhenConnected() {
        return false;
    }

    @Override
    public ControlExtension createControlExtension(String hostAppPackageName) {
        // First we check if the host application API level and screen size
        // is supported by the extension.
        boolean advancedFeaturesSupported = DeviceInfoHelper.isSmartWatch2ApiAndScreenDetected(
                this, hostAppPackageName);
        if (advancedFeaturesSupported) {
            return new SmartDayControl(hostAppPackageName, this, new Handler());
        } else {
            throw new IllegalArgumentException("No control for: " + hostAppPackageName);
        }
    }
}

package com.mbhgroup.cricketasiacup2018;

/**
 * Created by appsd on 6/6/2018.
 */

import com.onesignal.OneSignal;


public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }
}
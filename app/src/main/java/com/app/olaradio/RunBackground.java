package com.app.olaradio;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class RunBackground extends IntentService {

    public RunBackground() {
        super("RunBackground");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SystemClock.sleep(5000);
    }
}

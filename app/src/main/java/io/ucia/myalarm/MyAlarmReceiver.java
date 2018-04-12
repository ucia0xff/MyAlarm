package io.ucia.myalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyAlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent is = new Intent(context, MyAlarmService.class);
        context.startService(is);
    }
}

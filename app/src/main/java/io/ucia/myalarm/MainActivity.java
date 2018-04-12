package io.ucia.myalarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends Activity {
    private Switch switch1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        switch1 = findViewById(R.id.switch1);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    startAlarm();
                } else {
                    stopAlarm();
                }
            }
        });
    }
    public void startAlarm() {
        int waitTime = 5  * 1000;
        long nextTime =  + System.currentTimeMillis() + waitTime;
        Intent i = new Intent(MainActivity.this, MyAlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this, 0, i, 0);
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.setExact(AlarmManager.RTC_WAKEUP, nextTime, pi);
    }

    public void stopAlarm() {
        Intent i = new Intent(MainActivity.this, MyAlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this, 0, i, 0);
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.cancel(pi);
        Intent is = new Intent(MainActivity.this, MyAlarmService.class);
        stopService(is);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

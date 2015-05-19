package edu.washington.xyju.quizdroidpart4;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class MainActivity extends ActionBarActivity {

    private PendingIntent pendingIntent;
    private String url;
    private String interval;

    private static final int SETTINGS_RESULT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.user_preferences:
                Intent i = new Intent(getApplicationContext(), UserSettingActivity.class);
                startActivityForResult(i, SETTINGS_RESULT);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==SETTINGS_RESULT)
        {
            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

            url = sharedPrefs.getString("prefUserURL", "NOURL");
            interval = sharedPrefs.getString("prefUserFrequency","0");

            Button download = (Button) findViewById(R.id.download);
            download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
                    alarmIntent.putExtra("URL", url);
                    pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, 0);
                    start();

                    Button stop = (Button) findViewById(R.id.cancel);
                    stop.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cancel();
                        }
                    });

                }
            });
        }

    }

    public void start() {

        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int iMinutes= Integer.parseInt(interval)*60*1000;

        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), iMinutes, pendingIntent);
        Toast.makeText(this, "Download Begin", Toast.LENGTH_SHORT).show();
    }

    public void cancel() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show();
    }
}

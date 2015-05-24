package edu.washington.xyju.quizdroidpart5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by xyju on 15/5/23.
 */
public class AlarmReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        String url =intent.getStringExtra("URL");
        Toast.makeText(context, url, Toast.LENGTH_SHORT).show();

        Intent downloadServiceIntent = new Intent(context,DownloadService.class);
        downloadServiceIntent.putExtra("URL",url);
        context.startService(downloadServiceIntent);

    }
}

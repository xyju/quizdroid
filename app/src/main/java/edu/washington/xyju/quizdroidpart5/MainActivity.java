package edu.washington.xyju.quizdroidpart5;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.ParcelFileDescriptor;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private PendingIntent pendingIntent;
    private String url;
    private String interval;
    private String json,jsonToPrint;

    private static final int SETTINGS_RESULT = 1;

    private DownloadManager downloadManager;
    private long downloadReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filter = new IntentFilter();
        filter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        registerReceiver(receiver, filter);
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

        //boolean isEnabled = Settings.System.getInt(this.getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 0) == 1;

        if (requestCode == SETTINGS_RESULT) {
            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

            //mode = sharedPrefs.getBoolean("DefAirplaneMode",false);

            url = sharedPrefs.getString("prefUserURL", "NOURL");
            interval = sharedPrefs.getString("prefUserFrequency", "0");

            Button download = (Button) findViewById(R.id.download);
            download.setOnClickListener(this);

            Button stop = (Button) findViewById(R.id.cancel);
            stop.setOnClickListener(this);

        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.download:

                boolean networkStatus = isNetworkConnected();
                boolean isEnabled = Settings.System.getInt(this.getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 0) == 1;

                if(isEnabled == true){


                    setAirplaneMode();
                }else{

                    if(networkStatus == true){
                        Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
                        alarmIntent.putExtra("URL", url);
                        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, 0);
                        start();
                    }else{
                        Toast.makeText(getApplicationContext(), "You have no signal.",
                                Toast.LENGTH_LONG).show();
                        Log.i("NetworkAccess", "No signal!");
                    }
                }
                break;
            case R.id.cancel:
                cancel();
                break;
            default:
                break;
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

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);

            if(DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)){
                downloadReference = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID,0);

                if(downloadReference != 0){
                    DownloadManager.Query myDownloadQuery = new DownloadManager.Query();

                    myDownloadQuery.setFilterById(downloadReference);
                    Cursor cursor = downloadManager.query(myDownloadQuery);
                    if(cursor.moveToFirst()){
                        Toast.makeText(context, checkStatus(cursor), Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    };


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = cm.getActiveNetworkInfo();
        if (network == null) {
            return false;
        } else
            return true;
    }

    private String checkStatus(Cursor cursor) {

        int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
        int status = cursor.getInt(columnIndex);

        String statusText = "";
        String checkStatus;

        switch(status){
            case DownloadManager.STATUS_FAILED:
                statusText = "STATUS_FAILED";
                setDownloadRetry();
                break;
            case DownloadManager.STATUS_PAUSED:
                statusText = "STATUS_PAUSED";
                break;
            case DownloadManager.STATUS_PENDING:
                statusText = "STATUS_PENDING";
                break;
            case DownloadManager.STATUS_RUNNING:
                statusText = "STATUS_RUNNING";
                break;
            case DownloadManager.STATUS_SUCCESSFUL:
                statusText = "STATUS_SUCCESSFUL";
                ParcelFileDescriptor file;


                try {
                    file = downloadManager.openDownloadedFile(downloadReference);
                    FileInputStream fis = new FileInputStream(file.getFileDescriptor());

                    json = readJSONFile(fis);

                    writeToFile(json);

                    FileInputStream fileInputStream = openFileInput("questions.json");
                    jsonToPrint = readJSONFile(fileInputStream);

                    TextView result = (TextView) findViewById(R.id.result);
                    result.setText(jsonToPrint);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }


        checkStatus = statusText;
        return checkStatus;
    }

    public void setAirplaneMode(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(false)
                .setTitle("Airplane Mode Setting")
                .setMessage("Your phone is in airplane mode. Do you want to turn off airplane mode?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS));
                    }
                })
                .setNeutralButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void setDownloadRetry(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(false)
                .setTitle("Download")
                .setMessage("Download failed. Do you want to retry?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        start();
                        dialog.dismiss();
                    }
                })
                .setNeutralButton("Quit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public String readJSONFile(InputStream inputStream) throws IOException {

        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();
        return new String(buffer, "UTF-8");

    }

    public void writeToFile(String data) {
        try {
            Log.i("MainActivity", "writing downloaded to file");

            File file = new File(getFilesDir().getAbsolutePath(), "questions.json");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data.getBytes());
            fos.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

}

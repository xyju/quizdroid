package edu.washington.xyju.quizdroidpart4;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by xyju on 15/5/19.
 */
public class UserSettingActivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences_setting);

        //Intent back = new Intent(UserSettingActivity.this, MainActivity.class);
        //startActivity(back);


    }
}

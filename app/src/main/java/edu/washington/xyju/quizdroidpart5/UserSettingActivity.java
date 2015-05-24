package edu.washington.xyju.quizdroidpart5;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by xyju on 15/5/23.
 */
public class UserSettingActivity extends PreferenceActivity{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.user_settings);

    }
}

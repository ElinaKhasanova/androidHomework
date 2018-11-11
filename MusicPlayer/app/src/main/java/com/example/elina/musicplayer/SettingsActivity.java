package com.example.elina.musicplayer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class SettingsActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String currentTheme = sharedPref.getString("pref_theme","pref_theme_default");
        int themeId = R.style.Theme1;
        switch (currentTheme) {
            case ("Theme1"):
                themeId = R.style.Theme1;
                break;
            case ("Theme2"):
                themeId = R.style.Theme2;
                break;
            case ("Theme3"):
                themeId = R.style.Theme3;
                break;
            default: themeId = R.style.Theme1;
        }
        setTheme(themeId);
    }
}

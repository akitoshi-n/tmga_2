package com.example.test175memetest2;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Yu on 2018/08/25.
 */

public class MyPreferencesActivity {

    public boolean setCurrentBGM(Context context, int id){
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("PreferencesData", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("currentbgm", id);
            editor.apply();
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public int getCurrentBGM(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("PreferencesData", MODE_PRIVATE);
        int id = sharedPreferences.getInt("currentbgm", 0);
        return id;
    }

    public boolean setKubifuriInstrument(Context context, int id){
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("PreferencesData", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("kubifuriinstrument", id);
            editor.apply();
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public int getKubifuriInstrument(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("PreferencesData", MODE_PRIVATE);
        int id = sharedPreferences.getInt("kubifuriinstrument", 0);
        return id;
    }

    public boolean setShisenInstrument(Context context, int id){
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("PreferencesData", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("shiseninstrument", id);
            editor.apply();
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public int getShisenInstrument(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("PreferencesData", MODE_PRIVATE);
        int id = sharedPreferences.getInt("shiseninstrument", 0);
        return id;
    }
}

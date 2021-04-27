package com.example.kabisa.registre.retrofitutil;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.kabisa.R;

public class AppConfig {
    private Context context;
    private SharedPreferences sharedPreferences;

    public AppConfig(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(
                context.getString(R.string.pref_file_key),Context.MODE_PRIVATE);
    }
    public boolean isUserLogin(){
        return sharedPreferences.getBoolean(context.getString(R.string.pref_is_user_login),false);
    }
    public void updateUserLginStatus(boolean status){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_is_user_login),status);
        editor.apply();
    }
   public void savaNameOfUser(String name){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_name_of_user), name);
        editor.apply();
   }
   public String getNaneOfUser(){
        return sharedPreferences.getString(context.getString(
                R.string.pref_name_of_user
        ),context.getString(R.string._nconnue));
   }
}

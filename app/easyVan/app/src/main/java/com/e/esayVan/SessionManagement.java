package com.e.esayVan;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME="session";
    String KEY_USERNAME="session_user";
    String KEY_ROLE="session_role";

    public SessionManagement(Context context){
        //only this application can access this shared preference
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    public void saveSession(User user){
        //save session of user whenever user  is logged in
        String userRole = user.getUserRole();
        String userName=user.getUsername();
        editor.putString(KEY_ROLE,userRole).commit();
        editor.putString(KEY_USERNAME,userName).commit();
        //editor.apply();
    }

    public String getSession(){
        //return user id whose session is saved
        return sharedPreferences.getString(KEY_USERNAME,null);
    }

    public void removeSession(){
        editor.putString(KEY_USERNAME,null).commit();
        editor.putString(KEY_ROLE,null).commit();
        //editor.clear();
        //editor.apply();
    }
}

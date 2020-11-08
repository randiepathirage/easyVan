package com.e.esayVan;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME="session";
    String SESSION_KEY="session_user";

    public SessionManagement(Context context){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    public void saveSession(User user){
            //save session of user whenever user  is logged in
    }

    public String getSession(){

        return null;
    }

}

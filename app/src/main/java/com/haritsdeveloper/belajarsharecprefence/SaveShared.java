package com.haritsdeveloper.belajarsharecprefence;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveShared {
    private static final String USER_SHARED = "user_shared";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String AGGRE = "aggre";
    private static final String KELAMIN = "kelamin";
    private  static final String LOGIN = "statu_login";

    private final SharedPreferences preferences;

    SaveShared (Context context){
        preferences = context.getSharedPreferences(USER_SHARED,Context.MODE_PRIVATE);
    }

    public void setUser(UserModel value){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(NAME,value.getNama());
        editor.putString(EMAIL,value.getEmai());
        editor.putString(PASSWORD,value.getPassword());
        editor.putString(AGGRE,value.getAggre());
        editor.putString(KELAMIN,value.getKelamin());
        editor.putBoolean(LOGIN,value.isStatusLogin());
        editor.apply();
    }

    UserModel getUser(){
        UserModel model = new UserModel();
        model.setNama(preferences.getString(NAME,""));
        model.setEmai(preferences.getString(EMAIL,""));
        model.setPassword(preferences.getString(PASSWORD,""));
        model.setKelamin(preferences.getString(KELAMIN,""));
        model.setAggre(preferences.getString(AGGRE,""));
        model.setStatusLogin(preferences.getBoolean(LOGIN,false));
        return model;
    }
}

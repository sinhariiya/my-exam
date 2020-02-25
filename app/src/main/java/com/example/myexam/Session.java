package com.example.myexam;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor edit;
    Context context;

    public Session(Context context){
        this.context=context;
        sharedPreferences=context.getSharedPreferences("UserSession",Context.MODE_PRIVATE);
        edit=sharedPreferences.edit();
    }

    public void setLoggedIn(Boolean status){
        edit.putBoolean("Status",status);
        edit.commit();
    }

    public boolean checkLoggedIn(){

        return sharedPreferences.getBoolean("Status",false);
    }
    public void setName(String name) {
        edit.putString("Name",name);
        edit.commit();
    }
    public String getName() {
        return sharedPreferences.getString("Name","NOT FOUND");
    }
    public void setUsername(String username) {
        edit.putString("Username",username);
        edit.commit();
    }
    public String getUsername() {
        return sharedPreferences.getString("Username","NOT_FOUND");
    }


    public void logout(){
        edit.clear();
        edit.commit();
    }
}



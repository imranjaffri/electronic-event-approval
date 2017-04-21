package com.example.choudry.electroniceventapproval.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.choudry.electroniceventapproval.data.User;

/**
 * Created by maqsood on 15/04/2017.
 */

public class SharedPreferenceUtils {


    private static String preferenceName = "appAndroid.db";

    private static String USER_EMAIL = "email";
    private static String USER_ID = "user_id";
    private static String USER_TYPE = "type";

    private static SharedPreferences getPreference(Activity activity) {
        return activity.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
    }

    public static void saveLoggedUser(Activity activity, User user) {
        SharedPreferences.Editor editor = getPreference(activity).edit();
        editor.putString(USER_EMAIL, user.getEmail());
        editor.putString(USER_ID, user.getUser_id());
        editor.putString(USER_TYPE, user.getType());
        editor.apply();

    }

    public static User getLoggedUser(Activity activity) {
        SharedPreferences preferences = getPreference(activity);
        User user = new User();
        user.setEmail(preferences.getString(USER_EMAIL, null));
        user.setUser_id(preferences.getString(USER_ID, "00"));
        user.setType(preferences.getString(USER_TYPE, null));
        return user;
    }

}

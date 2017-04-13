package com.example.choudry.electroniceventapproval.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by ft on 4/13/2017.
 */

public class CommonUtils {


    public static void showToast(String message, Activity activity) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
    }
}

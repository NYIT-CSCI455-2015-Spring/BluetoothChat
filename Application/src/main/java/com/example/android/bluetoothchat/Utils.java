package com.example.android.bluetoothchat;

import android.app.Activity;
import android.content.Intent;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Utils {

    private static int sTheme;
    public final static int THEME_DEFAULT = 0;
    public final static int THEME_WHITE = 1;
    public final static int THEME_BLUE = 2;
    public static Calendar calendar = new GregorianCalendar();

    /**
     * Set the theme of the Activity and restart it by creating a new Activity of the same type
     */

    public static void changeToTheme(Activity activity, int theme) {

        sTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }

    /**
     * Set the theme of the activity according to the configuration
     */

    public static void onActivityCreateSetTheme(Activity activity) {

        switch (sTheme) {

            default:
            case THEME_DEFAULT:
                activity.setTheme(R.style.FirstTheme);
                break;
            case THEME_WHITE:
                activity.setTheme(R.style.SecondTheme);
                break;
            case THEME_BLUE:
                activity.setTheme(R.style.ThirdTheme);
                break;
        }
    }

    public static String getTimeStamp() {
        Date now = new Date();
        calendar.setTime(now);

        String timestamp = null;
        timestamp = calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DATE) + "-" + calendar.get(Calendar.YEAR) + " | "
                + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND) + " " + calendar.get(Calendar.AM_PM);
        return timestamp;
    }
}

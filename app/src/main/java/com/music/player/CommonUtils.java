package com.music.player;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Harjot on 18-Jan-17.
 */

public class CommonUtils {

    public static int getStatusBarHeight(Context ctx) {
        int result = 0;
        int resourceId = ctx.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = ctx.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int getNavBarHeight(Context ctx) {
        int result = 0;
        int resourceId = ctx.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = ctx.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int dpTopx(int dp, Context ctx) {
        DisplayMetrics displayMetrics = ctx.getResources().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density) + 0.5);
    }

    public static boolean hasNavBar(Context ctx) {
        int id = ctx.getResources().getIdentifier("config_showNavigationBar", "bool", "android");
        return id > 0 && ctx.getResources().getBoolean(id);
    }

    public static int getDarkColor(int color) {
        int darkColor = 0;

        int r = Math.max(Color.red(color) - 25, 0);
        int g = Math.max(Color.green(color) - 25, 0);
        int b = Math.max(Color.blue(color) - 25, 0);

        darkColor = Color.rgb(r, g, b);

        return darkColor;
    }

    public static void hideKeyboard(Context ctx) {
        View view = ((Activity) ctx).getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static String getMetaData(@NonNull Context context, @NonNull String key) {
        ApplicationInfo appInfo = null;
        try {
            appInfo = context.getApplicationContext()
                    .getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            if (appInfo != null && appInfo.metaData != null) {
                String serverName = appInfo.metaData.getString(key);
                return serverName;
            } else {
                Log.e("music lark","需要在AndroidManifest.xml中配置WebviewUrl meta数据");
                return "";
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }



}

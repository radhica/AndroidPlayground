package com.firoze.helpers;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by firozerakib on 1/19/15.
 * Helpers - a bunch of static Helper methods
 */
public class Helpers {

    public static boolean isLollipop() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return true;
        } else {
            return false;
        }
    }

    public static String getZeroPaddedNum(int num) {
        String str;

        if (num < 10 && num >= 0) {
            str = "0" + num;
        } else {
            str = String.valueOf(num);
        }

        return str;
    }

    public static String convertMaskedPhone(String phone) {
        StringBuilder phoneNumber = new StringBuilder(phone);
        for (int i = 0; i < phoneNumber.length(); i++) {
            switch(Character.toLowerCase(phoneNumber.charAt(i))) {
                case 'a': case 'b': case 'c':
                    phoneNumber.setCharAt(i, '2');
                    break;
                case 'd': case 'e': case 'f':
                    phoneNumber.setCharAt(i, '3');
                    break;
                case 'g': case 'h': case 'i':
                    phoneNumber.setCharAt(i, '4');
                    break;
                case 'j': case 'k': case 'l':
                    phoneNumber.setCharAt(i, '5');
                    break;
                case 'm': case 'n': case 'o':
                    phoneNumber.setCharAt(i, '6');
                    break;
                case 'p': case 'q': case 'r': case 's':
                    phoneNumber.setCharAt(i, '7');
                    break;
                case 't': case 'u': case 'v':
                    phoneNumber.setCharAt(i, '8');
                    break;
                case 'w': case 'x': case 'y': case 'z':
                    phoneNumber.setCharAt(i, '9');
                    break;
                case '+':
                    phoneNumber.setCharAt(i, '0');
                    break;
            }
        }
        return phoneNumber.toString();
    }

    public static BitmapDrawable createDrawableFromView(Context context, View v) {
        Bitmap b = Bitmap.createBitmap(v.getLayoutParams().width, v.getLayoutParams().height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
        v.draw(c);
        return new BitmapDrawable(context.getResources(), b);
    }

    public static Bitmap decodeBitmapFromBase64(String base64input) {
        byte bytes[] = Base64.decode(base64input, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    public static String encodeBitmapTobase64(Bitmap bitmap) {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, bytearrayoutputstream);
        return Base64.encodeToString(bytearrayoutputstream.toByteArray(), Base64.DEFAULT);
    }

    public static String getSHA1ForString(String str) {
        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA1");
            byte[] result = mDigest.digest(str.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < result.length; i++) {
                sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static float convertDPtoPX(Context context, float dpVal) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, context.getResources().getDisplayMetrics());
    }

    public static float convertPXtoDP(Context context, float pxVal) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pxVal, context.getResources().getDisplayMetrics());
    }

    public static boolean isPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    public static boolean isLandscape(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    public static boolean isNetworkAvailable(Context context) {
        NetworkInfo networkinfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return ((networkinfo != null) && (networkinfo.isConnectedOrConnecting()));
    }

    public static boolean isCellularDataAvailable(Context context) {
        return ((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_MOBILE;
    }

    public static boolean isWifiAvailable(Context context) {
        return ((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
    }

    public static boolean isLocationServicesAvailable(Context context) {
        LocationManager locationmanager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (locationmanager != null) {
            boolean isGPSAvailable = locationmanager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            boolean isNetworkLocationAvailable = locationmanager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            return isGPSAvailable || isNetworkLocationAvailable;
        } else {
            return false;
        }
    }

    public static boolean isPointInView(View view, int x, int y) {
        int location[] = new int[2];

        view.getLocationOnScreen(location);

        return new Rect(location[0], location[1], 100 + view.getWidth(), 100 + view.getHeight()).contains(x, y);
    }

    public static void openBrowser(Context context, String url) {
        if (!TextUtils.isEmpty(url)) {
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://" + url;
            }
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } else {
            Log.e("Helpers#openBrowser", "Something isn't right about the URL passed");
        }
    }

    public static String toCamelCase(String s){
        String[] parts = s.split(" ");
        String camelCaseString = "";
        for (String part : parts) {
            camelCaseString = camelCaseString + " " + toProperCase(part);
        }
        return camelCaseString;
    }

    private static String toProperCase(String s) {
        return s.trim().substring(0, 1).toUpperCase(Locale.getDefault()) + s.substring(1).toLowerCase(Locale.getDefault());
    }
}

package com.nabilhachicha.kc.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.nabilhachicha.kc.model.POI;

/**
 * Created by voicuklein on 25/04/15.
 */
public class MapUtils {

    public static Uri getMapIntentUri(POI poi) {
        StringBuilder builder = new StringBuilder()
                .append("geo:")
                .append(poi.getLocation().latitude)
                .append(poi.getLocation().longitude)
                .append("?q=")
                .append(poi.getName());

        return Uri.parse(builder.toString());
    }

    public static void startMapIntent(POI poi, Activity activity){
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, MapUtils.getMapIntentUri(poi));
        mapIntent.setPackage("com.google.android.apps.maps");
        activity.startActivity(mapIntent);
    }
}

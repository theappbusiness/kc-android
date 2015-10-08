package com.theappbusiness.kc.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.theappbusiness.kc.model.Venue;

/**
 * Created by voicuklein on 25/04/15.
 */
public class MapUtils {

    public static Uri getMapIntentUri(Venue venue) {
        StringBuilder builder = new StringBuilder()
                .append("geo:")
                .append(venue.getLattitude())
                .append(venue.getLongitude())
                .append("?q=")
                .append(venue.getName());

        return Uri.parse(builder.toString());
    }

    public static void startMapIntent(Venue venue, Activity activity) {
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, MapUtils.getMapIntentUri(venue));
        mapIntent.setPackage("com.google.android.apps.maps");
        activity.startActivity(mapIntent);
    }
}

package com.theappbusiness.kc.persistence;

import android.content.Context;
import android.content.SharedPreferences;

import com.theappbusiness.kc.di.qualifiers.ForApplication;

import javax.inject.Inject;

/**
 * TODO Add a class header comment
 */
public class PreferencesManagerImpl implements PreferencesManager {
    private static final String PREFERENCES_NAME = "kc";

    private final SharedPreferences mSharedPreferences;

    @Inject
    public PreferencesManagerImpl(@ForApplication Context context) {
        mSharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }
}

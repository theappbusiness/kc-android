package com.theappbusiness.kc.di.modules;

import android.content.Context;

import com.snappydb.SnappyDB;
import com.theappbusiness.kc.di.qualifiers.ForApplication;
import com.theappbusiness.kc.di.scopes.PerApplication;
import com.theappbusiness.kc.persistence.Database;
import com.theappbusiness.kc.persistence.DatabaseImpl;
import com.theappbusiness.kc.persistence.PreferencesManager;
import com.theappbusiness.kc.persistence.PreferencesManagerImpl;

import dagger.Module;
import dagger.Provides;

/**
 * TODO Add a class header comment
 */
@Module
public class PersistenceModule {
    @Provides
    @PerApplication
    PreferencesManager providePrefsManager(PreferencesManagerImpl prefsManager) {
        return prefsManager;
    }

    @Provides
    @PerApplication
    Database provideDatabase(DatabaseImpl db) {
        return db;
    }

    @Provides
    @PerApplication
    SnappyDB.Builder provideSnappyDb(@ForApplication Context context) {
        return new SnappyDB.Builder(context);
    }
}

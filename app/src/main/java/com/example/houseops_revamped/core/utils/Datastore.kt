package com.example.houseops_revamped.core.utils

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

val Context.datastore by preferencesDataStore(
    name = DatastoreConstants.PREFERENCES_NAME
)
package com.example.houseops_revamped.core.utils.datastore

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.example.houseops_revamped.core.utils.datastore.DatastoreConstants

val Context.datastore by preferencesDataStore(
    name = DatastoreConstants.PREFERENCES_NAME
)
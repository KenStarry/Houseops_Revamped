package com.kenstarry.houseops_revamped.core.data.datastore.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.kenstarry.houseops_revamped.core.data.datastore.DatastoreConstants
import com.kenstarry.houseops_revamped.core.data.datastore.datastore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserDetailsPreference(
    private val context: Context
) {

    companion object {
        val USER_TYPE_KEY = stringPreferencesKey(DatastoreConstants.DETAILS_USER_TYPE_KEY)
    }

    val getUserType: Flow<String?> = context.datastore.data
        .map { preferences ->
            preferences[USER_TYPE_KEY]
        }

    suspend fun setUserType(userType: String) {
        context.datastore.edit { preferences ->
            preferences[USER_TYPE_KEY] = userType
        }
    }


}
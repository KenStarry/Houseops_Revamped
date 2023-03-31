package com.kenstarry.houseops_revamped.core.domain.util

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

//  We use this function to check if permission is enabled
fun Context.hasLocationPermission(): Boolean {
    return ContextCompat.checkSelfPermission(
        this,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
}
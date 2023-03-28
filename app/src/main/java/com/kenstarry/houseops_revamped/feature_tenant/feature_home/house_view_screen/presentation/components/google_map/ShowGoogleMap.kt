package com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.presentation.components.google_map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun ShowGoogleMap(
    location: LatLng
) {

    val mapProperties by remember {
        mutableStateOf(
            MapProperties(
                maxZoomPreference = 15f,
                minZoomPreference = 7f
            )
        )
    }

    val mapUISettings by remember {
        mutableStateOf(
            MapUiSettings(
                myLocationButtonEnabled = true,
                scrollGesturesEnabled = true,
                scrollGesturesEnabledDuringRotateOrZoom = true,
                mapToolbarEnabled = true
            )
        )
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, 15f)
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.onSecondary)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {

        GoogleMap(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .fillMaxWidth()
                .height(400.dp),
            cameraPositionState = cameraPositionState,
            properties = mapProperties,
            uiSettings = mapUISettings
        )

    }


}
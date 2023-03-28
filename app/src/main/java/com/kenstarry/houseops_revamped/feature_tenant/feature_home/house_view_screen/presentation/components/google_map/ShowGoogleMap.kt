package com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.presentation.components.google_map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun ShowGoogleMap(
    location: LatLng,
    placeName: String,
    primaryColor: Color,
    tertiaryColor: Color
) {

    val mapProperties by remember {
        mutableStateOf(
            MapProperties(
                maxZoomPreference = 20f,
                minZoomPreference = 5f
            )
        )
    }

    val mapUISettings by remember {
        mutableStateOf(
            MapUiSettings(
                myLocationButtonEnabled = true,
                scrollGesturesEnabled = false,
                scrollGesturesEnabledDuringRotateOrZoom = false,
                mapToolbarEnabled = true,
                rotationGesturesEnabled = false,
                tiltGesturesEnabled = false
            )
        )
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, 15f)
    }

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.onSecondary)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Outlined.LocationOn,
                contentDescription = "Location",
                tint = primaryColor
            )

            Text(
                text = placeName,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
        }

        GoogleMap(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .fillMaxWidth()
                .height(400.dp),
            cameraPositionState = cameraPositionState,
            properties = mapProperties,
            uiSettings = mapUISettings
        ) {
            val infoWindowState = rememberMarkerState(position = location)

            var isInfoWindowVisible by remember {
                mutableStateOf(false)
            }

            //  marker
            Marker(
                state = MarkerState(location),
                draggable = false,
                title = "Marker in $placeName"
            )

            MarkerInfoWindow(
                state = infoWindowState,
                title = placeName,
                content = {
                    Column(
                        modifier = Modifier.wrapContentSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(text = "Hello There ")
                    }
                }
            )
        }

    }


}
package com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.presentation.components.google_map

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.*
import com.google.maps.android.compose.*
import com.kenstarry.houseops_revamped.R
import com.kenstarry.houseops_revamped.core.data.repository.LocationService
import com.kenstarry.houseops_revamped.core.domain.model.events.CoreEvents
import com.kenstarry.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.houseops_revamped.feature_tenant.feature_settings.presentation.utils.SettingsConstants
import com.kenstarry.houseops_revamped.feature_tenant.feature_settings.presentation.viewmodel.SettingsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun ShowGoogleMap(
    location: LatLng,
    placeName: String,
    primaryColor: Color,
    tertiaryColor: Color
) {

    val context = LocalContext.current
    val settingsVM: SettingsViewModel = hiltViewModel()
    //  check if system is in dark theme or light theme
    val savedTheme = settingsVM.themeFlow.collectAsState(initial = null).value
    val inDarkTheme = isSystemInDarkTheme()

    savedTheme?.let {

        val mapProperties by remember {
            mutableStateOf(
                MapProperties(
                    maxZoomPreference = 20f,
                    minZoomPreference = 5f,
                    isBuildingEnabled = true,
                    isMyLocationEnabled = true,
                    mapStyleOptions = when (savedTheme) {
                        SettingsConstants.themeOptions[0].title -> {
                            MapStyleOptions.loadRawResourceStyle(context, R.raw.map_night)
                        }
                        SettingsConstants.themeOptions[1].title -> {
                            null
                        }
                        SettingsConstants.themeOptions[2].title -> {
                            if (inDarkTheme) {
                                MapStyleOptions.loadRawResourceStyle(context, R.raw.map_night)
                            } else {
                                null
                            }
                        }
                        else -> null
                    }
                )
            )
        }

        val mapUISettings by remember {
            mutableStateOf(
                MapUiSettings(
                    myLocationButtonEnabled = true,
                    scrollGesturesEnabled = true,
                    scrollGesturesEnabledDuringRotateOrZoom = true,
                    mapToolbarEnabled = true,
                    rotationGesturesEnabled = true,
                    tiltGesturesEnabled = true
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

            //  let user toggle location tracking


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
                    title = "Marker in $placeName",
                    icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
                )
            }

        }
    }


}
package com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.presentation.components.google_map

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.kenstarry.houseops_revamped.BuildConfig
import com.kenstarry.houseops_revamped.R
import com.kenstarry.houseops_revamped.core.data.repository.LocationService
import com.kenstarry.houseops_revamped.core.domain.model.Apartment
import com.kenstarry.houseops_revamped.core.domain.model.events.CoreEvents
import com.kenstarry.houseops_revamped.core.presentation.components.permission_handling.RequestPermission
import com.kenstarry.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.houseops_revamped.ui.theme.HouseOps_RevampedTheme

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun DetailsGoogleMap(
    apartment: Apartment?,
    primaryColor: Color,
    tertiaryColor: Color
) {

    //  location permission
    val locationPermissionState = rememberPermissionState(
        permission = android.Manifest.permission.ACCESS_FINE_LOCATION
    )
    val coreVM: CoreViewModel = hiltViewModel()
    val context = LocalContext.current

    RequestPermission(
        permissionState = locationPermissionState,
        deniedContent = {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(id = R.drawable.undraw_current_location_re_j130),
                    contentDescription = "location SVG",
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp)
                )

                Column(
                    modifier = Modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    Text(
                        text = "Location Permission",
                        fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.9f)
                    )

                    Text(
                        text = "Grant access to view pin-point live locations on Google Maps.",
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                    )

                    TextButton(
                        onClick = {
                            locationPermissionState.launchPermissionRequest()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.onPrimary,
                            contentColor = primaryColor
                        ),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(
                            text = "Grant permission",
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                }

            }
        },
        content = {

            apartment?.apartmentLocation?.let {

                //  initialize places client
                Places.initialize(context, BuildConfig.MAPS_API_KEY)


                coreVM.onEvent(
                    CoreEvents.GetPlaceCoordinates(
                        place = it,
                        placesClient = Places.createClient(context),
                        response = {}
                    ))

                coreVM.currentLatLong.value?.let { latlng ->
                    ShowGoogleMap(
                        location = latlng,
                        placeName = it.address,
                        primaryColor, tertiaryColor
                    )
                }
            }
        }
    )

}
































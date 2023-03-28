package com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.presentation.components.google_map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.kenstarry.houseops_revamped.core.domain.model.Apartment
import com.kenstarry.houseops_revamped.core.domain.model.events.CoreEvents
import com.kenstarry.houseops_revamped.core.presentation.components.permission_handling.RequestPermission
import com.kenstarry.houseops_revamped.core.presentation.viewmodel.CoreViewModel

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
            // show error message
            Button(onClick = {
                locationPermissionState.launchPermissionRequest()
            }) {
                Text(text = "Grant Location Permission")
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
                    ShowGoogleMap(location = latlng)
                }
            }
        }
    )

}
































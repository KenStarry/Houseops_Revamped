package com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation

import android.Manifest
import android.content.Context
import android.content.Intent
import android.location.Geocoder
import android.location.LocationManager
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.kenstarry.houseops_revamped.R
import com.kenstarry.houseops_revamped.core.domain.model.LocationAddresses
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.core.domain.model.events.BottomSheetEvents
import com.kenstarry.houseops_revamped.core.domain.model.events.CoreEvents
import com.kenstarry.houseops_revamped.core.domain.util.hasLocationPermission
import com.kenstarry.houseops_revamped.core.presentation.components.BottomSheet
import com.kenstarry.houseops_revamped.core.presentation.components.Lottie
import com.kenstarry.houseops_revamped.core.presentation.model.LatLngModel
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants
import com.kenstarry.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.houseops_revamped.feature_tenant.feature_categories.domain.model.CategoryEvents
import com.kenstarry.houseops_revamped.feature_tenant.feature_categories.presentation.components.content_agent.CaretakerBottomSheet
import com.kenstarry.houseops_revamped.feature_tenant.feature_categories.presentation.viewmodel.CategoriesViewModel
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.*
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.utils.HomeConstants
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.viewmodel.HomeViewModel
import com.kenstarry.houseops_revamped.navigation.Direction
import com.kenstarry.houseops_revamped.navigation.screens.Screens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class, ExperimentalPermissionsApi::class
)
@Composable
fun HomeScreen(
    navHostController: NavHostController
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val homeVM: HomeViewModel = hiltViewModel()
    val categoriesVM: CategoriesViewModel = hiltViewModel()
    val servicesScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    val direction = Direction(navHostController)
    val snackbarHostState = remember { SnackbarHostState() }
    val scrollState = rememberScrollState()
    val context = LocalContext.current

//    //  get current user location
//    val latLng = remember {
//        mutableStateOf<LatLngModel?>(null)
//    }
//
//    //  get location
//    coreVM.onEvent(
//        CoreEvents.GetCurrentLocation(
//            interval = 2000L,
//            onResponse = {}
//        )
//    )
//
//    LaunchedEffect(key1 = Unit) {
//        coreVM.userCurrentLocation.value
//            ?.catch { e -> Log.d("location", "Error occurred : $e") }
//            ?.onEach { location ->
//                latLng.value = LatLngModel(location.latitude, location.longitude)
//            }
//            ?.launchIn(servicesScope)
//    }
//
//    var address by remember {
//        mutableStateOf<LocationAddresses?>(null)
//    }

//    latLng.value?.let {
//        coreVM.onEvent(CoreEvents.GetLocationAddressName(
//            latLngModel = it,
//            address = { addr ->
//                address = addr
//            }
//        ))
//    }

    val primaryColor = Color(
        coreVM.primaryAccentFlow.collectAsState(
            initial = Constants.accentColors[0].darkColor
        ).value ?: Constants.accentColors[0].darkColor
    )

    val tertiaryColor = Color(
        coreVM.tertiaryAccentFlow.collectAsState(
            initial = Constants.accentColors[0].lightColor
        ).value ?: Constants.accentColors[0].lightColor
    )

    val currentUser = coreVM.currentUser()
    val userDetails = coreVM.getUserDetails(currentUser?.email ?: "no email")
    //  location permission
    val locationPermissionState = rememberPermissionState(
        permission = Manifest.permission.ACCESS_FINE_LOCATION
    )

    coreVM.onEvent(
        CoreEvents.GetApartments(
            response = {}
        ))

    BottomSheet(
        sheetBackground = MaterialTheme.colorScheme.onPrimary,
        sheetContent = { state, scope ->

            when (categoriesVM.bottomSheetContent.value) {
                //  Caretakers screen
                HomeConstants.homePills[4].title -> {
                    CaretakerBottomSheet(
                        categoriesVM = categoriesVM,
                        agent = categoriesVM.agentData.value,
                        userDetails = userDetails,
                        direction = direction,
                        onHouseClicked = { house ->
                            //  open house view Screen
                            direction.navigateToHouseView(
                                house.houseApartmentName, house.houseCategory
                            )

                            //  dismiss bottomshee
                            coreVM.onBottomSheetEvent(
                                BottomSheetEvents.CloseBottomSheet(
                                    state, scope
                                )
                            )
                        },
                        primaryColor = primaryColor,
                        tertiaryColor = tertiaryColor
                    )
                }

                else -> {
                    CaretakerBottomSheet(
                        categoriesVM = categoriesVM,
                        agent = categoriesVM.agentData.value,
                        userDetails = userDetails,
                        direction = direction,
                        onHouseClicked = { house ->
                            //  open house view Screen
                            direction.navigateToHouseView(
                                house.houseApartmentName, house.houseCategory
                            )

                            //  dismiss bottomshee
                            coreVM.onBottomSheetEvent(
                                BottomSheetEvents.CloseBottomSheet(
                                    state, scope
                                )
                            )
                        },
                        primaryColor = primaryColor,
                        tertiaryColor = tertiaryColor
                    )
                }

            }
        },
        sheetScope = { state, scope ->
            Scaffold(
                snackbarHost = {
                    SnackbarHost(hostState = snackbarHostState) { data ->
                    }
                },
                topBar = {
                    userDetails?.userImageUri?.let {
                        if (it.uri.isBlank()) {
                            HomeAppBar(
                                navHostController = navHostController,
                                direction = direction,
                                userImageUrl = currentUser?.photoUrl?.toString() ?: ""
                            )
                        } else {
                            HomeAppBar(
                                navHostController = navHostController,
                                direction = direction,
                                userImageUrl = it.uri
                            )
                        }
                    }
                },
                floatingActionButton = {
                    //  only display the fab if the user is a caretaker
                    ExtendedFloatingActionButton(
                        text = {
                            Text(text = "Dashboard")
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.Dashboard,
                                contentDescription = "Dashboard"
                            )
                        },
                        onClick = {
                            direction.navigateToRoute(
                                Screens.Dashboard.route,
                                null
                            )
                        },
                        expanded = true,
                        containerColor = primaryColor
                    )
                }
            ) {

                //  GPS Alert dialog
                AnimatedVisibility(
                    visible = coreVM.alertDialogSelected.value?.dialogType
                            == "GPS dialog" &&
                            coreVM.alertDialogSelected.value?.isDialogVisible == true
                ) {
                    GPSAlertDialog(
                        primaryColor = primaryColor,
                        tertiaryColor = tertiaryColor,
                        onConfirm = {
                            //  open location settings
                            context.startActivity(
                                Intent(
                                    Settings.ACTION_LOCATION_SOURCE_SETTINGS
                                )
                            )

                            //  close dialog
                            coreVM.onEvent(
                                CoreEvents.ToggleAlertDialog(
                                    dialogType = "GPS dialog",
                                    isDialogVisible = false
                                )
                            )
                        },
                        onDismiss = {
                            coreVM.onEvent(
                                CoreEvents.ToggleAlertDialog(
                                    dialogType = "GPS dialog",
                                    isDialogVisible = false
                                )
                            )
                        }
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.onPrimary)
                            .verticalScroll(scrollState)
                            .padding(horizontal = 12.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {

                        //  greetings text
                        HomeGreetings(
                            userName = if (userDetails?.userName == "no name")
                                currentUser?.displayName ?: ""
                            else
                                userDetails?.userName ?: "",

                            location = null,

                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(vertical = 8.dp),

                            onRequestPermission = {
                                when (locationPermissionState.status) {
                                    is PermissionStatus.Granted -> {

                                        val locationManager =
                                            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

                                        if (!locationManager.isProviderEnabled(
                                                LocationManager.GPS_PROVIDER
                                            )
                                        ) {
                                            coreVM.onEvent(
                                                CoreEvents.ToggleAlertDialog(
                                                    dialogType = "GPS dialog",
                                                    isDialogVisible = true
                                                )
                                            )
                                        }
                                    }
                                    is PermissionStatus.Denied -> {
                                        locationPermissionState.launchPermissionRequest()
                                    }
                                }
                            }
                        )

                        if (homeVM.houses.isEmpty()) {

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(450.dp)
                                    .background(MaterialTheme.colorScheme.onPrimary),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {

                                Lottie(
                                    rawFile = R.raw.search_icon,
                                    isPlaying = true,
                                    iterations = 1,
                                    modifier = Modifier
                                        .fillMaxWidth(0.9f)
                                        .height(250.dp)
                                )

                                Spacer(modifier = Modifier.height(24.dp))

                                Text(
                                    text = "No Houses uploaded yet...",
                                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(
                                        alpha = 0.8f
                                    )
                                )

                            }

                        } else {
                            //  pill buttons
                            PillSection(
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .height(150.dp),
                                onPillClicked = { categoryTitle ->
                                    direction.navigateToCategory(categoryTitle)
                                },
                                primaryColor = primaryColor,
                                tertiaryColor = tertiaryColor
                            )

                            //  featured section
                            FeaturedSection(
                                title = "Featured",
                                context = context,
                                houses = homeVM.houses,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                user = userDetails,
                                navHostController = navHostController,
                                snackbarHostState = snackbarHostState,
                                primaryColor = primaryColor,
                                tertiaryColor = tertiaryColor
                            )

                            ApartmentsSection(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                allApartments = coreVM.allApartments.value,
                                title = "Apartments",
                                primaryColor = primaryColor,
                                tertiaryColor = tertiaryColor,
                                onViewApartmentClicked = { apartment ->
                                    //  open apartment view screen
                                }
                            )

                            Spacer(modifier = Modifier.height(64.dp))
                        }

                    }

                }

            }
        },
        closeBottomSheet = { state, scope ->
            categoriesVM.onEvent(
                CategoryEvents.CloseBottomSheet(
                    state = state,
                    scope = scope
                )
            )
        }
    )
}
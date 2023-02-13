package com.example.houseops_revamped.feature_home.house_view_screen.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Timelapse
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.houseops_revamped.core.domain.model.events.AlertDialogEvents
import com.example.houseops_revamped.core.domain.model.events.BottomSheetEvents
import com.example.houseops_revamped.core.presentation.components.BottomSheet
import com.example.houseops_revamped.core.presentation.components.CustomAlertDialog
import com.example.houseops_revamped.core.presentation.components.ExtendedFab
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.example.houseops_revamped.core.utils.Constants
import com.example.houseops_revamped.feature_booked.domain.model.BookedHouseModel
import com.example.houseops_revamped.feature_categories.domain.model.CategoryEvents
import com.example.houseops_revamped.feature_categories.presentation.components.content_caretaker.CaretakerBottomSheet
import com.example.houseops_revamped.feature_categories.presentation.viewmodel.CategoriesViewModel
import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel
import com.example.houseops_revamped.feature_home.house_view_screen.domain.model.HouseViewEvents
import com.example.houseops_revamped.feature_home.house_view_screen.domain.model.UserBooked
import com.example.houseops_revamped.feature_home.house_view_screen.domain.utils.HouseViewConstants
import com.example.houseops_revamped.feature_home.house_view_screen.presentation.components.bottom_sheets.BookHouseDatePicker
import com.example.houseops_revamped.feature_home.house_view_screen.presentation.components.bottom_sheets.BookedHouseBottomSheet
import com.example.houseops_revamped.feature_home.house_view_screen.presentation.components.view_pager.HouseViewPager
import com.example.houseops_revamped.feature_home.house_view_screen.presentation.components.house_view_details.HouseViewDetails
import com.example.houseops_revamped.feature_home.house_view_screen.presentation.viewmodel.HouseViewVM
import com.example.houseops_revamped.navigation.Direction
import com.example.houseops_revamped.ui.theme.LimeGreen

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HouseViewScreen(
    navHostController: NavHostController,
    apartment: String,
    category: String
) {

    val houseViewVM: HouseViewVM = hiltViewModel()
    val coreVM: CoreViewModel = hiltViewModel()
    val categoriesVM: CategoriesViewModel = hiltViewModel()

    val currentUser = coreVM.currentUser()
    val userDetails = coreVM.getUserDetails(currentUser?.email ?: "no email")
    val direction = Direction(navHostController)
    val context = LocalContext.current

    houseViewVM.getHouse(apartment, category)

    var openBookAlertDialog by remember { mutableStateOf(false) }
    var isHouseBooked by remember {
        mutableStateOf(false)
    }

    Toast.makeText(context, "$isHouseBooked", Toast.LENGTH_SHORT).show()
    var dateBooked by remember { mutableStateOf("") }

    BottomSheet(
        sheetBackground = MaterialTheme.colorScheme.onPrimary,
        sheetContent = { state, scope ->
            when (coreVM.bottomSheetContent.value) {

                HouseViewConstants.HV_CARETAKER_BOTTOM_SHEET -> {
                    CaretakerBottomSheet(
                        categoriesVM = categoriesVM,
                        caretaker = categoriesVM.caretakerData.value,
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
                        }
                    )
                }

                HouseViewConstants.HV_BOOK_HOUSE_BOTTOM_SHEET -> {
                    BookedHouseBottomSheet(
                        house = coreVM.bottomSheetData.value as HouseModel
                    )
                }

                HouseViewConstants.HV_BOOK_DATE_BOTTOM_SHEET -> {
                    BookHouseDatePicker(
                        onCloseBottomSheet = {
                            categoriesVM.onEvent(CategoryEvents.CloseBottomSheet(state, scope))
                        },
                        onHouseBooked = { date ->

                            dateBooked = date

                            //  add user to house booked
                            userDetails?.userEmail?.let {
                                houseViewVM.onEvent(
                                    HouseViewEvents.AddUserToHouseBooked(
                                        apartmentName = apartment,
                                        houseCategory = category,
                                        userBooked = UserBooked(
                                            userEmail = it,
                                            dateBooked = date
                                        ),
                                        isAdd = true
                                    )
                                )
                            }

                            //  update house field
                            houseViewVM.currentHouse?.let { house ->
                                userDetails?.userEmail?.let { email ->
                                    houseViewVM.onEvent(
                                        HouseViewEvents.AddToBookedHouses(
                                            bookedHouse = BookedHouseModel(
                                                houseId = house.houseId,
                                                dateBooked = dateBooked
                                            ),
                                            email = email,
                                            isAdd = true
                                        )
                                    )
                                }
                            }

                            coreVM.onBottomSheetEvent(
                                BottomSheetEvents.OpenBottomSheet(
                                    state = state,
                                    scope = scope,
                                    bottomSheetType = HouseViewConstants.HV_BOOK_HOUSE_BOTTOM_SHEET,
                                    bottomSheetData = houseViewVM.currentHouse
                                )
                            )
                        }
                    )
                }

                else -> {
                    CaretakerBottomSheet(
                        categoriesVM = categoriesVM,
                        caretaker = categoriesVM.caretakerData.value,
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
                        }
                    )
                }
            }
        },
        sheetScope = { state, scope ->

            when (coreVM.alertDialogContent.value) {
                Constants.BOOK_HOUSE_ALERT -> {

                    if (openBookAlertDialog) {
                        CustomAlertDialog(
                            icon = Icons.Outlined.Warning,
                            title = "Notice!",
                            content = {
                                Text(
                                    text = "HouseOps will not ask you to pay " +
                                            "any amount of money to book a house prior to seeing it. " +
                                            "Do not pay for a house before inspecting it!",
                                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                    fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(
                                        alpha = 0.8f
                                    )
                                )
                            },
                            onConfirm = {

                                coreVM.onBottomSheetEvent(
                                    BottomSheetEvents.OpenBottomSheet(
                                        state = state,
                                        scope = scope,
                                        bottomSheetType = HouseViewConstants.HV_BOOK_DATE_BOTTOM_SHEET,
                                        bottomSheetData = null
                                    )
                                )

                                openBookAlertDialog = false
                            },
                            onDismiss = {
                                openBookAlertDialog = false
                            }
                        )
                    }
                }
            }

            Scaffold(
                floatingActionButton = {

                    //  check if house is booked or not
                    userDetails?.userBookedHouses?.let {
                        isHouseBooked = it.any { bookedHouse ->
                            bookedHouse.houseId == houseViewVM.currentHouse?.houseId
                        }
                    }

                    if (isHouseBooked) {

                        ExtendedFab(
                            icon = Icons.Outlined.Check,
                            title = "House Booked",
                            containerColor = LimeGreen,
                            onFabClicked = {

                                val currentUserBookedDate =
                                    houseViewVM.currentHouse?.houseUsersBooked?.filter {
                                        it.userEmail == userDetails?.userEmail
                                    }?.map { it.dateBooked }?.first()

                                //  remove user from house booked
                                userDetails?.userEmail?.let {
                                    houseViewVM.onEvent(
                                        HouseViewEvents.AddUserToHouseBooked(
                                            apartmentName = apartment,
                                            houseCategory = category,
                                            userBooked = UserBooked(
                                                userEmail = it,
                                                dateBooked = currentUserBookedDate ?: "no date"
                                            ),
                                            isAdd = false
                                        )
                                    )
                                }

                                //  update house field
                                houseViewVM.currentHouse?.let { house ->
                                    userDetails?.userEmail?.let { email ->
                                        houseViewVM.onEvent(
                                            HouseViewEvents.AddToBookedHouses(
                                                bookedHouse = BookedHouseModel(
                                                    houseId = house.houseId,
                                                    dateBooked = currentUserBookedDate ?: "no date"
                                                ),
                                                email = email,
                                                isAdd = false
                                            )
                                        )
                                    }
                                }

                                //  toast message
                                Toast.makeText(
                                    context,
                                    "House dropped successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        )

                    } else {
                        ExtendedFab(
                            icon = Icons.Outlined.Timelapse,
                            title = "Book Now",
                            onFabClicked = {

                                openBookAlertDialog = true

                                coreVM.onAlertEvent(
                                    AlertDialogEvents.OpenAlertDialog(
                                        Constants.BOOK_HOUSE_ALERT
                                    )
                                )
                            }
                        )
                    }
                }
            ) { contentPadding ->

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(contentPadding)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.onPrimary)
                            .verticalScroll(rememberScrollState())
                            .padding(12.dp)
                    ) {

                        houseViewVM.currentHouse?.let {

                            //  view pager
                            HouseViewPager(
                                house = it,
                                context = context,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp),
                                navHostController = navHostController
                            )

                            Spacer(modifier = Modifier.height(24.dp))

                            //  main content
                            HouseViewDetails(
                                context = context,
                                house = it,
                                userDetails = userDetails,
                                onCaretakerClicked = { caretaker ->
                                    coreVM.onBottomSheetEvent(
                                        BottomSheetEvents.OpenBottomSheet(
                                            state = state,
                                            scope = scope,
                                            bottomSheetType = HouseViewConstants.HV_CARETAKER_BOTTOM_SHEET,
                                            bottomSheetData = caretaker
                                        )
                                    )
                                }
                            )

                            Spacer(modifier = Modifier.height(56.dp))
                        }
                    }

                }
            }
        },
        closeBottomSheet = { state, scope ->
            categoriesVM.onEvent(CategoryEvents.CloseBottomSheet(state, scope))
        }
    )

}



















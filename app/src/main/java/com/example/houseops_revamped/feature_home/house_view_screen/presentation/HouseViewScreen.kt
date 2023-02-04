package com.example.houseops_revamped.feature_home.house_view_screen.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.BookOnline
import androidx.compose.material.icons.outlined.Paid
import androidx.compose.material.icons.outlined.Timelapse
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.houseops_revamped.core.presentation.components.BottomSheet
import com.example.houseops_revamped.core.presentation.components.ExtendedFab
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.example.houseops_revamped.core.utils.Constants
import com.example.houseops_revamped.feature_categories.domain.model.CategoryEvents
import com.example.houseops_revamped.feature_categories.presentation.components.content_caretaker.CaretakerBottomSheet
import com.example.houseops_revamped.feature_categories.presentation.viewmodel.CategoriesViewModel
import com.example.houseops_revamped.feature_home.home_screen.presentation.utils.HomeConstants
import com.example.houseops_revamped.feature_home.house_view_screen.domain.utils.HouseViewConstants
import com.example.houseops_revamped.feature_home.house_view_screen.presentation.components.view_pager.HouseViewPager
import com.example.houseops_revamped.feature_home.house_view_screen.presentation.components.house_view_details.HouseViewDetails
import com.example.houseops_revamped.feature_home.house_view_screen.presentation.viewmodel.HouseViewVM
import com.example.houseops_revamped.navigation.Direction

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

    BottomSheet(
        sheetBackground = MaterialTheme.colorScheme.onPrimary,
        sheetContent = { state, scope ->

            when (categoriesVM.bottomSheetContent.value) {

                HouseViewConstants.HV_CARETAKER_BOTTOM_SHEET -> {
                    CaretakerBottomSheet(
                        categoriesVM = categoriesVM,
                        caretaker = categoriesVM.caretakerData.value,
                        userDetails = userDetails,
                        direction = direction
                    )
                }

                else -> {
                    CaretakerBottomSheet(
                        categoriesVM = categoriesVM,
                        caretaker = categoriesVM.caretakerData.value,
                        userDetails = userDetails,
                        direction = direction
                    )
                }
            }

        },
        sheetScope = { state, scope ->

            Scaffold(
                floatingActionButton = {
                    ExtendedFab(
                        icon = Icons.Outlined.Timelapse,
                        title = "Book House"
                    )
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
                                    categoriesVM.onEvent(
                                        CategoryEvents.OpenBottomSheet(
                                            state = state,
                                            scope = scope,
                                            bottomSheetType = Constants.CARETAKER_BOTTOM_SHEET_TYPE,
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



















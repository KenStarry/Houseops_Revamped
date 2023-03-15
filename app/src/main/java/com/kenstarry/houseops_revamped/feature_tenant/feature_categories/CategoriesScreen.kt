package com.kenstarry.houseops_revamped.feature_tenant.feature_categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kenstarry.houseops_revamped.core.domain.model.events.BottomSheetEvents
import com.kenstarry.houseops_revamped.core.presentation.components.BottomSheet
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants
import com.kenstarry.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.houseops_revamped.feature_tenant.feature_categories.domain.model.CategoryEvents
import com.kenstarry.houseops_revamped.feature_tenant.feature_categories.presentation.components.CategoriesTopBar
import com.kenstarry.houseops_revamped.feature_tenant.feature_categories.presentation.components.content_caretaker.CaretakerBottomSheet
import com.kenstarry.houseops_revamped.feature_tenant.feature_categories.presentation.components.content_caretaker.ContentCaretaker
import com.kenstarry.houseops_revamped.feature_tenant.feature_categories.presentation.viewmodel.CategoriesViewModel
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.utils.HomeConstants
import com.kenstarry.houseops_revamped.navigation.Direction

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun CategoriesScreen(
    navHostController: NavHostController,
    categoryTitle: String
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val categoriesVM: CategoriesViewModel = hiltViewModel()

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
    val direction = Direction(navHostController)

    val categories = HomeConstants.homePills
    val caretakers = coreVM.getAllCaretakers()

    BottomSheet(
        sheetBackground = MaterialTheme.colorScheme.onPrimary,
        sheetContent = { state, scope ->

            when (categoriesVM.bottomSheetContent.value) {
                //  Caretakers screen
                categories[5].title -> {
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
                            ))
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
                            ))
                        },
                        primaryColor = primaryColor,
                        tertiaryColor = tertiaryColor
                    )
                }
            }
        },
        sheetScope = { state, scope ->
            Scaffold(
                topBar = {
                    CategoriesTopBar(
                        title = categoryTitle,
                        icon = categories.find { it.title == categoryTitle }?.icon,
                        onBackPressed = {
                            direction.navigateUp()
                        }
                    )
                }
            ) { contentPadding ->

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.onPrimary)
                        .padding(contentPadding)
                ) {

                    when (categoryTitle) {

                        //  House Categories screen
                        categories[0].title -> {

                        }
                        //  For Rent screen
                        categories[1].title -> {

                        }
                        //  Apartments screen
                        categories[2].title -> {

                        }
                        //  For Sale screen
                        categories[3].title -> {

                        }
                        //  Near You screen
                        categories[4].title -> {

                        }
                        //  Caretakers screen
                        categories[5].title -> {
                            ContentCaretaker(
                                caretakers = caretakers,
                                onCardClicked = {
                                    categoriesVM.onEvent(
                                        CategoryEvents.OpenBottomSheet(
                                            state = state,
                                            scope = scope,
                                            bottomSheetType = categoryTitle,
                                            bottomSheetData = it
                                        )
                                    )
                                },
                                primaryColor = primaryColor,
                                tertiaryColor = tertiaryColor
                            )
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
            ))
        }
    )
}
package com.example.houseops_revamped.feature_home.home_screen.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.houseops_revamped.core.domain.model.BottomSheetEvents
import com.example.houseops_revamped.core.presentation.components.BottomSheet
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.example.houseops_revamped.feature_categories.domain.model.CategoryEvents
import com.example.houseops_revamped.feature_categories.presentation.components.content_caretaker.CaretakerBottomSheet
import com.example.houseops_revamped.feature_categories.presentation.viewmodel.CategoriesViewModel
import com.example.houseops_revamped.feature_home.home_screen.presentation.components.*
import com.example.houseops_revamped.feature_home.home_screen.presentation.utils.HomeConstants
import com.example.houseops_revamped.feature_home.home_screen.presentation.viewmodel.HomeViewModel
import com.example.houseops_revamped.navigation.Direction

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalMaterialApi::class
)
@Composable
fun HomeScreen(
    navHostController: NavHostController
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val homeVM: HomeViewModel = hiltViewModel()
    val categoriesVM: CategoriesViewModel = hiltViewModel()

    val direction = Direction(navHostController)

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    val currentUser = coreVM.currentUser()
    val userDetails = coreVM.getUserDetails(currentUser?.email ?: "no email")
    val context = LocalContext.current

    BottomSheet(
        sheetBackground = MaterialTheme.colorScheme.onPrimary,
        sheetContent = { state, scope ->

            when (categoriesVM.bottomSheetContent.value) {
                //  Caretakers screen
                HomeConstants.homePills[5].title -> {
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
                            ))
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
                            coreVM.onBottomSheetEvent(BottomSheetEvents.CloseBottomSheet(
                                state, scope
                            ))
                        }
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
                        HomeAppBar(
                            navHostController = navHostController,
                            userImageUrl = it
                        )
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
                        onClick = { /*TODO*/ },
                        expanded = true,
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                }
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.onPrimary)
                            .padding(horizontal = 16.dp)
                            .verticalScroll(scrollState),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {

                        //  greetings text
                        HomeGreetings(
                            userName = userDetails?.userName ?: "",
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(vertical = 8.dp)
                        )

                        //  pill buttons
                        PillSection(
                            modifier = Modifier
                                .wrapContentWidth()
                                .height(150.dp),
                            onPillClicked = { categoryTitle ->
                                direction.navigateToCategory(categoryTitle)
                            }
                        )

                        //  caretaker section
                        CaretakerSection(
                            coreVM = coreVM,
                            context = context,
                            onCardClicked = { caretaker ->
                                categoriesVM.onEvent(
                                    CategoryEvents.OpenBottomSheet(
                                        state = state,
                                        scope = scope,
                                        bottomSheetType = HomeConstants.homePills[5].title,
                                        bottomSheetData = caretaker
                                    )
                                )
                            }
                        )

                        //  featured section
                        FeaturedSection(
                            title = "Featured",
                            context = context,
                            houses = homeVM.houses,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentSize(),
                            user = userDetails,
                            navHostController = navHostController,
                            snackbarHostState = snackbarHostState
                        )

                        ApartmentsSection(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            title = "Apartments"
                        )

                        Spacer(modifier = Modifier.height(48.dp))

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
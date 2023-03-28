package com.kenstarry.houseops_revamped.feature_tenant.feature_bookmark.presentation.components

import android.content.Context
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kenstarry.houseops_revamped.core.domain.model.HouseCategoryModel
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection
import com.kenstarry.houseops_revamped.core.domain.model.HouseModel
import com.kenstarry.houseops_revamped.core.domain.model.events.CoreEvents
import com.kenstarry.houseops_revamped.core.presentation.components.HouseItemAlt
import com.kenstarry.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.houseops_revamped.navigation.Direction

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryItem(
    context: Context,
    navHostController: NavHostController,
    houseCategory: HouseCategoryModel,
    bookmarkedHouses: List<HouseModel>,
    currentUser: UsersCollection?,
    snackbarHostState: SnackbarHostState,
    primaryColor: Color,
    tertiaryColor: Color
) {

    val direction = Direction(navHostController)
    val coreVM: CoreViewModel = hiltViewModel()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        //  title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(MaterialTheme.colorScheme.onPrimary),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(35.dp)
                    .background(tertiaryColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = houseCategory.icon,
                    contentDescription = "House category",
                    tint = primaryColor
                )
            }

            Text(
                text = houseCategory.title,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
            )

            Divider(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.1f)),
                thickness = 1.dp
            )

        }

        //  another lazy column for the house items
        LazyColumn(
            content = {
                items(
                    items = bookmarkedHouses,
                    key = { it.houseId }
                ) { house ->

                    if (house.houseCategory == houseCategory.title) {

                        //  get apartment details of the house
                        coreVM.onEvent(
                            CoreEvents.GetApartmentDetails(
                                apartmentName = house.houseApartmentName,
                                apartmentDetails = {},
                                response = {}
                            ))

                        HouseItemAlt(
                            context = context,
                            house = house,
                            location = coreVM.apartmentDetails.value?.apartmentLocation?.address
                                ?: "no location",
                            user = currentUser,
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .fillMaxWidth()
                                .height(150.dp)
                                .background(MaterialTheme.colorScheme.onSecondary)
                                .clickable {
                                    //  open house view Screen
                                    direction.navigateToHouseView(
                                        house.houseApartmentName, house.houseCategory
                                    )
                                }
                                .padding(8.dp)
                                .animateItemPlacement(),
                            snackbarHostState = snackbarHostState,
                            primaryColor = primaryColor,
                            tertiaryColor = tertiaryColor
                        )

                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            },
            state = rememberLazyListState(),
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary),
            horizontalAlignment = Alignment.CenterHorizontally
        )
    }
}














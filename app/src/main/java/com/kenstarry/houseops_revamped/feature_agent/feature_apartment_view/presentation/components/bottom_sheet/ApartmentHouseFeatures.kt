package com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.components.bottom_sheet

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FeaturedPlayList
import androidx.compose.material.icons.outlined.ImageSearch
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kenstarry.houseops_revamped.core.domain.model.HouseModel
import com.kenstarry.houseops_revamped.core.domain.model.events.CoreEvents
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants
import com.kenstarry.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.domain.model.AgentApartmentEvents
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.components.HouseFeatureCardItem
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.components.HouseFeaturesAlertDialog
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.model.ApartmentHouseFeaturesModel
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.utils.AgentApartmentConstants
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.viewmodel.AgentApartmentViewModel
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.HomePillBtns

@Composable
fun ApartmentHouseFeatures(
    house: HouseModel?,
    primaryColor: Color,
    tertiaryColor: Color
) {

    val coreVM = hiltViewModel<CoreViewModel>()
    val context = LocalContext.current
    val agentApartmentVM = hiltViewModel<AgentApartmentViewModel>()
    val listState = rememberLazyListState()

    AnimatedVisibility(
        visible = coreVM.alertDialogSelected.value?.dialogType
                == Constants.APARTMENT_FEATURES_ALERT_DIALOG &&
                coreVM.alertDialogSelected.value?.isDialogVisible == true
    ) {
        HouseFeaturesAlertDialog(
            primaryColor = primaryColor,
            tertiaryColor = tertiaryColor,
            onConfirm = {

                //  close alert dialog
                coreVM.onEvent(
                    CoreEvents.ToggleAlertDialog(
                        dialogType = Constants.APARTMENT_FEATURES_ALERT_DIALOG,
                        isDialogVisible = false
                    )
                )
            },
            onDismiss = {
                //  close alert dialog
                coreVM.onEvent(
                    CoreEvents.ToggleAlertDialog(
                        dialogType = Constants.APARTMENT_FEATURES_ALERT_DIALOG,
                        isDialogVisible = false
                    )
                )
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        //  features title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Features",
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
            )

            //  add image button
            HomePillBtns(
                icon = Icons.Outlined.ImageSearch,
                title = "Add Feature",
                primaryColor = primaryColor,
                tertiaryColor = tertiaryColor,
                onClick = {
                    //  open alert dialog
                    coreVM.onEvent(
                        CoreEvents.ToggleAlertDialog(
                            dialogType = Constants.APARTMENT_FEATURES_ALERT_DIALOG,
                            isDialogVisible = true
                        )
                    )
                }
            )

        }

        //  feature item
        if(house != null) {
            LazyRow(
                content = {
                    items(agentApartmentVM.selectedFeaturesState.listOfSelectedFeatures) { feature ->

                        //    feature card
                        HouseFeatureCardItem(
                            apartmentHouseFeaturesModel = feature,
                            primaryColor = primaryColor,
                            tertiaryColor = tertiaryColor,
                            isSelected = false,
                            onClick = {
                                //  open alert dialog
                                coreVM.onEvent(
                                    CoreEvents.ToggleAlertDialog(
                                        dialogType = Constants.APARTMENT_FEATURES_ALERT_DIALOG,
                                        isDialogVisible = true
                                    )
                                )
                            }
                        )
                    }
                },
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            )
        }

        AnimatedVisibility(visible = house == null && agentApartmentVM.selectedFeaturesState.listOfSelectedFeatures.isNotEmpty()) {
            LazyRow(
                content = {
                    items(agentApartmentVM.selectedFeaturesState.listOfSelectedFeatures) { feature ->

                        //    feature card
                        HouseFeatureCardItem(
                            apartmentHouseFeaturesModel = feature,
                            primaryColor = primaryColor,
                            tertiaryColor = tertiaryColor,
                            isSelected = false,
                            onClick = {
                                //  open alert dialog
                                coreVM.onEvent(
                                    CoreEvents.ToggleAlertDialog(
                                        dialogType = Constants.APARTMENT_FEATURES_ALERT_DIALOG,
                                        isDialogVisible = true
                                    )
                                )
                            }
                        )
                    }
                },
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            )
        }
    }
}
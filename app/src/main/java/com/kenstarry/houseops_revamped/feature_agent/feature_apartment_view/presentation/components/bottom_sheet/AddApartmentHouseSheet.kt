package com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.components.bottom_sheet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import com.kenstarry.houseops_revamped.core.domain.model.events.CoreEvents
import com.kenstarry.houseops_revamped.core.presentation.components.DoneCancelButtons
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants
import com.kenstarry.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.domain.model.AgentApartmentEvents
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.components.HouseCategoriesAlertDialog
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.viewmodel.AgentApartmentViewModel
import com.kenstarry.houseops_revamped.core.domain.model.HouseModel
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.utils.AgentApartmentConstants

@Composable
fun AddApartmentHouseSheet(
    house: HouseModel?,
    apartmentName: String,
    primaryColor: Color,
    tertiaryColor: Color,
    onDone: (house: HouseModel) -> Unit,
    onUpdate: (house: HouseModel) -> Unit,
    onCancel: () -> Unit
) {

    val agentApartmentVM = hiltViewModel<AgentApartmentViewModel>()
    val coreVM = hiltViewModel<CoreViewModel>()
    val imagesState = agentApartmentVM.selectedImagesState

    if (house != null) {
        //  set category
        agentApartmentVM.onEvent(
            AgentApartmentEvents.SelectHouseCategory(
                house.houseCategory
            )
        )

        //  set images
        agentApartmentVM.onEvent(
            AgentApartmentEvents.UpdateGalleryImages(
                house.houseImageUris
            )
        )

        //  set pricing
        agentApartmentVM.onEvent(
            AgentApartmentEvents.SelectHousePrice(
                house.housePrice
            )
        )

        //  set vacant units
        agentApartmentVM.onEvent(
            AgentApartmentEvents.SelectVacantUnits(
                house.houseUnits.toInt()
            )
        )

        //  set features
        house.houseFeatures.forEach { houseFeature ->
            agentApartmentVM.onEvent(
                AgentApartmentEvents.AddFeature(
                    AgentApartmentConstants.featureOptionsList.first { feature ->
                        houseFeature == feature.title
                    }
                )
            )
        }


        //  set description
        agentApartmentVM.onEvent(
            AgentApartmentEvents.SelectHouseDescription(
                house.houseDescription
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(26.dp)
    ) {

        //  house category dialog
        AnimatedVisibility(
            visible = coreVM.alertDialogSelected.value?.dialogType
                    == Constants.APARTMENT_HOUSE_CATEGORIES_ALERT_DIALOG &&
                    coreVM.alertDialogSelected.value?.isDialogVisible == true
        ) {
            HouseCategoriesAlertDialog(
                primaryColor = primaryColor,
                tertiaryColor = tertiaryColor,
                onConfirm = {
                    //  closee alert dialog for house category
                    coreVM.onEvent(
                        CoreEvents.ToggleAlertDialog(
                            dialogType = Constants.APARTMENT_HOUSE_CATEGORIES_ALERT_DIALOG,
                            isDialogVisible = false
                        )
                    )
                },
                onDismiss = {
                    //  closee alert dialog for house category
                    coreVM.onEvent(
                        CoreEvents.ToggleAlertDialog(
                            dialogType = Constants.APARTMENT_HOUSE_CATEGORIES_ALERT_DIALOG,
                            isDialogVisible = false
                        )
                    )
                }
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Add House",
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
            )
        }

        //  Apartment Name
        Row(
            modifier = Modifier
                .wrapContentSize(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Outlined.Apartment,
                contentDescription = "apartment icon",
                tint = primaryColor
            )

            Text(
                text = apartmentName,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
            )

        }

        //  house category
        ApartmentHouseCategory(
            house = house,
            primaryColor = primaryColor,
            tertiaryColor = tertiaryColor
        )

        //  apartment house images
        ApartmentHouseImages(
            houseModel = house,
            primaryColor = primaryColor,
            tertiaryColor = tertiaryColor
        )

        //  house price
        ApartmentHousePrice(
            house = house,
            primaryColor = primaryColor,
            tertiaryColor = tertiaryColor
        )

        //  vacant houses remaining
        ApartmentHouseVacants(
            primaryColor = primaryColor,
            tertiaryColor = tertiaryColor
        )

        //  house features
        ApartmentHouseFeatures(
            house = house,
            primaryColor = primaryColor,
            tertiaryColor = tertiaryColor
        )

        //  house description
        ApartmentHouseDescription(
            primaryColor = primaryColor,
            tertiaryColor = tertiaryColor
        )

        //  generate random id for the house
        val randomNum = "${(0..9).random()}" +
                "${(0..9).random()}" +
                "${(0..9).random()}"

        val houseModel = HouseModel(

            houseId = "${apartmentName.take(2)}-${agentApartmentVM.selectedHouseCategory.value}-$randomNum",

            houseCategory = if (agentApartmentVM.selectedHouseCategory.value == "Pick House Category")
                "Single"
            else
                agentApartmentVM.selectedHouseCategory.value,

            housePurchaseType = "For Rent",

            houseImageUris = house?.houseImageUris
                ?: emptyList(),

            houseUnits = house?.houseUnits
                ?: agentApartmentVM.selectedVacantUnits.value.toString(),

            houseFeatures = house?.houseFeatures
                ?: agentApartmentVM.selectedFeaturesState.listOfSelectedFeatures.map { it.title },

            houseDescription = house?.houseDescription
                ?: agentApartmentVM.selectedHouseDescription.value,

            houseLikes = "",
            houseApartmentName = apartmentName,
            housePrice = house?.housePrice
                ?: agentApartmentVM.selectedHousePrice.value,

            housePriceCategory = house?.housePriceCategory
                ?: "monthly",
            houseComments = "",
            houseUsersBooked = house?.houseUsersBooked
                ?: emptyList()
        )

        if (house != null) {

            Button(onClick = { onUpdate(houseModel) }) {
                Text(text = "Update")
            }
        } else {
            DoneCancelButtons(
                onDone = {
                    onDone(houseModel)
                },
                onCancel = onCancel
            )
        }


        Spacer(modifier = Modifier.height(24.dp))

    }

}
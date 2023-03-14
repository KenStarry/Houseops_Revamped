package com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.components.bottom_sheet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material.icons.outlined.ArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kenstarry.houseops_revamped.core.domain.model.events.CoreEvents
import com.kenstarry.houseops_revamped.core.presentation.components.DoneCancelButtons
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants
import com.kenstarry.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.components.HouseCategoriesAlertDialog
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.viewmodel.AgentApartmentViewModel
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.domain.model.HouseModel
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.HomePillBtns
import kotlin.random.Random

@Composable
fun AddApartmentHouseSheet(
    apartmentName: String,
    primaryColor: Color,
    tertiaryColor: Color,
    onDone: (house: HouseModel) -> Unit,
    onCancel: () -> Unit
) {

    val agentApartmentVM = hiltViewModel<AgentApartmentViewModel>()
    val coreVM = hiltViewModel<CoreViewModel>()
    val imagesState = agentApartmentVM.selectedImagesState

    var houseID by remember {
        mutableStateOf<String?>(null)
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
            primaryColor = primaryColor,
            tertiaryColor = tertiaryColor
        )

        //  apartment house images
        ApartmentHouseImages(
            primaryColor = primaryColor,
            tertiaryColor = tertiaryColor
        )

        //  house price
        ApartmentHousePrice(
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
            primaryColor = primaryColor,
            tertiaryColor = tertiaryColor
        )

        //  house description
        ApartmentHouseDescription(
            primaryColor = primaryColor,
            tertiaryColor = tertiaryColor
        )

        val house = HouseModel(

            houseId = houseID ?: "",

            houseCategory = if (agentApartmentVM.selectedHouseCategory.value == "Pick House Category")
                "Single"
            else
                agentApartmentVM.selectedHouseCategory.value,

            housePurchaseType = "For Rent",
            houseImageUris = imagesState.listOfSelectedImages.map { it.toString() },
            houseUnits = "",
            houseFeatures = agentApartmentVM.selectedFeaturesState.listOfSelectedFeatures.map { it.title },
            houseDescription = "",
            houseLikes = "",
            houseApartmentName = apartmentName,
            housePrice = agentApartmentVM.selectedHousePrice.value,
            housePriceCategory = "monthly",
            houseComments = "",
            houseUsersBooked = emptyList()
        )

        DoneCancelButtons(
            onDone = {

                //  generate random id for the house
                val randomNum = List(5) { (0..10).random() }

                houseID =
                    "${apartmentName.take(2)}-${agentApartmentVM.selectedHouseCategory.value}-$randomNum"

                houseID?.let {
                    onDone(house)
                }
            },
            onCancel = onCancel
        )

        Spacer(modifier = Modifier.height(24.dp))

    }

}
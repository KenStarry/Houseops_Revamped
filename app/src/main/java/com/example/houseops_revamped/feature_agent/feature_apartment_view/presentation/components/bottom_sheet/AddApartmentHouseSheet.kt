package com.example.houseops_revamped.feature_agent.feature_apartment_view.presentation.components.bottom_sheet

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.houseops_revamped.core.presentation.components.DoneCancelButtons
import com.example.houseops_revamped.feature_agent.feature_apartment_view.presentation.components.bottom_sheet.ApartmentHouseImages
import com.example.houseops_revamped.feature_agent.feature_apartment_view.presentation.components.bottom_sheet.ApartmentHousePrice
import com.example.houseops_revamped.feature_agent.feature_apartment_view.presentation.viewmodel.AgentApartmentViewModel
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.domain.model.HouseModel
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.HomePillBtns

@Composable
fun AddApartmentHouseSheet(
    apartmentName: String,
    primaryColor: Color,
    tertiaryColor: Color,
    onDone: (house: HouseModel) -> Unit,
    onCancel: () -> Unit
) {

    val agentApartmentVM: AgentApartmentViewModel = hiltViewModel()
    val imagesState = agentApartmentVM.selectedImagesState

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

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

        HomePillBtns(
            icon = Icons.Outlined.Apartment,
            title = apartmentName,
            primaryColor = primaryColor,
            tertiaryColor = tertiaryColor,
            onClick = {}
        )

        ApartmentHouseImages(
            primaryColor = primaryColor,
            tertiaryColor = tertiaryColor
        )

        //  house price
        ApartmentHousePrice(
            primaryColor = primaryColor,
            tertiaryColor = tertiaryColor
        )

        val house = HouseModel(
            houseId = "lu-single-573",
            houseCategory = "single",
            housePurchaseType = "For Rent",
            houseImageUris = imagesState.listOfSelectedImages.map { it.toString() },
            houseUnits = "",
            houseFeatures = emptyList(),
            houseDescription = "",
            houseLikes = "",
            houseApartmentName = apartmentName,
            housePrice = "",
            housePriceCategory = "monthly",
            houseComments = "",
            houseUsersBooked = emptyList()
        )

        DoneCancelButtons(
            onDone = { onDone(house) },
            onCancel = onCancel
        )

        Spacer(modifier = Modifier.height(24.dp))

    }

}
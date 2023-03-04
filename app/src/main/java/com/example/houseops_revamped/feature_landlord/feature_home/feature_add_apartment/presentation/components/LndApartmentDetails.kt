package com.example.houseops_revamped.feature_landlord.feature_home.feature_add_apartment.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.feature_authentication.presentation.login.presentation.components.CustomTextField
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.presentation.viewmodel.LndAddApartmentViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LndApartmentDetails(
    modifier: Modifier = Modifier,
    lndAddApartmentVM: LndAddApartmentViewModel,
    primaryColor: Color,
    tertiaryColor: Color,
    onLocationClicked: () -> Unit,
    onHouseFeaturesClicked: () -> Unit
) {

    val context = LocalContext.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        //  add apartment
        CustomTextField(
            textFieldValue = lndAddApartmentVM.apartmentName.value,
            startIcon = Icons.Outlined.Apartment,
            endIcon = null,
            placeholder = "Apartment Name",
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text,
            primaryColor = primaryColor,
            tertiaryColor = tertiaryColor,
            fontSize = MaterialTheme.typography.titleSmall.fontSize,
            fontWeight = FontWeight.Bold,
            onInput = {
                //  verify the details
                lndAddApartmentVM.apartmentName.value = it
            }
        )

        //  location section
        LocationSection(
            lndAddApartmentVM = lndAddApartmentVM,
            onLocationClicked = onLocationClicked,
            primaryColor = primaryColor,
            tertiaryColor = tertiaryColor
        )

        //  caretaker id section
        CaretakerIdSection(
            lndAddApartmentVM = lndAddApartmentVM,
            primaryColor = primaryColor,
            tertiaryColor = tertiaryColor
        )

        //  apartment features section
        ApartmentFeaturesSection(
            lndAddApartmentVM = lndAddApartmentVM,
            onHouseFeaturesClicked = onHouseFeaturesClicked,
            primaryColor = primaryColor,
            tertiaryColor = tertiaryColor
        )


        //  apartment features e.g security type, social ammenities... etc (list)
        //  apartment terms and conditions (list)

    }
}















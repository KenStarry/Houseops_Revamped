package com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_add_apartment.presentation.components

import androidx.compose.animation.AnimatedVisibility
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_add_apartment.presentation.viewmodel.LndAddApartmentViewModel
import com.kenstarry.houseops_revamped.core.presentation.components.OptionsToggle
import com.kenstarry.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.houseops_revamped.feature_authentication.presentation.login.presentation.components.CustomTextField
import com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_add_apartment.presentation.utils.LndApartmentConstants

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LndApartmentDetails(
    modifier: Modifier = Modifier,
    lndAddApartmentVM: LndAddApartmentViewModel,
    primaryColor: Color,
    tertiaryColor: Color,
    onLocationClicked: () -> Unit,
    onHouseFeaturesClicked: () -> Unit,
    onAddConditionClicked: () ->  Unit
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val context = LocalContext.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        //  for sale / rent apartment type
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OptionsToggle(
                optionsList = LndApartmentConstants.optionsList,
                primaryColor = primaryColor,
                tertiaryColor = tertiaryColor,
                onSelectOption = { option ->

                }
            )

            AnimatedVisibility(
                visible =
                coreVM.chosenOptionToggle.value == LndApartmentConstants.optionsList[1]
            ) {
                
                Spacer(modifier = Modifier.height(24.dp))

                //  price of apartment
                CustomTextField(
                    textFieldValue = lndAddApartmentVM.apartmentPrice.value,
                    startIcon = Icons.Outlined.CurrencyExchange,
                    endIcon = null,
                    placeholder = "price in Ksh.",
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text,
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    onInput = {
                        //  verify the details
                        lndAddApartmentVM.apartmentPrice.value = it
                    }
                )

            }
        }

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
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
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

        //  apartment features section
        ApartmentFeaturesSection(
            lndAddApartmentVM = lndAddApartmentVM,
            onHouseFeaturesClicked = onHouseFeaturesClicked,
            primaryColor = primaryColor,
            tertiaryColor = tertiaryColor
        )

        //  apartment terms and conditions (list)
        ApartmentTermsSection(
            lndAddApartmentVM = lndAddApartmentVM,
            onAddConditionClicked = onAddConditionClicked,
            primaryColor = primaryColor,
            tertiaryColor = tertiaryColor
        )
    }
}















package com.example.houseops_revamped.feature_landlord.feature_home.feature_add_apartment.presentation.components.bottomsheets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Title
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.core.presentation.components.DoneCancelButtons
import com.example.houseops_revamped.feature_authentication.presentation.login.presentation.components.CustomTextField
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.presentation.viewmodel.LndAddApartmentViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FeaturesBottomSheet(
    lndAddApartmentVM: LndAddApartmentViewModel,
    onDone: (
        title: String,
        description: String
    ) -> Unit,
    onCancel: () -> Unit,
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        Text(
            text = "Add Feature",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
        )

        //  title
        CustomTextField(
            textFieldValue = lndAddApartmentVM.featureTitle.value,
            startIcon = Icons.Outlined.Title,
            endIcon = null,
            placeholder = "e.g Security, Parking etc.",
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text,
            primaryColor = MaterialTheme.colorScheme.primary,
            tertiaryColor = MaterialTheme.colorScheme.tertiary,
            fontSize = MaterialTheme.typography.titleSmall.fontSize,
            fontWeight = FontWeight.Bold,
            onInput = {
                lndAddApartmentVM.featureTitle.value = it
            }
        )

        //  description
        CustomTextField(
            textFieldValue = lndAddApartmentVM.featureDescription.value,
            startIcon = Icons.Outlined.Description,
            endIcon = null,
            placeholder = "Feature description",
            imeAction = ImeAction.Default,
            keyboardType = KeyboardType.Text,
            primaryColor = MaterialTheme.colorScheme.primary,
            tertiaryColor = MaterialTheme.colorScheme.tertiary,
            singleLine = false,
            maxLines = Int.MAX_VALUE,
            onInput = {
                lndAddApartmentVM.featureDescription.value = it
            }
        )

        DoneCancelButtons(
            onDone = {
                keyboardController?.hide()
                onDone(
                    lndAddApartmentVM.featureTitle.value,
                    lndAddApartmentVM.featureDescription.value
                )
            },
            onCancel = {
                keyboardController?.hide()
                onCancel()
            }
        )

    }

}
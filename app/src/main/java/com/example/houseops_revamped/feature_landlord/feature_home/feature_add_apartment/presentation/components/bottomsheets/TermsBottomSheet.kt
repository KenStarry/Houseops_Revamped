package com.example.houseops_revamped.feature_landlord.feature_home.feature_add_apartment.presentation.components.bottomsheets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Title
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun TermsBottomSheet(
    lndAddApartmentVM: LndAddApartmentViewModel,
    primaryColor: Color,
    tertiaryColor: Color,
    onDone: (
        title: String,
        description: String
    ) -> Unit,
    onCancel: () -> Unit
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
            text = "Terms and Conditions",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
        )

        //  title
        CustomTextField(
            textFieldValue = lndAddApartmentVM.termsTitle.value,
            startIcon = Icons.Outlined.Title,
            endIcon = null,
            placeholder = "e.g Deposit, Rent Payment...",
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text,
            primaryColor = primaryColor,
            tertiaryColor = tertiaryColor,
            fontSize = MaterialTheme.typography.titleSmall.fontSize,
            fontWeight = FontWeight.Bold,
            onInput = {
                lndAddApartmentVM.termsTitle.value = it
            }
        )

        //  description
        CustomTextField(
            textFieldValue = lndAddApartmentVM.termsDescription.value,
            startIcon = Icons.Outlined.Description,
            endIcon = null,
            placeholder = "Terms description",
            imeAction = ImeAction.Default,
            keyboardType = KeyboardType.Text,
            primaryColor = primaryColor,
            tertiaryColor = tertiaryColor,
            singleLine = false,
            maxLines = Int.MAX_VALUE,
            onInput = {
                lndAddApartmentVM.termsDescription.value = it
            }
        )

        DoneCancelButtons(
            onDone = {
                keyboardController?.hide()
                onDone(
                    lndAddApartmentVM.termsTitle.value,
                    lndAddApartmentVM.termsDescription.value
                )
            },
            onCancel = {
                keyboardController?.hide()
                onCancel()
            }
        )

    }
}
































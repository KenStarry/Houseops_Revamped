package com.example.houseops_revamped.feature_landlord.feature_home.feature_add_apartment.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Numbers
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.feature_authentication.presentation.login.presentation.components.CustomTextField
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.presentation.viewmodel.LndAddApartmentViewModel

@Composable
fun CaretakerIdSection(
    lndAddApartmentVM: LndAddApartmentViewModel,
    primaryColor: Color,
    tertiaryColor: Color
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        //  section items
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    color = MaterialTheme.colorScheme.onSecondary,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            //  caretaker id
            CustomTextField(
                textFieldValue = lndAddApartmentVM.apartmentCaretakerId.value,
                startIcon = Icons.Outlined.Numbers,
                endIcon = null,
                placeholder = "Caretaker ID Number",
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number,
                primaryColor = primaryColor,
                tertiaryColor = tertiaryColor,
                containerColor = MaterialTheme.colorScheme.onSecondary,
                onInput = {
                    //  verify the details
                    lndAddApartmentVM.apartmentCaretakerId.value = it
                }
            )

        }
    }
}























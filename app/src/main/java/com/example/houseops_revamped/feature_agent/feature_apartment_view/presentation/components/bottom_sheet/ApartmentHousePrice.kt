package com.example.houseops_revamped.feature_agent.feature_apartment_view.presentation.components.bottom_sheet

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PriceChange
import androidx.compose.material.icons.outlined.PriceCheck
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.feature_authentication.presentation.login.presentation.components.CustomTextField
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.HomePillBtns

@Composable
fun ApartmentHousePrice(
    primaryColor: Color,
    tertiaryColor: Color
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .weight(2f),
            contentAlignment = Alignment.Center
        ) {
            CustomTextField(
                startIcon = Icons.Outlined.PriceCheck,
                endIcon = null,
                placeholder = "House Price",
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number,
                primaryColor = primaryColor,
                tertiaryColor = tertiaryColor,
                onInput = {
                    //  add commas to price
                }
            )
        }

        Box(
            modifier = Modifier
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            HomePillBtns(
                icon = Icons.Outlined.PriceChange,
                title = "monthly",
                primaryColor = primaryColor,
                tertiaryColor = tertiaryColor,
                onClick = {},
                paddingHorizontal = 8.dp
            )
        }

    }
}
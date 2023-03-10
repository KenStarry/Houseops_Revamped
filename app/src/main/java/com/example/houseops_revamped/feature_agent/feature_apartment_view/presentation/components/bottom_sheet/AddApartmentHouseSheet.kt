package com.example.houseops_revamped.feature_agent.feature_apartment_view.presentation.components

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
import com.example.houseops_revamped.feature_agent.feature_apartment_view.presentation.components.bottom_sheet.ApartmentHousePrice
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.HomePillBtns

@Composable
fun AddApartmentHouseSheet(
    apartmentName: String,
    primaryColor: Color,
    tertiaryColor: Color
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp)
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

        //  house price
        ApartmentHousePrice(
            primaryColor = primaryColor,
            tertiaryColor = tertiaryColor
        )

    }

}
package com.example.houseops_revamped.feature_tenant.feature_home.house_view_screen.presentation.components.house_view_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.OpenInFull
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.domain.model.HouseModel
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.HomePillBtns

@Composable
fun DetailsUsersBooked(
    house: HouseModel,
    primaryColor: Color,
    tertiaryColor: Color,
    onClicked: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.onPrimary),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = if (house.houseUsersBooked.size == 1)
                "${house.houseUsersBooked.size} user booked!"
            else
                "${house.houseUsersBooked.size} users booked!",
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
        )

        Spacer(modifier = Modifier.width(16.dp))

        HomePillBtns(
            icon = Icons.Outlined.OpenInFull,
            title = "See all",
            primaryColor = primaryColor,
            tertiaryColor = tertiaryColor,
            onClick = { onClicked() }
        )
    }

}
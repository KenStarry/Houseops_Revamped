package com.example.houseops_revamped.feature_home.house_view_screen.presentation.components.house_view_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Expand
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel
import com.example.houseops_revamped.feature_home.home_screen.presentation.components.HomePillBtns

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
            .background(MaterialTheme.colorScheme.onSecondary),
        horizontalArrangement = Arrangement.SpaceBetween
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

        HomePillBtns(
            icon = Icons.Outlined.Expand,
            title = "See all",
            primaryColor = primaryColor,
            tertiaryColor = tertiaryColor,
            onClick = { onClicked() }
        )
    }

}
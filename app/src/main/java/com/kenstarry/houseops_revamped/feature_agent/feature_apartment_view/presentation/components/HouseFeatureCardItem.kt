package com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.model.ApartmentHouseFeaturesModel

@Composable
fun HouseFeatureCardItem(
    apartmentHouseFeaturesModel: ApartmentHouseFeaturesModel,
    primaryColor: Color,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .wrapContentSize(),
        shape = RoundedCornerShape(16.dp),

        colors = CardDefaults.cardColors(
            containerColor = if (isSelected)
                primaryColor
            else
                MaterialTheme.colorScheme.onPrimary,

            contentColor = if (isSelected)
                primaryColor
            else
                MaterialTheme.colorScheme.onPrimary
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Column(
            modifier = Modifier
                .wrapContentSize()
                .defaultMinSize(
                    minWidth = 80.dp
                )
                .clickable { onClick() }
                .padding(
                    horizontal = 8.dp,
                    vertical = 16.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            //  icon
            Icon(
                imageVector = apartmentHouseFeaturesModel.icon,
                contentDescription = "feature icon",

                tint = if (isSelected)
                    Color.White
                else
                    primaryColor,

                modifier = Modifier
                    .size(16.dp)
            )

            //  tile
            Text(
                text = apartmentHouseFeaturesModel.title,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = FontWeight.SemiBold,
                color = if (isSelected)
                    Color.White
                else
                    MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f)
            )

        }

    }


}
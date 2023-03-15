package com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.presentation.components.house_view_details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kenstarry.houseops_revamped.core.domain.model.Apartment
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.HomePillBtns

@Composable
fun DetailsTitle(
    apartmentDetails: Apartment?,
    houseCategory: String,
    primaryColor: Color,
    tertiaryColor: Color
) {

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Outlined.Apartment,
                    contentDescription = "Apartment",
                    tint = primaryColor
                )

                Text(
                    text = apartmentDetails?.apartmentName ?: "",
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = houseCategory,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f)
                )
                
                Spacer(modifier = Modifier.width(16.dp))

                //  location
                HomePillBtns(
                    icon = Icons.Outlined.LocationOn,
                    title = apartmentDetails?.apartmentLocation?.address ?: "",
                    onClick = {},
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor
                )

            }

        }

    }
}

















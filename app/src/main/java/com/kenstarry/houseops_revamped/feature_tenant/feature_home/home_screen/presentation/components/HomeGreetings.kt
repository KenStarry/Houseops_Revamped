package com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MyLocation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kenstarry.houseops_revamped.core.domain.model.LocationAddresses
import com.kenstarry.houseops_revamped.core.presentation.components.permission_handling.RequestPermission
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.utils.HomeUtils

@Composable
fun HomeGreetings(
    modifier: Modifier = Modifier,
    userName: String,
    location: LocationAddresses?,
    onRequestPermission: () -> Unit
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = "welcome back!",
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = HomeUtils.getNthWord(userName, 1) +
                        " ${HomeUtils.getNthWord(userName, 2).firstOrNull() ?: ""}.",
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

            Row(
                modifier = Modifier
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Outlined.MyLocation,
                    contentDescription = "My location",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.5f)
                )

                Text(
                    text = if (location == null)
                        "No location"
                    else
                        "${location.locality}, ${location.country}",
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f),
                    modifier = Modifier
                        .clickable {
                            //  request location permission
                            if (location == null)
                                onRequestPermission()
                        }
                )

            }

        }

    }

}
package com.example.houseops_revamped.feature_home.presentation.components.house_item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun HouseLocation() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Icon(
            imageVector = Icons.Outlined.LocationOn,
            contentDescription = "Location",
            tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f),
            modifier = Modifier
                .size(16.dp)
        )

        Text(
            text = "Westlands, Nairobi",
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

    }
}
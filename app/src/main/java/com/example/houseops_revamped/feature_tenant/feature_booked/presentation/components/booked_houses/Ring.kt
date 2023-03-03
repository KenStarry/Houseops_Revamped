package com.example.houseops_revamped.feature_tenant.feature_booked.presentation.components.booked_houses

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun Ring() {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .size(25.dp)
            .background(MaterialTheme.colorScheme.onPrimary)
            .border(
                width = 3.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = CircleShape
            )
    )
}
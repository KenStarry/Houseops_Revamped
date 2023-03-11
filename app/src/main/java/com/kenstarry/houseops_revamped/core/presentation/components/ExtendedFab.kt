package com.kenstarry.houseops_revamped.core.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ExtendedFab(
    icon: ImageVector?,
    title: String,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    onFabClicked: () -> Unit
) {

    ExtendedFloatingActionButton(
        onClick = { onFabClicked() },
        contentColor = Color.White,
        containerColor = containerColor
    ) {

        icon?.let {
            Icon(
                imageVector = it,
                contentDescription = "Fab Icon",
                tint = Color.White.copy(alpha = 0.8f)
            )
        }
        
        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = title,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = FontWeight.Bold
        )

    }
}











































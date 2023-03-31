package com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.GpsOff
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kenstarry.houseops_revamped.core.presentation.components.CustomAlertDialog

@Composable
fun GPSAlertDialog(
    primaryColor: Color,
    tertiaryColor: Color,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {

    CustomAlertDialog(
        icon = Icons.Outlined.GpsOff,
        primaryColor = primaryColor,
        tertiaryColor = tertiaryColor,
        title = "Enable GPS Location",
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "GPS is disabled. Open location settings?",
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )

            }
        },
        onConfirm = onConfirm,
        onDismiss = onDismiss
    )
}
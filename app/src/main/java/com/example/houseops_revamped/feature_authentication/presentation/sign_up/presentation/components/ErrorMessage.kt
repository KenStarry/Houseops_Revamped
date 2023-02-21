package com.example.houseops_revamped.feature_authentication.presentation.sign_up.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ErrorMessage(
    modifier: Modifier = Modifier,
    message: String?
) {

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(
                    color = MaterialTheme.colorScheme.error.copy(alpha = 0.1f),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(16.dp / 2)
                    .background(
                        color = MaterialTheme.colorScheme.error,
                        shape = CircleShape
                    )
            )
        }

        //  message
        message?.let {
            Text(
                text = message,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}






















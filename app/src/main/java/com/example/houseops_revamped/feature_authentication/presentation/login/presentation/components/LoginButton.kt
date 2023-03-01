package com.example.houseops_revamped.feature_authentication.presentation.login.presentation.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun LoginButton(
    primaryColor: Color,
    tertiaryColor: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor =  tertiaryColor,
            disabledContainerColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.3f),
            disabledContentColor = Color.White.copy(alpha = 0.3f)
        ),
        enabled = true
    ) {
        Text(
            text = "Login",
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
        )
    }
}
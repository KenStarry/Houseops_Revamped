package com.example.houseops_revamped.feature_authentication.login.presentation.components

import android.util.Log
import android.widget.Toast
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.core.utils.Constants
import com.example.houseops_revamped.feature_authentication.login.domain.model.LoginEvents

@Composable
fun LoginButton(
    onClick: () -> Unit
) {
    Button(
        onClick = {
onClick()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.3f),
            disabledContentColor = Color.White.copy(alpha = 0.3f)
        ),
        enabled = true
    ) {
        Text(
            text = "Login",
            fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
            color = Color.White
        )
    }
}
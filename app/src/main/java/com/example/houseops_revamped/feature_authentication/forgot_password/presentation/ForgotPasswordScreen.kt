package com.example.houseops_revamped.feature_authentication.forgot_password.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.R
import com.example.houseops_revamped.core.presentation.components.CustomLargeAppBar
import com.example.houseops_revamped.core.presentation.components.Lottie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen() {

    Scaffold(
        topBar = {
            CustomLargeAppBar(
                title = "Verification Complete!",
                onBackPressed = {}
            )
        }
    ) { contentPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Lottie(
                rawFile = R.raw.success_lottie,
                isPlaying = true,
                iterations = 1,
                modifier = Modifier
                    .size(100.dp)
            )

            Text(
                text = "Follow instructions in your email to reset your password.",
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
            )

        }

    }
}
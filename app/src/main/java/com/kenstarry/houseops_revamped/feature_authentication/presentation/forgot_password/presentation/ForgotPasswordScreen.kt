package com.kenstarry.houseops_revamped.feature_authentication.presentation.forgot_password.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.kenstarry.houseops_revamped.core.presentation.components.CustomLargeAppBar
import com.kenstarry.houseops_revamped.core.presentation.components.Lottie
import com.kenstarry.houseops_revamped.navigation.Direction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    navHostController: NavHostController
) {

    val direction = Direction(navHostController)

    Scaffold(
        topBar = {
            CustomLargeAppBar(
                title = "Verification Complete!",
                onBackPressed = {
                    direction.navigateUp()
                }
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
                rawFile = com.kenstarry.houseops_revamped.R.raw.success_lottie,
                isPlaying = true,
                iterations = 1,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxSize(0.8f)
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
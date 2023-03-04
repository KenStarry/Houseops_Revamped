package com.example.houseops_revamped.core.presentation.components

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.core.domain.model.events.CoreEvents
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.HomePillBtns

@Composable
fun EmailVerificationMessage(
    coreVM: CoreViewModel = hiltViewModel(),
    onSuccess: () -> Unit,
    onFailure: () -> Unit
) {

    var isLoading by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Verify Email",
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
            )

            coreVM.currentUser?.email?.let { user ->

                Spacer(modifier = Modifier.height(8.dp))

                HomePillBtns(
                    icon = Icons.Outlined.Email,
                    title = user,
                    primaryColor = MaterialTheme.colorScheme.primary,
                    tertiaryColor = MaterialTheme.colorScheme.tertiary,
                    onClick = {}
                )

            }

        }

        Spacer(modifier = Modifier.height(24.dp))

        Lottie(
            rawFile = com.example.houseops_revamped.R.raw.email_verification,
            isPlaying = true,
            iterations = 2,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Take a moment to verify your email address before you can proceed.",
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (isLoading) {

            LoadingCircle(
                primaryColor = MaterialTheme.colorScheme.primary,
                tertiaryColor = MaterialTheme.colorScheme.tertiary
            )

        } else {
            Button(onClick = {

                isLoading = true

                coreVM.onEvent(CoreEvents.SendVerificationEmail(
                    response = {
                        when (it) {
                            is Response.Success -> {
                                isLoading = false
                                onSuccess()
                            }
                            is Response.Failure -> {
                                isLoading = false
                                onFailure()
                            }
                        }
                    }
                ))
            }) {
                Text(
                    text = "Send Verification Email",
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }

    }
}






























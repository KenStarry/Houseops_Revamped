package com.kenstarry.houseops_revamped.feature_admin.feature_home.presentation.components.bottomsheets

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import com.kenstarry.houseops_revamped.R
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.core.domain.model.events.CoreEvents
import com.kenstarry.houseops_revamped.core.presentation.components.LoadingCircle
import com.kenstarry.houseops_revamped.core.presentation.components.Lottie
import com.kenstarry.houseops_revamped.core.presentation.viewmodel.CoreViewModel

@Composable
fun AdminVerificationSheet(
    coreViewModel: CoreViewModel,
    onLogout: () -> Unit
) {

    var isLoading by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Email Sent Successfully!",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "After verification, login to verify your account.",
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        //  success lottie
        Lottie(
            rawFile = R.raw.success_lottie,
            isPlaying = true,
            iterations = 1,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Logout from account",
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                imageVector = Icons.Outlined.ArrowRight,
                contentDescription = "right arrow"
            )

            Spacer(modifier = Modifier.width(8.dp))

            if (isLoading) {

                LoadingCircle(
                    primaryColor = MaterialTheme.colorScheme.primary,
                    tertiaryColor = MaterialTheme.colorScheme.tertiary
                )

            } else {

                Button(onClick = {

                    isLoading = true

                    //  logout
                    coreViewModel.onEvent(
                        CoreEvents.LogoutUser(
                        response = {
                            when (it) {
                                is Response.Success -> {
                                    isLoading = false
                                    onLogout()
                                }
                                is Response.Failure -> {
                                    isLoading = false
                                    Toast.makeText(
                                        context,
                                        "Couldn't log you out",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    ))
                }) {
                    Text(
                        text = "Logout",
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }

        }

    }
}

















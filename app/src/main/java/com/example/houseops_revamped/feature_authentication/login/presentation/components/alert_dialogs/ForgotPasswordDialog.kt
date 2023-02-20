package com.example.houseops_revamped.feature_authentication.login.presentation.components.alert_dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.QuestionMark
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.core.presentation.components.CustomAlertDialog

@Composable
fun ForgotPasswordDialog(
    primaryColor: Color,
    tertiaryColor: Color,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {

    CustomAlertDialog(
        icon = Icons.Outlined.QuestionMark,
        primaryColor = primaryColor,
        tertiaryColor = tertiaryColor,
        title = "Reset Password",
        content = {

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {

                Text(
                    text = "Enter email address",
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(40.dp)
                            .background(tertiaryColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Email,
                            contentDescription = "icon",
                            tint = primaryColor
                        )
                    }

                    //  email address input field


                }

            }
        },
        onConfirm = { onConfirm() },
        onDismiss = { onDismiss() })
}
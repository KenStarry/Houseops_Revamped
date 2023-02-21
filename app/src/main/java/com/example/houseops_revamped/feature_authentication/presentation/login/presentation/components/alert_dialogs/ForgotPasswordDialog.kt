package com.example.houseops_revamped.feature_authentication.presentation.login.presentation.components.alert_dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.QuestionMark
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.core.presentation.components.CustomAlertDialog
import com.example.houseops_revamped.feature_authentication.presentation.login.presentation.components.CustomTextField

@Composable
fun ForgotPasswordDialog(
    primaryColor: Color,
    tertiaryColor: Color,
    onConfirm: (email: String) -> Unit,
    onDismiss: () -> Unit
) {

    var emailInput by remember {
        mutableStateOf("")
    }

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

                    //  email address input field
                    Column {
                        //  email address
                        CustomTextField(
                            startIcon = null,
                            endIcon = null,
                            placeholder = "Email Address",
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Email,
                            primaryColor = primaryColor,
                            tertiaryColor = tertiaryColor
                        ) {
                            emailInput = it
                        }
                    }

                }

            }
        },
        onConfirm = { onConfirm(emailInput) },
        onDismiss = { onDismiss() })
}
package com.example.houseops_revamped.feature_authentication.presentation.login.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp

//  email input textfield
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.CustomTextField(
    textFieldValue: String = "",
    startIcon: ImageVector?,
    endIcon: ImageVector?,
    placeholder: String,
    imeAction: ImeAction,
    keyboardType: KeyboardType,
    primaryColor: Color,
    tertiaryColor: Color,
    isPassword: Boolean = false,
    onInput: (input: String) -> Unit
) {

    var textFieldState by remember {
        mutableStateOf(textFieldValue)
    }

    var passVisibilityState by remember {
        mutableStateOf(false)
    }

    val icon = if (passVisibilityState)
        Icons.Outlined.Visibility
    else
        Icons.Outlined.VisibilityOff

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(40.dp)
                .background(tertiaryColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = startIcon ?: Icons.Outlined.AlternateEmail,
                contentDescription = "icon",
                tint = primaryColor
            )
        }

        if (isPassword) {
            TextField(
                value = textFieldState,
                onValueChange = {
                    textFieldState = it
                    onInput(textFieldState)
                },
                placeholder = {
                    Text(text = placeholder)
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    imeAction = imeAction,
                    keyboardType = keyboardType
                ),
                modifier = Modifier
                    .fillMaxWidth(),

                singleLine = true,

                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    cursorColor = primaryColor,
                    unfocusedIndicatorColor = tertiaryColor,
                    focusedIndicatorColor = primaryColor
                ),

                trailingIcon = {
                    IconButton(onClick = {
                        passVisibilityState = !passVisibilityState
                    }) {
                        Icon(
                            imageVector = icon,
                            contentDescription = "Trailing Icon"
                        )
                    }
                },

                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.Normal
                ),

                visualTransformation = if (passVisibilityState)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation()
            )
        } else {
            TextField(
                value = textFieldState,
                onValueChange = {
                    textFieldState = it
                    onInput(textFieldState)
                },
                placeholder = {
                    Text(text = placeholder)
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    imeAction = imeAction,
                    keyboardType = keyboardType
                ),
                modifier = Modifier
                    .fillMaxWidth(),

                singleLine = true,

                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    cursorColor = primaryColor,
                    unfocusedIndicatorColor = tertiaryColor,
                    focusedIndicatorColor = primaryColor
                ),

                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.Normal
                )
            )
        }

    }
}
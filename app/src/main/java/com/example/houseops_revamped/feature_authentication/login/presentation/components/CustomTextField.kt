package com.example.houseops_revamped.feature_authentication.login.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

//  email input textfield
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.CustomTextField(
    startIcon: ImageVector?,
    endIcon: ImageVector?,
    placeholder: String,
    imeAction: ImeAction,
    keyboardType: KeyboardType,
    primaryColor: Color,
    tertiaryColor: Color,
    onInput: (input: String) -> Unit
) {

    var textFieldState by remember {
        mutableStateOf("")
    }

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
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = FontWeight.Normal
            )
        )

    }
}
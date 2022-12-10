package com.example.houseops_revamped.custom_components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import com.example.houseops_revamped.models.TopbarDropdown

//  custom form textfields
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.FormTextField(
    startIcon: ImageVector?,
    endIcon: ImageVector?,
    placeholder: String,
    imeAction: ImeAction,
    keyboardType: KeyboardType,
    onInputFieldChanged: (input: String) -> Unit
) {

    var apartmentTextFieldState by remember {
        mutableStateOf("")
    }

    TextField(
        value = apartmentTextFieldState,
        onValueChange = {
            apartmentTextFieldState = it
            onInputFieldChanged(apartmentTextFieldState)
        },
        leadingIcon = {
            if (startIcon != null) {
                Icon(
                    imageVector = startIcon,
                    contentDescription = "Leading Icon",
                    tint = Color.LightGray.copy(alpha = 0.9f)
                )
            }
        },
        trailingIcon = {
            if (endIcon != null) {
                Icon(
                    imageVector = endIcon,
                    contentDescription = "Trailing Icon",
                    tint = Color.LightGray.copy(alpha = 0.9f)
                )
            }
        },
        placeholder = {
            Text(
                text = placeholder,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
            )
        },
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            imeAction = imeAction,
            keyboardType = keyboardType
        ),
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.Start),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            cursorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = Color.LightGray.copy(alpha = 0.3f),
            focusedIndicatorColor = MaterialTheme.colorScheme.primary
        )
    )
}

//  dropdown menus
@Composable
fun CustomDropdownMenuItem(
    dropdownItems: List<TopbarDropdown>
) {

    dropdownItems.forEach { item ->

        DropdownMenuItem(
            text = {
                Text(text = item.title)
            },
            leadingIcon = {
                Icon(
                    imageVector = item.icon!!,
                    contentDescription = "Dropdown icon"
                )
            },
            onClick = { item.onItemClicked() }
        )
    }

}
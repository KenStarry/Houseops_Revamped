package com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CategoriesAlertDialogItem(
    houseCategory: String,
    primaryColor: Color,
    tertiaryColor: Color,
    isSelected: Boolean,
    onRadioButtonClicked: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 16.dp)
            .selectable(
                selected = isSelected,
                onClick = onRadioButtonClicked
            ),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        RadioButton(
            selected = isSelected,
            onClick = onRadioButtonClicked,
            colors = RadioButtonDefaults.colors(
                selectedColor = primaryColor,
                unselectedColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f)
            )
        )

        Text(
            text = houseCategory,
            fontSize = MaterialTheme.typography.titleSmall.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(
                alpha = 0.8f
            )
        )

    }

}
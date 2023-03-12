package com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.House
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.kenstarry.houseops_revamped.core.presentation.components.CustomAlertDialog

@Composable
fun HouseCategoriesAlertDialog(
    primaryColor: Color,
    tertiaryColor: Color,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {

    CustomAlertDialog(
        icon = Icons.Outlined.House,
        primaryColor = primaryColor,
        tertiaryColor = tertiaryColor,
        title = "Choose House Category",
        content = {},
        onConfirm = onConfirm,
        onDismiss = onDismiss
    )
}
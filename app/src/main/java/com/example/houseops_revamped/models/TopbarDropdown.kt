package com.example.houseops_revamped.models

import androidx.compose.ui.graphics.vector.ImageVector

data class TopbarDropdown(
    val title: String,
    val icon: ImageVector?,
    val onItemClicked: () -> Unit
) {
    constructor() : this("", null, {})
}

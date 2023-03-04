package com.example.houseops_revamped.core.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

data class TopbarDropdown(
    val title: String,
    val icon: ImageVector?,
    val onItemClicked: () -> Unit
) {
    constructor() : this("", null, {})
}

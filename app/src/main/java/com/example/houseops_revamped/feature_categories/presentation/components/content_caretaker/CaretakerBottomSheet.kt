package com.example.houseops_revamped.feature_categories.presentation.components.content_caretaker

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.houseops_revamped.core.domain.model.Caretaker

@Composable
fun CaretakerBottomSheet(
    caretaker: Caretaker?
) {
    
    Text(text = caretaker?.caretakerName ?: "none")
}
package com.example.houseops_revamped.feature_categories.domain.model

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import kotlinx.coroutines.CoroutineScope

sealed class CategoryEvents<out T> {

    data class ChangeBottomSheetContent(
        val categoryTitle: String
    ) : CategoryEvents<Nothing>()

    data class OpenBottomSheet<out T> @OptIn(ExperimentalMaterialApi::class) constructor(
        val state: ModalBottomSheetState,
        val scope: CoroutineScope,
        val bottomSheetData: T
    ) : CategoryEvents<T>()

    data class CloseBottomSheet @OptIn(ExperimentalMaterialApi::class) constructor(
        val state: ModalBottomSheetState,
        val scope: CoroutineScope
    ) : CategoryEvents<Nothing>()

}

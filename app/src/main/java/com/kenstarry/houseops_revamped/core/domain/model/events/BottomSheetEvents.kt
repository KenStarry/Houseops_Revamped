package com.kenstarry.houseops_revamped.core.domain.model.events

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import kotlinx.coroutines.CoroutineScope

sealed class BottomSheetEvents<out T> {

    data class OpenBottomSheet<out T> @OptIn(ExperimentalMaterialApi::class) constructor(
        val state: ModalBottomSheetState,
        val scope: CoroutineScope,
        val bottomSheetType: String,
        val bottomSheetData: T?
    ) : BottomSheetEvents<T>()

    data class CloseBottomSheet @OptIn(ExperimentalMaterialApi::class) constructor(
        val state: ModalBottomSheetState,
        val scope: CoroutineScope
    ) : BottomSheetEvents<Nothing>()

    data class UpdateBottomSheetData<out T>(
        val data: T?
    ) : BottomSheetEvents<T>()
}

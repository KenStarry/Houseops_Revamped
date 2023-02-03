package com.example.houseops_revamped.core.presentation.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet(
    sheetBackground: Color,
    sheetContent: @Composable (state: ModalBottomSheetState, scope: CoroutineScope) -> Unit,
    sheetScope: @Composable (state: ModalBottomSheetState, scope: CoroutineScope) -> Unit,
    closeBottomSheet: (state: ModalBottomSheetState, scope: CoroutineScope) -> Unit
) {


    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val scope = rememberCoroutineScope()

    //  modal bottomsheet layout
    ModalBottomSheetLayout(
        sheetContent = { sheetContent(modalBottomSheetState, scope) },
        sheetState = modalBottomSheetState,
        sheetElevation = 4.dp,
        sheetShape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp
        ),
        sheetBackgroundColor = sheetBackground
    ) {
        sheetScope(modalBottomSheetState, scope)
    }

    BackHandler(
        enabled = modalBottomSheetState.isVisible
    ) {
        closeBottomSheet(modalBottomSheetState, scope)
    }
}




































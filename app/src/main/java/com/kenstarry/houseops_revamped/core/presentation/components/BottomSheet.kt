package com.kenstarry.houseops_revamped.core.presentation.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Minimize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        sheetContent = {

            //  Bottom sheet Icon
            androidx.compose.material3.Icon(
                imageVector = Icons.Outlined.Minimize,
                contentDescription = "Dash icon",
                tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.5f),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            sheetContent(modalBottomSheetState, scope)
        },
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




































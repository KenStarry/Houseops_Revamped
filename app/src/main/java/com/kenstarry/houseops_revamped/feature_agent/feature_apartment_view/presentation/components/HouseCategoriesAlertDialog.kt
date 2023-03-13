package com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.House
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection
import com.kenstarry.houseops_revamped.core.presentation.components.CustomAlertDialog
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.utils.AgentApartmentConstants

@Composable
fun HouseCategoriesAlertDialog(
    primaryColor: Color,
    tertiaryColor: Color,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {

    val listState = rememberLazyListState()
    var selectedCategory by remember {
        mutableStateOf(AgentApartmentConstants.houseCategories[0])
    }

    CustomAlertDialog(
        icon = Icons.Outlined.House,
        primaryColor = primaryColor,
        tertiaryColor = tertiaryColor,
        title = "Choose House Category",
        content = {
            LazyColumn(
                content = {
                    items(AgentApartmentConstants.houseCategories) { category ->
                        CategoriesAlertDialogItem(
                            houseCategory = category,
                            primaryColor = primaryColor,
                            tertiaryColor = tertiaryColor,
                            isSelected = category == selectedCategory,
                            onRadioButtonClicked = {
                                selectedCategory = category
                            }
                        )
                    }
                },
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
        },
        onConfirm = onConfirm,
        onDismiss = onDismiss
    )
}
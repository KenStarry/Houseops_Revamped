package com.example.houseops_revamped.feature_settings.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.houseops_revamped.feature_settings.domain.model.SettingsEvents
import com.example.houseops_revamped.feature_settings.presentation.viewmodel.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsAppBar(
    settingsViewModel: SettingsViewModel,
    onBackPressed: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) {

    LargeTopAppBar(
        title = {
            Text(
                text = "Settings",
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.ExtraBold
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBackPressed() }) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Back arrow"
                )
            }
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
            navigationIconContentColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
        ),
        actions = {
            IconButton(onClick = {
                settingsViewModel.onEvent(
                    SettingsEvents.ToggleDropdownMenu(
                        !settingsViewModel.isDropdownExpanded.value
                    )
                )
            }) {
                Icon(
                    imageVector = Icons.Outlined.MoreVert,
                    contentDescription = "More Icon",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )
            }

            DropdownMenu(
                expanded = settingsViewModel.isDropdownExpanded.value,
                onDismissRequest = {
                    settingsViewModel.onEvent(SettingsEvents.ToggleDropdownMenu(false))
                },
                modifier = Modifier
                    .wrapContentSize()
                    .background(MaterialTheme.colorScheme.onSecondary)
            ) {

                DropdownMenuItem(
                    text = {
                        Text(
                            text = "Expand All Items",
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                        )
                    },
                    onClick = {
                        settingsViewModel.onEvent(SettingsEvents.ToggleDropdownMenu(false))
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.ExpandMore,
                            contentDescription = "expand more"
                        )
                    }
                )

            }
        },
        scrollBehavior = scrollBehavior
    )
}

















package com.example.houseops_revamped.feature_agent.feature_home.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AgentHomeFab(
    modifier: Modifier = Modifier,
    primaryColor: Color,
    onClick: () -> Unit
) {

    FloatingActionButton(
        onClick = onClick,
        containerColor = primaryColor,
        contentColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f),
        modifier = modifier
    ) {

        Icon(
            imageVector = Icons.Outlined.Add,
            contentDescription = "Add icon"
        )
    }
}
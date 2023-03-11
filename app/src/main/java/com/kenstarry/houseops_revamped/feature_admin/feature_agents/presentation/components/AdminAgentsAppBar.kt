package com.kenstarry.houseops_revamped.feature_admin.feature_agents.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowRight
import androidx.compose.material.icons.outlined.Sort
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material.icons.sharp.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminAgentsAppBar(
    title: String,
    primaryColor: Color,
    tertiaryColor: Color,
    onBackPressed: () -> Unit,
    onSort: () -> Unit
) {
    LargeTopAppBar(
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = title,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
                    modifier = Modifier
                        .weight(5f)
                )

                //  sort button
                Row(
                    modifier = Modifier
                        .weight(2f),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Sort",
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        fontWeight = FontWeight.Bold
                    )

                    Icon(
                        imageVector = Icons.Outlined.ArrowRight,
                        contentDescription = "Arrow icon"
                    )

                    IconButton(
                        onClick = onSort,
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = tertiaryColor,
                            contentColor = primaryColor
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Sort,
                            contentDescription = "view icon"
                        )
                    }

                }
            }
        },
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.Sharp.ArrowBack,
                    contentDescription = "Back Arrow"
                )
            }
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        ),
        actions = {
            IconButton(onClick = {  }) {
                Icon(
                    imageVector = Icons.Sharp.MoreVert,
                    contentDescription = "More icon"
                )
            }
        }
    )
}
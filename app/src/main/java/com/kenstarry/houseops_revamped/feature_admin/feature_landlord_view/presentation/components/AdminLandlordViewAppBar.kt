package com.kenstarry.houseops_revamped.feature_admin.feature_landlord_view.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material.icons.sharp.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.HomePillBtns

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminLandlordViewAppBar(
    title: String,
    email: String,
    primaryColor: Color,
    tertiaryColor: Color,
    onBackPressed: () -> Unit
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
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )

                HomePillBtns(
                    icon = Icons.Outlined.AlternateEmail,
                    title = email,
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor,
                    onClick = {}
                )
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
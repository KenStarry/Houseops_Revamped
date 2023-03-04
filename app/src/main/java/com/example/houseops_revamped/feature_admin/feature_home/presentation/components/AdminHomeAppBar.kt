package com.example.houseops_revamped.feature_admin.feature_home.presentation.components

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowRight
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.VerifiedUser
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.houseops_revamped.core.presentation.components.CoilImage
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.HomePillBtns

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminHomeAppBar(
    context: Context,
    userName: String
) {

    LargeTopAppBar(
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Admin",
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.ExtraBold
                )

                Icon(
                    imageVector = Icons.Outlined.ArrowRight,
                    contentDescription = "Right arrow"
                )

                HomePillBtns(
                    icon = Icons.Outlined.VerifiedUser,
                    title = userName,
                    primaryColor = MaterialTheme.colorScheme.primary,
                    tertiaryColor = MaterialTheme.colorScheme.tertiary
                ) {

                    //  go to settings screen

                }

            }
        },
        actions = {

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Outlined.MoreVert,
                    contentDescription = "More Icon"
                )
            }
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
        )
    )
}
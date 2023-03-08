package com.example.houseops_revamped.feature_agent.feature_home.presentation.components

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.houseops_revamped.R
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.core.presentation.components.CoilImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentHomeTopBar(
    context: Context,
    userDetails: UsersCollection?
) {

    TopAppBar(
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${userDetails?.userName}",
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.Bold
                )
            }
        },

        navigationIcon = {
            CoilImage(
                context = context,
                imageUri = userDetails?.userImageUri?.toUri(),
                placeholder = R.drawable.profile,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
            )
        },

        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Outlined.MoreVert,
                    contentDescription = "More Icon"
                )
            }
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor =  MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
        ),

        windowInsets = WindowInsets(8.dp)
    )
}


























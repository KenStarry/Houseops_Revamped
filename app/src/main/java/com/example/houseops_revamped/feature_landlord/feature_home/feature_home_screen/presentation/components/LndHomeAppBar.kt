package com.example.houseops_revamped.feature_landlord.feature_home.feature_home_screen.presentation.components

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.R
import com.example.houseops_revamped.core.presentation.components.CoilImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LndHomeAppBar(
    context: Context,
    imageUri: Uri?
) {

    TopAppBar(

        title = {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (imageUri != null) {
                    CoilImage(
                        context = context,
                        imageUri = imageUri,
                        placeholder = R.drawable.houseops_light_final,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(45.dp)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile image",
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(45.dp)
                    )
                }

                Text(
                    text = "Home",
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    fontWeight = FontWeight.ExtraBold
                )
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
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
            titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
            actionIconContentColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
        ),
        windowInsets = TopAppBarDefaults.windowInsets
    )
}
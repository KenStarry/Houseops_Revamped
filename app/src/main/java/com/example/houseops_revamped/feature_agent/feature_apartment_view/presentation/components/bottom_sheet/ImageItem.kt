package com.example.houseops_revamped.feature_agent.feature_apartment_view.presentation.components.bottom_sheet

import android.net.Uri
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DeleteForever
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.core.presentation.components.CoilImage
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.HomePillBtns

@Composable
fun ImageItem(
    imageUri: Uri,
    primaryColor: Color,
    tertiaryColor: Color,
    onDelete: () -> Unit
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .width(150.dp)
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {

        CoilImage(
            context = context,
            imageUri = imageUri,
            placeholder = com.example.houseops_revamped.R.drawable.houseops_dark_final,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .size(150.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {

            //  delete button
            HomePillBtns(
                icon = Icons.Outlined.DeleteForever,
                title = "Delete",
                primaryColor = primaryColor,
                tertiaryColor = tertiaryColor,
                onClick = onDelete
            )
        }
    }

}
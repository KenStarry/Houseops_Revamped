package com.example.houseops_revamped.feature_categories.presentation.components.content_caretaker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.houseops_revamped.core.domain.model.Caretaker
import com.example.houseops_revamped.core.presentation.components.CoilImage
import com.example.houseops_revamped.feature_home.home_screen.presentation.components.HomePillBtns

@Composable
fun CaretakerBottomSheet(
    caretaker: Caretaker?
) {

    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {

            CoilImage(
                context = context,
                imageUri = caretaker?.caretakerImage?.toUri(),
                placeholder = com.example.houseops_revamped.R.drawable.houseops_dark_final,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(70.dp)
            )
        }

        //  content
        Column(
            modifier = Modifier
                .weight(2f)
                .background(MaterialTheme.colorScheme.onPrimary),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = caretaker?.caretakerName ?: "Anonymous",
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

            HomePillBtns(
                icon = Icons.Outlined.Apartment,
                title = caretaker?.caretakerApartment ?: "none",
                onClick = {}
            )

        }

    }
}




















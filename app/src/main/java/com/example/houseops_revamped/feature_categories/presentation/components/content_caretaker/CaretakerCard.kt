package com.example.houseops_revamped.feature_categories.presentation.components.content_caretaker

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.houseops_revamped.R
import com.example.houseops_revamped.core.domain.model.Caretaker
import com.example.houseops_revamped.core.presentation.components.CoilImage

@Composable
fun CaretakerCard(
    modifier: Modifier = Modifier,
    context: Context,
    caretaker: Caretaker
) {

    Card(
        modifier = modifier,
        shape = CardDefaults.shape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            CoilImage(
                context = context,
                imageUri = caretaker.caretakerImage?.toUri(),
                placeholder = R.drawable.houseops_dark_final,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(60.dp)
            )

            Text(
                text = caretaker.caretakerName ?: "",
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
            )
        }

    }

}


























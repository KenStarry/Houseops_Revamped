package com.kenstarry.houseops_revamped.core.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kenstarry.houseops_revamped.R

@Composable
fun ComingSoon(
    modifier: Modifier = Modifier,
    title: String
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = title,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
        )

        Lottie(
            rawFile = R.raw.rocket,
            isPlaying = true,
            iterations = Int.MAX_VALUE,
            modifier = Modifier
                .fillMaxSize(0.6f)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Coming Soon...",
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
        )

    }
}
package com.kenstarry.houseops_revamped.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kenstarry.houseops_revamped.core.presentation.components.Lottie

@Composable
fun ErrorLottie(
    lottieImage: Int,
    title: String?,
    message: String
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        //  error message
        title?.let {
            Text(
                text = it,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        Lottie(
            rawFile = lottieImage,
            isPlaying = true,
            iterations = Int.MAX_VALUE,
            modifier = Modifier
                .fillMaxSize(0.6f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        //  error message
        Text(
            text = message,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
        )

    }

}
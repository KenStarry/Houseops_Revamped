package com.kenstarry.houseops_revamped.feature_tenant.feature_categories.presentation.components.content_agent

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection
import com.kenstarry.houseops_revamped.core.presentation.components.CoilImage
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.HomePillBtns

@Composable
fun AgentCard(
    modifier: Modifier = Modifier,
    context: Context,
    agent: UsersCollection,
    primaryColor: Color,
    tertiaryColor: Color
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

            Spacer(modifier = Modifier.height(16.dp))

            CoilImage(
                context = context,
                imageUri = agent.userImageUri?.toUri(),
                placeholder = com.kenstarry.houseops_revamped.R.drawable.houseops_dark_final,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(60.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = agent.userName ?: "",
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.height(8.dp))

            HomePillBtns(
                icon = null,
                title = agent.userType,
                onClick = {},
                paddingVertical = 8.dp,
                paddingHorizontal = 16.dp,
                primaryColor = primaryColor,
                tertiaryColor = tertiaryColor
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

    }

}


























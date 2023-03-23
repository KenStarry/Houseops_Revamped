package com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.model.ActionCard

@Composable
fun AgentActionCardItem(
    actionCard: ActionCard,
    primaryColor: Color,
    tertiaryColor: Color
) {

    Card(
        modifier = Modifier
            .wrapContentSize(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {

        Column(
            modifier = Modifier
                .size(80.dp)
                .clickable { }
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            //  icon
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(35.dp)
                    .background(tertiaryColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = actionCard.cardIcon,
                    contentDescription = "Card icon",
                    tint = primaryColor
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            //  title
            Text(
                text = actionCard.cardTitle,
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f)
            )


        }

    }

}



























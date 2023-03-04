package com.example.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HomePillBtns(
    icon: ImageVector?,
    title: String,
    primaryColor: Color,
    tertiaryColor: Color,
    paddingHorizontal: Dp = 16.dp,
    paddingVertical: Dp = 8.dp,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .wrapContentSize(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = tertiaryColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {

        Row(
            modifier = Modifier
                .wrapContentSize()
                .background(MaterialTheme.colorScheme.tertiary)
                .clickable { onClick() }
                .padding(
                    horizontal = paddingHorizontal,
                    vertical = paddingVertical
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            icon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = "pill icon",
                    tint = primaryColor
                )

                Spacer(modifier = Modifier.width(8.dp))
            }

            Text(
                text = title,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                overflow = TextOverflow.Ellipsis
            )

        }

    }


}
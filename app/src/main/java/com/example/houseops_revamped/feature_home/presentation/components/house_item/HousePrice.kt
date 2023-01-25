package com.example.houseops_revamped.feature_home.presentation.components.house_item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun HousePrice(
    modifier: Modifier = Modifier
) {

    //  price and like icon
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = buildAnnotatedString {
                append("Ksh. ")
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = MaterialTheme.typography.titleSmall.fontSize
                    )
                ) { append("15,000") }
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary
                    )
                ) { append("/mo") }
            },
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )

        //  Thumbs up icon
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .wrapContentSize()
                .background(MaterialTheme.colorScheme.tertiary)
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Icon(
                imageVector = Icons.Outlined.ThumbUp,
                contentDescription = "Like Icon",
                tint = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "0",
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

        }

    }
}




















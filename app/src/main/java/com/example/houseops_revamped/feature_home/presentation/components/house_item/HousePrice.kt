package com.example.houseops_revamped.feature_home.presentation.components.house_item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmark
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.core.utils.Constants

@Composable
fun HousePrice(
    modifier: Modifier = Modifier,
    price: String,
    priceCategory: String
) {

    val category = when (priceCategory) {
        Constants.priceCategories[0] -> {
            "mo"
        }
        Constants.priceCategories[1] -> {
            "yr"
        }
        Constants.priceCategories[2] -> {
            "qrtr"
        }
        Constants.priceCategories[3] -> {
            "halfYr"
        }
        else -> {
            ""
        }
    }

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
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    )
                ) { append(price) }
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary
                    )
                ) { append("/$category") }
            },
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .weight(2f)
        )

        Spacer(modifier = Modifier.width(8.dp))

        //  Thumbs up icon
        ThumbsUp(
            modifier = Modifier
                .weight(1f)
        )


    }
}




















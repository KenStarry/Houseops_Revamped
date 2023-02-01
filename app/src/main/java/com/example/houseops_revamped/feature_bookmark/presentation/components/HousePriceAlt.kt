package com.example.houseops_revamped.feature_bookmark.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.core.utils.Constants
import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel
import com.example.houseops_revamped.feature_home.home_screen.presentation.components.house_item.BookmarkIcon

@Composable
fun HousePriceAlt(
    modifier: Modifier = Modifier,
    houseModel: HouseModel,
    user: UsersCollection?
) {

    val category = when (houseModel.housePriceCategory) {
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
                ) { append(houseModel.housePrice) }
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

        BookmarkIcon(
            house = houseModel,
            user = user
        )


    }
}




















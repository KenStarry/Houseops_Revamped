package com.example.houseops_revamped.feature_home.house_view_screen.presentation.components.view_pager

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.feature_home.home_screen.presentation.components.house_item.BookmarkIcon

@Composable
fun BackAppBar(
    price: String
) {

    //  navigation
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        //  back button
        Card(
            modifier = Modifier
                .size(45.dp)
                .clickable {  },
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onSecondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            )
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Back press",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )
            }

        }

        //  price
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .wrapContentSize()
                .background(MaterialTheme.colorScheme.onSecondary)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "Ksh. $price",
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
            )

        }

        //  bookmark
        Card(
            modifier = Modifier
                .size(45.dp)
                .clickable {  },
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onSecondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            )
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.MoreVert,
                    contentDescription = "More Icon",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )
            }

        }

    }
}









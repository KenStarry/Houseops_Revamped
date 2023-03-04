package com.example.houseops_revamped.feature_landlord.feature_home.feature_home_screen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.core.domain.model.Apartment
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_home_screen.presentation.components.ApartmentItem

@Composable
fun AlphabetItem(
    alphabet: String,
    apartmentList: List<Apartment>,
    primaryColor: Color,
    tertiaryColor: Color
) {

    val listState = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        //  title
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .size(50.dp)
                .background(tertiaryColor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = alphabet,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
            )
        }

        LazyColumn(
            content = {
                items(
                    items = apartmentList
                ) {

                    ApartmentItem(
                        apartment = it,
                        primaryColor = primaryColor,
                        tertiaryColor = tertiaryColor,
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .fillMaxWidth()
                            .height(100.dp)
                            .background(MaterialTheme.colorScheme.onSecondary)
                            .padding(16.dp)
                    )

                }
            },
            state = listState,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height((100.dp + 32.dp) * apartmentList.size)
        )

    }

}
package com.example.houseops_revamped.core.presentation.components

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel

@Composable
fun HouseItemAlt(
    house: HouseModel,
    context: Context,
    user: UsersCollection?
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            //  image
            Box(
                modifier = Modifier
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {

                CoilImage(
                    context = context,
                    imageUri = house.houseImageUris[0].toUri(),
                    placeholder = com.example.houseops_revamped.R.drawable.houseops_dark_final,
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .size(70.dp)
                )
            }

            Column(
                modifier = Modifier
                    .weight(2f)
            ) {

            }

        }

    }

}


































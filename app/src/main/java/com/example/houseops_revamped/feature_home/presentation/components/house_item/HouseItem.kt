package com.example.houseops_revamped.feature_home.presentation.components.house_item

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.houseops_revamped.R
import com.example.houseops_revamped.core.presentation.components.CoilImage
import com.example.houseops_revamped.feature_home.domain.model.HouseModel
import kotlin.random.Random

@Composable
fun HouseItem(
    modifier: Modifier = Modifier,
    context: Context,
    house: HouseModel
) {

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .size(
                width = 270.dp,
                height = 300.dp
            )
            .background(MaterialTheme.colorScheme.onSecondary)
            .padding(16.dp)
    ) {

        //  Image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
        ) {

            if (house.houseImageUris.isNotEmpty()) {
                CoilImage(
                    context = context,
                    imageUri = house.houseImageUris[Random.nextInt(
                        0,
                        house.houseImageUris.size
                    )].toUri(),
                    placeholder = R.drawable.houseops_dark_final,
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .fillMaxSize()
                )
            }

        }

        //  content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {

        }


    }


}


@Preview
@Composable
fun HouseItemPrev() {
    HouseItem(
        house = HouseModel(),
        context = LocalContext.current
    )
}
package com.example.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.house_item

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.houseops_revamped.R
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.core.presentation.components.CoilImage
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.domain.model.HouseModel

@Composable
fun HouseItem(
    modifier: Modifier = Modifier,
    context: Context,
    house: HouseModel,
    user: UsersCollection?,
    snackbarHostState: SnackbarHostState?,
    primaryColor: Color,
    tertiaryColor: Color
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        //  Image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            contentAlignment = Alignment.Center
        ) {

            if (house.houseImageUris.isNotEmpty()) {
                CoilImage(
                    context = context,
                    imageUri = house.houseImageUris[0].toUri(),
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
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {

                //  house category
                Text(
                    text = house.houseCategory,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                //  house location
                HouseLocation()

            }

            //  house price
            HousePrice(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                houseModel = house,
                user = user,
                snackbarHostState = snackbarHostState,
                primaryColor = primaryColor,
                tertiaryColor = tertiaryColor
            )
        }


    }


}
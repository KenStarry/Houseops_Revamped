package com.kenstarry.houseops_revamped.core.presentation.components

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BrokenImage
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.kenstarry.houseops_revamped.R
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection
import com.kenstarry.houseops_revamped.core.domain.model.HouseModel
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.house_item.HouseLocation
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.house_item.HousePrice

@Composable
fun HouseItemAlt(
    modifier: Modifier = Modifier,
    context: Context,
    house: HouseModel,
    location: String,
    user: UsersCollection?,
    snackbarHostState: SnackbarHostState?,
    primaryColor: Color,
    tertiaryColor: Color
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        //  Image
        Box(
            modifier = Modifier
                .weight(1.5f)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {

            if (house.houseImageUris.isNotEmpty()) {
                CoilImage(
                    context = context,
                    imageUri = house.houseImageUris[0].uri.toUri(),
                    placeholder = R.drawable.houseops_dark_final,
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .fillMaxSize()
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.BrokenImage,
                        contentDescription = "Broken image",
                        tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.5f),
                        modifier = Modifier
                            .fillMaxSize(0.8f)
                    )
                }
            }
        }

        //  content
        Column(
            modifier = Modifier
                .weight(2f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Card(
                    modifier = Modifier
                        .wrapContentSize(),
                    colors = CardDefaults.cardColors(
                        containerColor = tertiaryColor,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = house.houseApartmentName,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .padding(8.dp)
                    )
                }
            }

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
                HouseLocation(
                    location = location
                )

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
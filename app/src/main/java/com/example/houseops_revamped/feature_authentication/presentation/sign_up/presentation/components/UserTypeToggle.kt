package com.example.houseops_revamped.feature_authentication.presentation.sign_up.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.feature_authentication.presentation.model.UserType

@Composable
fun UserTypeToggle(
    userTypes: List<UserType>
) {

    val listState = rememberLazyListState()

    LazyRow(
        content = {

            itemsIndexed(userTypes) { index, user ->

                Row(
                    modifier = Modifier
                        .wrapContentHeight()
                        .width(100.dp)
                        .background(MaterialTheme.colorScheme.onSecondary)
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {

                    Icon(
                        imageVector = user.icon,
                        contentDescription = "User type icon",
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(text = user.userTitle)

                }
            }
        },
        state = listState,
        modifier = Modifier
            .clip(RoundedCornerShape(24.dp))
            .fillMaxWidth(0.6f)
            .height(72.dp),
        contentPadding = PaddingValues(16.dp)
    )

}
package com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.presentation.components.house_view_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.HomePillBtns

@Composable
fun DetailsVacant(
    vacantHouses: Int,
    primaryColor: Color,
    tertiaryColor: Color
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        HomePillBtns(
            icon = Icons.Outlined.Timer,
            title = "$vacantHouses vacants remaining",
            primaryColor = primaryColor,
            tertiaryColor = tertiaryColor,
            containerColor = if (vacantHouses in 0..4)
                MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.1f)
            else
                tertiaryColor,
            onClick = {}
        )

    }
}

















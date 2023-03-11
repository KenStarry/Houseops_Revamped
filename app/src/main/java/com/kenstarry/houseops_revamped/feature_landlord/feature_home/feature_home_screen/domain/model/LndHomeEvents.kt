package com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_home_screen.domain.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.kenstarry.houseops_revamped.core.domain.model.Response

sealed class LndHomeEvents {

    data class GetLandlordDetails(
        val email: String
    ) : LndHomeEvents()

    data class GetLandlordApartments(
        val email: String,
        val response: (response: Response<*>) -> Unit
    ) : LndHomeEvents()

    data class FilterGreetingsText(
        val currentHour: Int,
        val greetings: (
            greetingText: String,
            greetingIcon: ImageVector
        ) -> Unit,
    ) : LndHomeEvents()
}

package com.kenstarry.houseops_revamped.feature_admin.feature_landlord_view.domain.model

import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection

sealed class AdminLandlordViewEvents {

    data class GetApartments(
        val landlordEmail: String,
        val response: (response: Response<*>) -> Unit
    ) : AdminLandlordViewEvents()

    data class ToggleAgentSelected(
        val selectedAgent: UsersCollection
    ) : AdminLandlordViewEvents()

    data class ToggleAlertDialog(
        val isVisible: Boolean,
        val dialogType: String
    ) : AdminLandlordViewEvents()
}

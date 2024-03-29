package com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_add_apartment.domain.model

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import com.kenstarry.houseops_revamped.core.domain.model.Apartment
import com.kenstarry.houseops_revamped.core.domain.model.ApartmentFeature
import com.kenstarry.houseops_revamped.core.domain.model.Response
import kotlinx.coroutines.CoroutineScope

sealed class LndApartmentEvents {

    data class AddApartment(
        val apartment: Apartment,
        val response: (response: Response<*>) -> Unit
    ) : LndApartmentEvents()

    data class SearchPlaces(
        val query: String
    ) : LndApartmentEvents()

    //  add feature to viewmodel
    data class AddFeature(
        val apartmentFeature: ApartmentFeature
    ) : LndApartmentEvents()

    //  open bottom sheet
    data class OpenBottomSheet @OptIn(ExperimentalMaterialApi::class) constructor
        (
        val state: ModalBottomSheetState,
        val scope: CoroutineScope,
        val bottomSheetType: String
    ) : LndApartmentEvents()

    //  close bottom sheet
    data class CloseBottomSheet @OptIn(ExperimentalMaterialApi::class) constructor
        (val state: ModalBottomSheetState, val scope: CoroutineScope) : LndApartmentEvents()
}

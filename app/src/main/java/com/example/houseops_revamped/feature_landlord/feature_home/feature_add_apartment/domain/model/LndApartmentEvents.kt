package com.example.houseops_revamped.feature_landlord.feature_home.feature_add_apartment.domain.model

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.feature_landlord.core.model.Apartment
import com.example.houseopscaretakers.feature_landlord.core.model.ApartmentFeature
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

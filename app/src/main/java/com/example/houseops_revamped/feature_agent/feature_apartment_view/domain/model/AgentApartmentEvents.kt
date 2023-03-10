package com.example.houseops_revamped.feature_agent.feature_apartment_view.domain.model

import android.net.Uri
import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.domain.model.HouseModel

sealed class AgentApartmentEvents {

    data class AddHouse(
        val apartmentName: String,
        val houseModel: HouseModel,
        val onResponse: (response: Response<*>) -> Unit
    ) : AgentApartmentEvents()

    data class GetApartmentHouses(
        val apartmentName: String,
        val onResponse: (response: Response<*>) -> Unit
    ) : AgentApartmentEvents()

    //  pick images from gallery
    data class AddGalleryImages(val uris: List<Uri>) : AgentApartmentEvents()

    //  update selected images list
    data class UpdateGalleryImages(val uris: List<Uri>) : AgentApartmentEvents()

    //  delete image from list
    data class DeleteImageFromList(val index: Int) : AgentApartmentEvents()
}

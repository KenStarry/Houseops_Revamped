package com.kenstarry.houseops_revamped.core.domain.model.events

import android.content.Context
import android.net.Uri
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.net.PlacesClient
import com.kenstarry.houseops_revamped.core.domain.model.*
import com.kenstarry.houseops_revamped.core.presentation.model.AccentColor
import com.kenstarry.houseops_revamped.core.presentation.model.LatLngModel
import com.kenstarry.houseops_revamped.core.presentation.model.OptionsToggleModel

sealed class CoreEvents {

    data class GetCurrentLocation(
        val interval: Long,
        val onResponse: (response: Response<*>) -> Unit
    ) : CoreEvents()

    data class GetPlaceCoordinates(
        val place: PlacesAPIResult,
        val placesClient: PlacesClient,
        val response: (response: Response<*>) -> Unit
    ) : CoreEvents()

    data class GetLocationAddressName(
        val latLngModel: LatLngModel,
        val address: (locationAddress: LocationAddresses) -> Unit
    ) : CoreEvents()

    data class SendVerificationEmail(
        val response: (response: Response<*>) -> Unit
    ) : CoreEvents()

    data class LogoutUser(
        val response: (response: Response<*>) -> Unit
    ) : CoreEvents()

    data class GetApartments(
        val response: (response: Response<*>) -> Unit
    ) : CoreEvents()

    data class GetApartmentDetails(
        val apartmentName: String,
        val apartmentDetails: (apartment: Apartment) -> Unit,
        val response: (response: Response<*>) -> Unit
    ) : CoreEvents()

    data class GetAllAgents(
        val response: (response: Response<*>) -> Unit
    ) : CoreEvents()

    data class UpdateFirestoreField(
        val collectionName: String,
        val documentName: String,
        val subCollectionName: String?,
        val subCollectionDocument: String?,
        val fieldName: String,
        val fieldValue: Any,
        val onResponse: (response: Response<*>) -> Unit
    ) : CoreEvents()

    data class UpdateArrayField(
        val collectionName: String,
        val documentName: String,
        val fieldName: String,
        val fieldValue: String,
        val isAddItem: Boolean
    ) : CoreEvents()

    data class UploadImagesToStorage(
        val imageUriList: List<ImageModel?>,
        val context: Context,
        val storageRef: String,
        val collectionName: String,
        val documentName: String,
        val subCollectionName: String?,
        val subCollectionDocument: String?,
        val fieldToUpdate: String,
        val onResponse: (response: Response<*>) -> Unit
    ) : CoreEvents()

    data class UploadSingleImageToStorage(
        val uri: ImageModel?,
        val context: Context,
        val storageRef: String,
        val collectionName: String,
        val documentName: String,
        val subCollectionName: String?,
        val subCollectionDocument: String?,
        val fieldToUpdate: String,
        val onResponse: (response: Response<*>) -> Unit
    ) : CoreEvents()

    data class DeleteDocument(
        val collectionName: String,
        val documentName: String,
        val subCollectionName: String?,
        val subCollectionDocument: String?,
        val onResponse: (response: Response<*>) -> Unit
    ) : CoreEvents()

    data class ChangeAccent(val accentColor: AccentColor) : CoreEvents()

    data class ToggleLoadingCircles(val isLoading: Boolean) : CoreEvents()

    data class ToggleOptions(val option: OptionsToggleModel) : CoreEvents()

    data class ToggleAlertDialog(
        val dialogType: String,
        val isDialogVisible: Boolean
    ) : CoreEvents()

    data class DatastoreSaveUserType(
        val userType: String
    ) : CoreEvents()
}

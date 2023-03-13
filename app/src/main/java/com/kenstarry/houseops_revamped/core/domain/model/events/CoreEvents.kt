package com.kenstarry.houseops_revamped.core.domain.model.events

import android.content.Context
import android.net.Uri
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.core.presentation.model.AccentColor
import com.kenstarry.houseops_revamped.core.presentation.model.OptionsToggleModel
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.domain.model.AgentApartmentEvents

sealed class CoreEvents {

    data class SendVerificationEmail(
        val response: (response: Response<*>) -> Unit
    ) : CoreEvents()

    data class LogoutUser(
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

    data class UploadImageToStorage(
        val imageUriList: List<Uri?>,
        val context: Context,
        val storageRef: String,
        val collectionName: String,
        val documentName: String,
        val subCollectionName: String?,
        val subCollectionDocument: String?,
        val fieldToUpdate: String,
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
package com.example.houseops_revamped.core.domain.model.events

import com.example.houseops_revamped.core.presentation.model.AccentColor

sealed class CoreEvents {

    data class UpdateFirestoreField(
        val collectionName: String,
        val documentName: String,
        val subCollectionName: String,
        val subCollectionDocument: String,
        val fieldName: String,
        val fieldValue: String
    ) : CoreEvents()

    data class UpdateArrayField(
        val collectionName: String,
        val documentName: String,
        val fieldName: String,
        val fieldValue: String,
        val isAddItem: Boolean
    ) : CoreEvents()

    data class ChangeAccent(val accentColor: AccentColor) : CoreEvents()

    data class ToggleLoadingCircles(val isLoading: Boolean) : CoreEvents()

    data class DatastoreSaveUserType(
        val userType: String
    ) : CoreEvents()
}

package com.example.houseops_revamped.core.domain.model

sealed class CoreEvents {

    data class UpdateFirestoreField(
        val collectionName: String,
        val documentName: String,
        val subCollectionName: String,
        val subCollectionDocument: String,
        val fieldName: String,
        val fieldValue: String
    ) : CoreEvents()

    data class UpdateFirestoreArrayField(
        val collectionName: String,
        val documentName: String,
        val subCollectionName: String,
        val subCollectionDocument: String,
        val fieldName: String,
        val fieldValue: String,
        val isAddItem: Boolean
    ) : CoreEvents()
}

package com.example.houseops_revamped.core.domain.use_cases

import com.example.houseops_revamped.core.domain.repository.CoreRepository

class UpdateArrayField(
    private val repository: CoreRepository
) {

    suspend operator fun invoke(
        collectionName: String,
        documentName: String,
        subCollectionName: String,
        subCollectionDocument: String,
        fieldName: String,
        fieldValue: String,
        addItem: Boolean
    ) = repository.updateFirestoreArrayField(
        collectionName,
        documentName,
        subCollectionName,
        subCollectionDocument,
        fieldName,
        fieldValue,
        addItem
    )
}
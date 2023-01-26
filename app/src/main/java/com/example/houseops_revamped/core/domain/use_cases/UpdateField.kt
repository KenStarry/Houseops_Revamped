package com.example.houseops_revamped.core.domain.use_cases

import com.example.houseops_revamped.core.domain.repository.CoreRepository

class UpdateField(
    private val repository: CoreRepository
) {

    suspend operator fun invoke(
        collectionName: String,
        documentName: String,
        subCollectionName: String,
        subCollectionDocument: String,
        fieldName: String,
        fieldValue: String
    ) = repository.updateFirestoreField(
        collectionName,
        documentName,
        subCollectionName,
        subCollectionDocument,
        fieldName,
        fieldValue
    )
}
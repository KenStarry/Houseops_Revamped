package com.kenstarry.houseops_revamped.core.domain.use_cases

import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.core.domain.repository.CoreRepository

class UpdateField(
    private val repository: CoreRepository
) {

    suspend operator fun invoke(
        collectionName: String,
        documentName: String,
        subCollectionName: String?,
        subCollectionDocument: String?,
        fieldName: String,
        fieldValue: Any,
        onResponse: (response: Response<*>) -> Unit
    ) = repository.updateFirestoreField(
        collectionName,
        documentName,
        subCollectionName,
        subCollectionDocument,
        fieldName,
        fieldValue,
        onResponse = onResponse
    )
}
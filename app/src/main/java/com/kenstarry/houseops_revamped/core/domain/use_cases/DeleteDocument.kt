package com.kenstarry.houseops_revamped.core.domain.use_cases

import com.kenstarry.houseops_revamped.core.domain.model.HouseModel
import com.kenstarry.houseops_revamped.core.domain.model.ImageModel
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.core.domain.repository.CoreRepository

class DeleteDocument(
    private val repo: CoreRepository
) {
    suspend operator fun invoke(
        house: HouseModel,
        extension: String,
        imageRefs: List<ImageModel>?,
        collectionName: String,
        documentName: String,
        subCollectionName: String?,
        subCollectionDocument: String?,
        onResponse: (response: Response<*>) -> Unit
    ) = repo.deleteDocument(
        house,
        extension,
        imageRefs,
        collectionName,
        documentName,
        subCollectionName,
        subCollectionDocument,
        onResponse
    )
}
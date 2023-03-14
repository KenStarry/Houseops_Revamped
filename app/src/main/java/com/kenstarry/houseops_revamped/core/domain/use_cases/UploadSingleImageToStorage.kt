package com.kenstarry.houseops_revamped.core.domain.use_cases

import android.content.Context
import android.net.Uri
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.core.domain.repository.CoreRepository

class UploadSingleImageToStorage(
    private val repo: CoreRepository
) {
    suspend operator fun invoke(
        uri: Uri?,
        context: Context,
        storageRef: String,
        collectionName: String,
        documentName: String,
        subCollectionName: String?,
        subCollectionDocument: String?,
        fieldToUpdate: String,
        onResponse: (response: Response<*>) -> Unit
    ) = repo.uploadSingleImageToStorage(
        uri,
        context,
        storageRef,
        collectionName,
        documentName,
        subCollectionName,
        subCollectionDocument,
        fieldToUpdate,
        onResponse
    )
}
package com.kenstarry.houseops_revamped.core.presentation.components

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kenstarry.houseops_revamped.R

@Composable
fun CoilImage(
    modifier: Modifier = Modifier,
    context: Context,
    imageUri: Uri?,
    placeholder: Int
) {

    imageUri?.let {

        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(it)
                .crossfade(true)
                .placeholder(placeholder)
                .build(),
            contentDescription = "User image",
            contentScale = ContentScale.Crop,
            modifier = modifier
        )

//        if (it.toString().startsWith("content")) {
//
//            Image(
//                painter = painterResource(id = R.drawable.profile),
//                contentDescription = "profile picture",
//                modifier = modifier,
//                contentScale = ContentScale.Crop
//            )
//
//        } else {
//
//            AsyncImage(
//                model = ImageRequest.Builder(context)
//                    .data(it)
//                    .crossfade(true)
//                    .placeholder(placeholder)
//                    .build(),
//                contentDescription = "User image",
//                contentScale = ContentScale.Crop,
//                modifier = modifier
//            )
//        }
    }
}
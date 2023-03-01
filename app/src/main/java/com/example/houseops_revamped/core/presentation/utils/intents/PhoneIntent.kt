package com.example.houseops_revamped.core.presentation.utils.intents

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

fun phoneDialIntent(
    context: Context,
    phoneNumber: String
) {

    val parsePhone = Uri.parse("tel:$phoneNumber")
    val intent = Intent(Intent.ACTION_DIAL, parsePhone)

    //  open intent
    try {
        context.startActivity(intent)

    } catch (e: SecurityException) {
        Toast.makeText(context, "An error occurred", Toast.LENGTH_SHORT).show()
    }

}

fun phoneCallIntent(
    context: Context,
    phoneNumber: String
) {

    val parsePhone = Uri.parse("tel:$phoneNumber")
    val intent = Intent(Intent.ACTION_CALL)

    //  open intent
    try {
        intent.data = parsePhone
        context.startActivity(intent)

    } catch (e: SecurityException) {
        Toast.makeText(context, "Error: $e", Toast.LENGTH_SHORT).show()
    }

}
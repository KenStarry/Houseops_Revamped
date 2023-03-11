package com.kenstarry.houseops_revamped.feature_authentication.presentation.sign_up.presentation.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

@Composable
fun ColumnScope.TermsAndConditions(
    primaryColor: Color,
    tertiaryColor: Color
) {
    Text(
        modifier = Modifier
            .align(Alignment.Start),
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.9f)
                )
            ) {
                append(
                    "By signing up, you agree to our "
                )
            }

            withStyle(
                style = SpanStyle(
                    color = primaryColor
                )
            ) {
                append(
                    "Terms & Conditions "
                )
            }

            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.9f)
                )
            ) {
                append(
                    "and "
                )
            }
            withStyle(
                style = SpanStyle(
                    color = primaryColor
                )
            ) {
                append(
                    "Privacy Policy."
                )
            }
        },
        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
        fontWeight = MaterialTheme.typography.bodyMedium.fontWeight
    )
}
package com.example.houseops_revamped.custom_components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.sharp.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.houseops_revamped.R
import com.example.houseops_revamped.models.TopbarDropdown
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.ui.theme.PendingColor

//  custom form textfields
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.FormTextField(
    startIcon: ImageVector?,
    endIcon: ImageVector?,
    placeholder: String,
    imeAction: ImeAction,
    keyboardType: KeyboardType,
    onInputFieldChanged: (input: String) -> Unit
) {

    var apartmentTextFieldState by remember {
        mutableStateOf("")
    }

    TextField(
        value = apartmentTextFieldState,
        onValueChange = {
            apartmentTextFieldState = it
            onInputFieldChanged(apartmentTextFieldState)
        },
        leadingIcon = {
            if (startIcon != null) {
                Icon(
                    imageVector = startIcon,
                    contentDescription = "Leading Icon",
                    tint = Color.LightGray.copy(alpha = 0.9f)
                )
            }
        },
        trailingIcon = {
            if (endIcon != null) {
                Icon(
                    imageVector = endIcon,
                    contentDescription = "Trailing Icon",
                    tint = Color.LightGray.copy(alpha = 0.9f)
                )
            }
        },
        placeholder = {
            Text(
                text = placeholder,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
            )
        },
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            imeAction = imeAction,
            keyboardType = keyboardType
        ),
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.Start),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            cursorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = Color.LightGray.copy(alpha = 0.3f),
            focusedIndicatorColor = MaterialTheme.colorScheme.primary
        )
    )
}

//  dropdown menus
@Composable
fun CustomDropdownMenuItem(
    dropdownItems: List<TopbarDropdown>
) {

    dropdownItems.forEach { item ->

        DropdownMenuItem(
            text = {
                Text(text = item.title)
            },
            leadingIcon = {
                Icon(
                    imageVector = item.icon!!,
                    contentDescription = "Dropdown icon"
                )
            },
            onClick = { item.onItemClicked() }
        )
    }

}


//  Caretaker id card
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaretakerIDCard(
    context: Context,
    userDetails: UsersCollection
) {

    Card(
        modifier = Modifier
            .size(width = 300.dp, height = 400.dp),
        shape = CardDefaults.shape,
        elevation = CardDefaults.cardElevation()
    ) {
        //  main container
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary),
            contentAlignment = Alignment.Center
        ) {

            //  background logo
            Image(
                painter = painterResource(id = R.drawable.img_2),
                contentDescription = "houseops logo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )

            //  main content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                //  white circle
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(10.dp)
                        .background(MaterialTheme.colorScheme.onPrimary)
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(16.dp))

                //  Apartment name title
                if (userDetails.userExtraDetails != null) {

                    //  apartment name
                    userDetails.userExtraDetails?.let {
                        Text(
                            text = it[2],
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            color = Color.White,
                            modifier = Modifier
                                .wrapContentWidth()
                                .align(Alignment.Start)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    //  apartment location
                    userDetails.userExtraDetails?.let {
                        CaretakerIDRow(
                            icon = Icons.Outlined.LocationOn,
                            desc = it[3],
                            modifier = Modifier
                                .align(Alignment.Start)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                //  caretaker image with a badge
                if (userDetails.userIsCaretaker) {
                    BadgedImage(
                        context = context,
                        imageUri = userDetails.userImageUri!!,
                        borderColor = MaterialTheme.colorScheme.primary,
                        icon = Icons.Sharp.Star,
                        iconTint = MaterialTheme.colorScheme.primary
                    )
                } else {
                    BadgedImage(
                        context = context,
                        imageUri = userDetails.userImageUri!!,
                        borderColor = PendingColor,
                        icon = Icons.Filled.Timer,
                        iconTint = PendingColor
                    )
                }


                Spacer(modifier = Modifier.height(16.dp))

                //  name
                if (userDetails.userExtraDetails != null) {

                    //  caretaker name
                    userDetails.userExtraDetails?.let {
                        Text(
                            text = it[0],
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            color = Color.White,
                            modifier = Modifier
                                .wrapContentWidth()
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    //  caretaker phone number
                    userDetails.userExtraDetails?.let {
                        CaretakerIDRow(
                            icon = Icons.Outlined.Phone,
                            desc = it[4],
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                        )
                    }

                }

                //  houseops logo
                Image(
                    painter = painterResource(id = R.drawable.houseops_dark_final),
                    contentDescription = "houseops logo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(150.dp)
                )
            }
        }
    }
}

//  badged image
@Composable
fun BadgedImage(
    context: Context,
    imageUri: String,
    borderColor: Color,
    icon: ImageVector,
    iconTint: Color
) {
    Box(
        modifier = Modifier
            .width(90.dp)
            .wrapContentHeight()
    ) {

        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(imageUri)
                .crossfade(true)
                .placeholder(R.drawable.lady1)
                .build(),
            contentDescription = "Badged Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(90.dp)
                .border(
                    width = 4.dp,
                    shape = CircleShape,
                    color = borderColor
                )
        )

        //  the caretaker premium icon
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "Badge icon",
                tint = iconTint,
                modifier = Modifier
                    .size(26.dp)
            )
        }

    }
}

@Composable
fun ColumnScope.CaretakerIDRow(
    icon: ImageVector,
    desc: String,
    modifier: Modifier = Modifier
) {

    Row(
        modifier
            .wrapContentSize(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = icon,
            contentDescription = "ID Number",
            tint = Color.White.copy(alpha = 0.5f),
            modifier = Modifier
                .size(16.dp)
        )

        Text(
            text = desc,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

    }
}

@Preview
@Composable
fun CaretakerIDCardPrev() {
    CaretakerIDCard(
        context = LocalContext.current,
        userDetails = UsersCollection()
    )
}



























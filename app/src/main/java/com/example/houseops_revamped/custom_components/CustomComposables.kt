package com.example.houseops_revamped.custom_components

import android.content.Context
import android.net.Uri
import android.view.RoundedCorner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
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
import com.example.houseops_revamped.models.firestore.UsersCollection
import com.example.houseops_revamped.ui.theme.DarkBlueAccent

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
                .background(DarkBlueAccent)
        ) {

            //  houseops logo
            Image(
                painter = painterResource(id = R.drawable.houseops_dark_final),
                contentDescription = "houseops logo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )

            //  main content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(DarkBlueAccent.copy(alpha = 0.9f))
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                //  white circle
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(10.dp)
                        .background(MaterialTheme.colorScheme.onPrimary)
                        .align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(8.dp))

                //  caretaker id title
                Text(
                    text = "Caretaker ID",
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )

                //  caretaker image
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                ) {

                    //  image
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(userDetails.userImageUri)
                            .crossfade(true)
                            .placeholder(R.drawable.lady1)
                            .build(),
                        contentDescription = "Caretaker ID Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(80.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    //  name
                    if (userDetails.userExtraDetails != null) {
                        userDetails.userExtraDetails?.let {
                            Text(
                                text = it[0],
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                color = Color.White,
                                modifier = Modifier
                                    .wrapContentWidth()
                            )
                        }
                    }
                }

                //  caretaker details
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                ) {

                    val detailsIcons = listOf(
                        Icons.Outlined.ConfirmationNumber,
                        Icons.Outlined.Apartment,
                        Icons.Outlined.LocationOn,
                        Icons.Outlined.Phone
                    )

                    if (userDetails.userExtraDetails != null) {
                        //  id number
                        CaretakerIDRow(
                            icon = detailsIcons[0],
                            desc = userDetails.userExtraDetails!!.get(1)
                        )

                        //  apartment
                        CaretakerIDRow(
                            icon = detailsIcons[1],
                            desc = userDetails.userExtraDetails!!.get(2)
                        )

                        //  location
                        CaretakerIDRow(
                            icon = detailsIcons[2],
                            desc = userDetails.userExtraDetails!!.get(3)
                        )

                        //  phone number
                        CaretakerIDRow(
                            icon = detailsIcons[3],
                            desc = userDetails.userExtraDetails!!.get(4)
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun ColumnScope.CaretakerIDRow(
    icon: ImageVector,
    desc: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
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



























package com.example.houseops_revamped.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.houseops_revamped.R
import com.example.houseops_revamped.custom_components.BackPressTopAppBar
import com.example.houseops_revamped.custom_components.FormTextField
import com.example.houseops_revamped.models.TextFieldModel
import com.example.houseops_revamped.navigation.Direction
import com.example.houseops_revamped.ui.theme.BlueAccentLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaretakerRegistrationScreen(
    navHostController: NavHostController
) {

    val direction = Direction(navHostController)
    val context = LocalContext.current

    var fullNameState by remember { mutableStateOf("") }
    var idNumberState by remember { mutableStateOf("") }
    var apartmentNameState by remember { mutableStateOf("") }
    var locationState by remember { mutableStateOf("") }
    var phoneNumberState by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            BackPressTopAppBar(title = "Caretaker Registration") {
                direction.navigateBack()
            }
        }
    ) { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(contentPadding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.undraw_apartment_rent_o_0_ut),
                    contentDescription = "Request SVG",
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .align(Alignment.CenterHorizontally)
                )

                //  make request title
                Text(
                    text = "Make Request",
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = MaterialTheme.typography.titleMedium.fontWeight
                )

                //  icons list
                val requestFieldsModel = listOf(
                    TextFieldModel("Full Name", Icons.Outlined.PersonOutline),
                    TextFieldModel("ID Number", Icons.Outlined.Contacts),
                    TextFieldModel("Apartment Name", Icons.Outlined.LocationCity),
                    TextFieldModel("Location", Icons.Outlined.LocationOn),
                    TextFieldModel("Phone Number", Icons.Outlined.Phone),
                )

                //  populating the form fields
                requestFieldsModel.forEach { field ->
                    FormTextField(
                        startIcon = field.textFieldIcon,
                        endIcon = null,
                        placeholder = field.placeHolderText,
                        imeAction = ImeAction.Next,
                        keyboardType = when (field.placeHolderText) {
                            "ID Number", "Phone Number" -> {
                                KeyboardType.Number
                            }
                            else -> {
                                KeyboardType.Text
                            }
                        },
                        onInputFieldChanged = {
                            when (field.placeHolderText) {
                                "Full Name" -> {
                                    fullNameState = it
                                }
                                "ID Number" -> {
                                    idNumberState = it
                                }
                                "Apartment Name" -> {
                                    apartmentNameState = it
                                }
                                "Location" -> {
                                    locationState = it
                                }
                                "Phone Number" -> {
                                    phoneNumberState = it
                                }
                            }
                        }
                    )
                }

                //  submit button
                Button(
                    onClick = {
                        //  do something
                        val allFieldsList = listOf(
                            fullNameState, idNumberState, apartmentNameState, locationState,
                            phoneNumberState
                        )

                        Toast.makeText(context, allFieldsList.toString(), Toast.LENGTH_SHORT).show()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.White,
                    ),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    Text(text = "Submit Request")
                }

            }

        }
    }
}

@Preview(
    showSystemUi = true
)
@Composable
fun CaretakerRegistrationPrev() {
    CaretakerRegistrationScreen(rememberNavController())
}
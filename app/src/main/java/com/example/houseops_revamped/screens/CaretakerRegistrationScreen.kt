package com.example.houseops_revamped.screens

import android.util.Log
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
import com.example.houseops_revamped.models.Constants
import com.example.houseops_revamped.models.TextFieldModel
import com.example.houseops_revamped.navigation.Direction
import com.example.houseops_revamped.ui.theme.BlueAccentLight
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.Objects

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaretakerRegistrationScreen(
    navHostController: NavHostController
) {

    val direction = Direction(navHostController)
    val context = LocalContext.current
    val db = Firebase.firestore
    val auth by remember {
        mutableStateOf(Firebase.auth)
    }
    val currentUser by remember {
        mutableStateOf(auth.currentUser)
    }

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

            MainRegistrationFormView(db = db, auth = auth)

        }
    }
}

@Composable
fun MainRegistrationFormView(
    db: FirebaseFirestore,
    auth: FirebaseAuth
) {

    var fullNameState by remember { mutableStateOf("") }
    var idNumberState by remember { mutableStateOf("") }
    var apartmentNameState by remember { mutableStateOf("") }
    var locationState by remember { mutableStateOf("") }
    var phoneNumberState by remember { mutableStateOf("") }
    var hasUserMadeRequest by remember {
        mutableStateOf(false)
    }

    //  main content
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
                val allFieldsList = listOf<String>(
                    fullNameState,
                    idNumberState,
                    apartmentNameState,
                    locationState,
                    phoneNumberState,
                )

                hasUserMadeRequest = true

                //  update current user field
                updateUserDetails(
                    db = db,
                    auth = auth,
                    extraDetails = allFieldsList,
                    hasMadeRequest = hasUserMadeRequest,
                    onUpdateExtraDetails = {},
                    onUpdateHasMadeRequest = {}
                )
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

@Composable
fun PendingRegistrationView() {


}

fun updateUserDetails(
    db: FirebaseFirestore,
    auth: FirebaseAuth,
    extraDetails: List<String>,
    hasMadeRequest: Boolean,
    onUpdateExtraDetails: () -> Unit,
    onUpdateHasMadeRequest: () -> Unit
) {

    val userRef = db.collection(Constants.USERS_COLLECTION).document(auth.currentUser?.email!!)

    userRef
        .update("userExtraDetails", extraDetails)
        .addOnSuccessListener {
            onUpdateExtraDetails()
        }
        .addOnFailureListener {
            Log.d("Register", it.message.toString())
        }

    //  update the hasMade request field
    userRef
        .update("userHasMadeRequest", hasMadeRequest)
        .addOnSuccessListener {
            onUpdateHasMadeRequest()
        }
        .addOnFailureListener {
            Log.d("Register", it.message.toString())
        }

}

@Preview(
    showSystemUi = true
)
@Composable
fun CaretakerRegistrationPrev() {
    CaretakerRegistrationScreen(rememberNavController())
}
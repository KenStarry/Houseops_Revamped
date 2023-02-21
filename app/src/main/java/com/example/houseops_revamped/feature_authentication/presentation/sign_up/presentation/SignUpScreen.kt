package com.example.houseops_revamped.feature_authentication.presentation.sign_up.presentation

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.example.houseops_revamped.core.utils.Constants
import com.example.houseops_revamped.custom_components.BackPressTopAppBar
import com.example.houseops_revamped.core.utils.Constants.AUTHENTICATION_ROUTE
import com.example.houseops_revamped.feature_authentication.presentation.login.presentation.components.CustomTextField
import com.example.houseops_revamped.feature_authentication.presentation.sign_up.presentation.components.PickImage
import com.example.houseops_revamped.feature_authentication.presentation.sign_up.presentation.components.TermsAndConditions
import com.example.houseops_revamped.feature_authentication.presentation.sign_up.presentation.viewmodel.SignUpViewModel
import com.example.houseops_revamped.network.createAccount
import com.example.houseops_revamped.network.createUserCollection
import com.example.houseops_revamped.network.uploadImageToFirestore
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navHostController: NavHostController
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val signUpVM: SignUpViewModel = hiltViewModel()

    val primaryColor = Color(
        coreVM.primaryAccentFlow.collectAsState(
            initial = Constants.accentColors[0].darkColor
        ).value ?: Constants.accentColors[0].darkColor
    )

    val tertiaryColor = Color(
        coreVM.tertiaryAccentFlow.collectAsState(
            initial = Constants.accentColors[0].lightColor
        ).value ?: Constants.accentColors[0].lightColor
    )

    val auth = Firebase.auth

    Scaffold(
        topBar = {
            BackPressTopAppBar(title = "") {
                //  navigate back to login screen
                navHostController.navigate(AUTHENTICATION_ROUTE) {
                    popUpTo(AUTHENTICATION_ROUTE)
                }
            }
        }
    ) { contentPadding ->

        val context = LocalContext.current
        val scope = rememberCoroutineScope()

        var emailInputState by remember {
            mutableStateOf("")
        }
        var fullNameInputState by remember {
            mutableStateOf("")
        }
        var newPassInputState by remember {
            mutableStateOf("")
        }
        var confirmPassInputState by remember {
            mutableStateOf("")
        }

        //  Image Picker
        var imageUri by remember {
            mutableStateOf<Uri?>(null)
        }

        //  launcher for gallery activity
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->

            imageUri = uri
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
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

                //  signup text
                Text(
                    modifier = Modifier
                        .align(Alignment.Start),
                    text = "Sign Up",
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = MaterialTheme.typography.titleLarge.fontWeight
                )

                //  pick image icon
                PickImage(imageUri = imageUri) {
                    launcher.launch("image/*")
                }

                //  email address
                CustomTextField(
                    startIcon = Icons.Outlined.AlternateEmail,
                    endIcon = null,
                    placeholder = "Email Address",
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email,
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor,
                    onInput = {
                        emailInputState = it
                    }
                )

                //  full name
                CustomTextField(
                    startIcon = Icons.Outlined.Person,
                    endIcon = null,
                    placeholder = "UserName",
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text,
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor,
                    onInput = {
                        fullNameInputState = it
                    }
                )

                //  password
                CustomTextField(
                    startIcon = Icons.Outlined.Key,
                    endIcon = null,
                    placeholder = "New Password",
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Password,
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor,
                    isPassword = true,
                    onInput = {
                        newPassInputState = it
                    }
                )

                //  confirm password
                CustomTextField(
                    startIcon = Icons.Outlined.Key,
                    endIcon = null,
                    placeholder = "Confirm Password",
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password,
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor,
                    isPassword = true,
                    onInput = {
                        confirmPassInputState = it
                    }
                )

                //  terms and conditions text
                TermsAndConditions(
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor
                )

                //  Signup button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.onPrimary),
                    contentAlignment = Alignment.Center
                ) {

                    Button(
                        onClick = {
                            validateDetails(
                                scope = scope,
                                navHostController = navHostController,
                                context = context,
                                auth = auth,
                                imageUri = if (imageUri == null) null else imageUri,
                                email = emailInputState,
                                name = fullNameInputState,
                                newPass = newPassInputState,
                                confirmPass = confirmPassInputState
                            )
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = tertiaryColor,
                            disabledContainerColor = MaterialTheme.colorScheme.onSecondary.copy(
                                alpha = 0.3f
                            ),
                            disabledContentColor = Color.White.copy(alpha = 0.3f)
                        ),
                        enabled = true
                    ) {
                        Text(
                            text = "Create Account",
                            fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }

                }

            }

        }

    }
}

fun validateDetails(
    scope: CoroutineScope,
    navHostController: NavHostController,
    context: Context,
    auth: FirebaseAuth,
    imageUri: Uri?,
    email: String,
    name: String,
    newPass: String,
    confirmPass: String,
) {

    //  validate all details are correct
    val uri = imageUri.toString()

    if (email.isBlank())
        Toast.makeText(context, "Email cannot be blank!", Toast.LENGTH_SHORT).show()
    else if (name.isBlank())
        Toast.makeText(context, "Input your full name", Toast.LENGTH_SHORT).show()
    else if (newPass != confirmPass)
        Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
    else {


        //  create the user account
//        scope.launch(Dispatchers.IO) {
//            //   create account
//            createAccount(
//                auth = auth,
//                context = context,
//                navHostController = navHostController,
//                email = email,
//                password = newPass
//            )
//            //  create user in database
//            createUserCollection(
//                userName = name,
//                userEmail = email,
//                userImageUri = "",
//                userPassword = newPass,
//                userIsCaretaker = false,
//                userExtraDetails = listOf(),
//                userHasMadeRequest = false
//            )
//            // upload image to firestore
//            uploadImageToFirestore(
//                imageUri = imageUri!!,
//                email = email,
//                context = context,
//                onUploadFailure = {},
//                onUploadProgress = {}
//            )
//        }
    }
}
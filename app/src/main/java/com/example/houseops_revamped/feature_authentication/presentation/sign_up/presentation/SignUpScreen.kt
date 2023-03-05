package com.example.houseops_revamped.feature_authentication.presentation.sign_up.presentation

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.core.domain.model.events.CoreEvents
import com.example.houseops_revamped.core.presentation.components.LoadingCircle
import com.example.houseops_revamped.core.presentation.utils.Constants
import com.example.houseops_revamped.core.presentation.utils.Constants.ADMIN_ROUTE
import com.example.houseops_revamped.core.presentation.utils.Constants.AGENT_ROUTE
import com.example.houseops_revamped.core.presentation.utils.Constants.AUTHENTICATION_ROUTE
import com.example.houseops_revamped.core.presentation.utils.Constants.HOME_ROUTE
import com.example.houseops_revamped.core.presentation.utils.Constants.LANDLORD_ROUTE
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.example.houseops_revamped.custom_components.BackPressTopAppBar
import com.example.houseops_revamped.feature_authentication.domain.model.ValidationEvent
import com.example.houseops_revamped.feature_authentication.domain.utils.AuthConstants
import com.example.houseops_revamped.feature_authentication.presentation.model.RegistrationFormEvent
import com.example.houseops_revamped.feature_authentication.presentation.sign_up.domain.model.SignUpEvents
import com.example.houseops_revamped.feature_authentication.presentation.sign_up.presentation.components.*
import com.example.houseops_revamped.feature_authentication.presentation.sign_up.presentation.viewmodel.SignUpViewModel
import com.example.houseops_revamped.feature_authentication.presentation.viewmodel.AuthenticationViewModel
import com.example.houseops_revamped.navigation.Direction
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navHostController: NavHostController
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val signUpVM: SignUpViewModel = hiltViewModel()
    val authVM: AuthenticationViewModel = hiltViewModel()
    val direction = Direction(navHostController)
    val context = LocalContext.current

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

    Log.d("userType", "selected User : ${signUpVM.chosenUserType.value.userTitle}")

    val userType = coreVM.userTypeFlow.collectAsState(initial = null).value

    LaunchedEffect(key1 = context) {
        authVM.validationEvents.collect { event ->
            when (event) {
                is ValidationEvent.Success -> {

                    Log.d("signUp", "Validation success")

                    //  signup user
                    signUpVM.onEvent(SignUpEvents.CreateAccount(
                        email = authVM.formState.email,
                        password = authVM.formState.password,
                        response = {
                            when (it) {

                                is Response.Success -> {

                                    Log.d("signUp", "create account success")

                                    Toast.makeText(
                                        context,
                                        signUpVM.chosenUserType.value.userTitle,
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    //  create tenant collection
                                    signUpVM.onEvent(SignUpEvents.CreateUserCollection(
                                        user = UsersCollection(
                                            userName = authVM.formState.username,
                                            userEmail = authVM.formState.email,
                                            userPassword = authVM.formState.password,
                                            userImageUri = authVM.formState.imageUri.toString(),
                                            userLikedHouses = listOf(),
                                            userBookmarks = listOf(),
                                            userBookedHouses = listOf(),
                                            userType = signUpVM.chosenUserType.value.userTitle,
                                            userIsVerified = false
                                        ),
                                        response = { res ->
                                            when (res) {
                                                is Response.Success -> {

                                                    //  upload user image
                                                    coreVM.onEvent(CoreEvents.UploadImageToStorage(
                                                        imageUriList = listOf(authVM.formState.imageUri),
                                                        context = context,
                                                        storageRef = "user_images/${authVM.formState.email}",
                                                        collectionName = Constants.USERS_COLLECTION,
                                                        email = authVM.formState.email,
                                                        fieldToUpdate = "userImageUri",
                                                        onResponse = { response ->
                                                            when (response) {
                                                                is Response.Success -> {
                                                                    Log.d(
                                                                        "storage",
                                                                        "url : $response"
                                                                    )
                                                                }
                                                                is Response.Failure -> {
                                                                    Log.d(
                                                                        "storage",
                                                                        "error : $response"
                                                                    )
                                                                }
                                                            }
                                                        }
                                                    ))

                                                    when (signUpVM.chosenUserType.value.userTitle) {
                                                        AuthConstants.userTypes[0].userTitle -> {
                                                            //  navigate to landlord screen
                                                            direction.navigateToRoute(
                                                                LANDLORD_ROUTE,
                                                                AUTHENTICATION_ROUTE
                                                            )
                                                        }
                                                        AuthConstants.userTypes[1].userTitle -> {
                                                            //  navigate to tenant screen
                                                            direction.navigateToRoute(
                                                                HOME_ROUTE,
                                                                AUTHENTICATION_ROUTE
                                                            )
                                                        }
                                                        AuthConstants.userTypes[2].userTitle -> {
                                                            //  navigate to admin screen
                                                            direction.navigateToRoute(
                                                                ADMIN_ROUTE,
                                                                AUTHENTICATION_ROUTE
                                                            )
                                                        }
                                                        AuthConstants.userTypes[3].userTitle -> {
                                                            //  navigate to agent screen
                                                            direction.navigateToRoute(
                                                                AGENT_ROUTE,
                                                                AUTHENTICATION_ROUTE
                                                            )
                                                        }
                                                    }

                                                    Toast.makeText(
                                                        context,
                                                        "created account successfully",
                                                        Toast.LENGTH_SHORT
                                                    ).show()

                                                    signUpVM.onEvent(
                                                        SignUpEvents.ToggleLoadingCircles(
                                                            false
                                                        )
                                                    )
                                                }

                                                is Response.Failure -> {

                                                    Toast.makeText(
                                                        context,
                                                        "could not create account.",
                                                        Toast.LENGTH_SHORT
                                                    ).show()

                                                    signUpVM.onEvent(
                                                        SignUpEvents.ToggleLoadingCircles(
                                                            false
                                                        )
                                                    )
                                                }
                                            }
                                        }
                                    ))
                                }

                                is Response.Failure -> {
                                    Toast.makeText(
                                        context,
                                        "Something went wrong!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    signUpVM.onEvent(SignUpEvents.ToggleLoadingCircles(false))
                                }
                            }
                        }
                    ))
                }

                is ValidationEvent.Failure -> {
                    signUpVM.onEvent(SignUpEvents.ToggleLoadingCircles(false))
                }
            }
        }
    }

    Scaffold(
        topBar = {
            BackPressTopAppBar(title = "Sign Up") {
                //  navigate back to login screen
                navHostController.navigate(AUTHENTICATION_ROUTE) {
                    popUpTo(AUTHENTICATION_ROUTE)
                }
            }
        }
    ) { contentPadding ->

        //  Image Picker
        var imageUri by remember {
            mutableStateOf<Uri?>(null)
        }

        //  launcher for gallery activity
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->

            authVM.onEvent(RegistrationFormEvent.ImageUriChanged(uri))
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

                //  choose user type
                UserTypeToggle(
                    userTypes = AuthConstants.userTypes,
                    signUpVM = signUpVM,
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor,
                    onSelectUserType = {

                        signUpVM.onEvent(SignUpEvents.ToggleUserType(it))
                        //  save user type to datastore
                        coreVM.onEvent(CoreEvents.DatastoreSaveUserType(it.userTitle))
                    }
                )

                //  pick image icon
                PickImage(imageUri = imageUri) {
                    launcher.launch("image/*")
                }

                //  signup fields
                SignUpFields(
                    authVM = authVM,
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor
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
                        .height(50.dp)
                        .background(MaterialTheme.colorScheme.onPrimary),
                    contentAlignment = Alignment.Center
                ) {

                    if (signUpVM.isLoading.value) {

                        LoadingCircle(
                            primaryColor = primaryColor,
                            tertiaryColor = tertiaryColor
                        )

                    } else {
                        Button(
                            onClick = {
                                signUpVM.onEvent(SignUpEvents.ToggleLoadingCircles(true))

                                val isAdmin = Constants.adminEmails.any {
                                    it == authVM.formState.email
                                }
                                val isAgent = Constants.agentEmails.any {
                                    it == authVM.formState.email
                                }
                                val isNormalUser = !Constants.adminEmails.any {
                                    it == authVM.formState.email
                                } && !Constants.agentEmails.any {
                                    it == authVM.formState.email
                                }

                                if ((signUpVM.chosenUserType.value.userTitle == AuthConstants.userTypes[2].userTitle ||
                                            signUpVM.chosenUserType.value.userTitle == AuthConstants.userTypes[3].userTitle) &&
                                    !isAdmin && !isAgent
                                ) {
                                    Toast.makeText(
                                        context,
                                        "Please choose if landlord or tenant",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    signUpVM.onEvent(SignUpEvents.ToggleLoadingCircles(false))

                                } else {
                                    authVM.onEvent(RegistrationFormEvent.Submit)
                                }

                                coreVM.onEvent(
                                    CoreEvents.DatastoreSaveUserType(
                                        signUpVM.chosenUserType.value.userTitle
                                    )
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
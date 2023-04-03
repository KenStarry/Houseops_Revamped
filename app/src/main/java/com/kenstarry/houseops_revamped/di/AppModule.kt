package com.kenstarry.houseops_revamped.di

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.location.Geocoder
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.location.LocationServices
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.kenstarry.houseops_revamped.BuildConfig
import com.kenstarry.houseops_revamped.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideResources(
        @ApplicationContext context: Context
    ): Resources = context.resources

    //  provide fused location
    @Provides
    @Singleton
    fun provideFusedLocationClient(
        @ApplicationContext context: Context
    ) = LocationServices.getFusedLocationProviderClient(context)

    //  geocoder
    @Provides
    @Singleton
    fun provideGeocoder(
        @ApplicationContext context: Context
    ) = Geocoder(context, Locale.getDefault())

    //  FIREBASE
    //  Firestore instance
    @Provides
    @Singleton
    fun provideFirebaseFiresore() = Firebase.firestore

    //  Authentication instance
    @Provides
    @Singleton
    fun provideFirebaseAuthentication() = Firebase.auth

    //  Authentication instance
    @Provides
    @Singleton
    fun provideFirebaseStorage() = Firebase.storage

    //  google sign in instance
    @Provides
    @Singleton
    fun provideGoogleSignInOptions(
        resource: Resources
    ) = GoogleSignInOptions
        .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(resource.getString(R.string.default_web_client_id))
        .requestEmail()
        .build()

    //  INTENTS
    @Provides
    @Singleton
    @Named("Phone_Call_Intent")
    fun providePhoneCallIntent() = Intent(Intent.ACTION_CALL)

    //  phone dial intent
    @Provides
    @Singleton
    @Named("Phone_Dial_Intent")
    fun providePhoneDialIntent() = Intent(Intent.ACTION_DIAL)

    //  view intent
    @Provides
    @Singleton
    @Named("Action_View_Intent")
    fun provideViewIntent() = Intent(Intent.ACTION_VIEW)
}


















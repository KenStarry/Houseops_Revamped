package com.kenstarry.houseops_revamped.core.di

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kenstarry.houseops_revamped.core.data.datastore.preferences.UserDetailsPreference
import com.kenstarry.houseops_revamped.core.data.repository.CoreRepositoryImpl
import com.kenstarry.houseops_revamped.core.data.repository.LocationClientImpl
import com.kenstarry.houseops_revamped.core.domain.repository.CoreRepository
import com.kenstarry.houseops_revamped.core.domain.repository.LocationClient
import com.kenstarry.houseops_revamped.core.domain.use_cases.*
import com.kenstarry.houseops_revamped.feature_tenant.feature_settings.data.datastore.AccentPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideCoreRepository(
        db: FirebaseFirestore,
        auth: FirebaseAuth
    ) : CoreRepository = CoreRepositoryImpl(
        db = db,
        auth = auth
    )

    @Provides
    @Singleton
    fun provideLocationClient(
        @ApplicationContext context: Context,
        client: FusedLocationProviderClient
    ) : LocationClient = LocationClientImpl(
        context = context,
        client = client
    )

    @Provides
    @Singleton
    fun provideCoreUseCase(
        repo: CoreRepository,
        client: LocationClient
    ) = CoreUseCases(
        isUserLoggedIn = IsUserLoggedIn(repo),
        currentUser = CurrentUser(repo),
        logoutUser = LogoutUser(repo),
        userDetails = UserDetails(repo),
        caretakerDetails = CaretakerDetails(repo),
        updateField = UpdateField(repo),
        updateArrayField = UpdateUsersArrayField(repo),
        getAllCaretakers = GetAllCaretakers(repo),
        getApartments = GetApartments(repo),
        getApartmentDetails = GetApartmentDetails(repo),
        getAllAgents = GetAllAgents(repo),
        uploadImagesToStorage = UploadImagesToStorage(repo),
        uploadSingleImageToStorage = UploadSingleImageToStorage(repo),
        verificationEmail = VerificationEmail(repo),
        deleteDocument = DeleteDocument(repo),
        getPlaceCoordinates = GetPlaceCoordinates(repo),
        getCurrentLocation = GetCurrentLocation(client)
    )

    //  provide datastore accent preference
    @Provides
    @Singleton
    fun provideAccentPreference(
        @ApplicationContext context: Context
    ) = AccentPreference(context)

    @Provides
    @Singleton
    fun provideUserDetailsPreference(
        @ApplicationContext context: Context
    ) = UserDetailsPreference(context)
}











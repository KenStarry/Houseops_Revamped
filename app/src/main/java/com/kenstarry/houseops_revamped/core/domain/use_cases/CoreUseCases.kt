package com.kenstarry.houseops_revamped.core.domain.use_cases

data class CoreUseCases(
    val isUserLoggedIn: IsUserLoggedIn,
    val currentUser: CurrentUser,
    val logoutUser: LogoutUser,
    val userDetails: UserDetails,
    val caretakerDetails: CaretakerDetails,
    val updateField: UpdateField,
    val updateArrayField: UpdateUsersArrayField,
    val getAllCaretakers: GetAllCaretakers,
    val getApartments: GetApartments,
    val getApartmentDetails: GetApartmentDetails,
    val getAllAgents: GetAllAgents,
    val uploadImagesToStorage: UploadImagesToStorage,
    val uploadSingleImageToStorage: UploadSingleImageToStorage,
    val verificationEmail: VerificationEmail,
    val deleteDocument: DeleteDocument
) {
}
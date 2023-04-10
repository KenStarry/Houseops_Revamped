package com.kenstarry.houseops_revamped.window.model

sealed class WindowType {
    //  smaller phones
    object Compact : WindowType()

    //  medium-sized tablets
    object Medium : WindowType()

    //  large tablets
    object Expanded : WindowType()
}

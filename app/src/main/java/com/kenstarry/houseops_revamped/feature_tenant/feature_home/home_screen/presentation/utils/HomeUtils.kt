package com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.utils

class HomeUtils {
    companion object {

        val getNthWord: (str: String, pos: Int) -> String = { myString, position ->
            val words = myString.split(" ")

            if (words.size >= position)
                words[position - 1]
            else
                ""
        }
    }
}
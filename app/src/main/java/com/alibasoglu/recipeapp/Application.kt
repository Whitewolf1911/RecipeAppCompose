package com.alibasoglu.recipeapp

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Application : Application() {

    val isDarkTheme = mutableStateOf(true)

    fun toggleDarkMode() {
        isDarkTheme.value = !isDarkTheme.value
    }
}

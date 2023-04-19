package com.alibasoglu.recipeapp.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alibasoglu.recipeapp.domain.model.Recipe
import com.alibasoglu.recipeapp.domain.repository.RecipeRepository
import com.alibasoglu.recipeapp.presentation.model.RecipeListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    var state by mutableStateOf(RecipeListState())

    val query = mutableStateOf("")

    init {
        searchRecipe()
    }

    private fun searchRecipe() {
        viewModelScope.launch {
            val result = recipeRepository.searchRecipe(token = token, page = 1, query = "chicken")
            state = state.copy(list = result)
        }
    }

    fun onQueryChanged(query: String) {
        this.query.value = query
    }

    companion object {
        const val token = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
    }

}

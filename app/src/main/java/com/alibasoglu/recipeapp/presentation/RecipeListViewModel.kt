package com.alibasoglu.recipeapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alibasoglu.recipeapp.domain.repository.RecipeRepository
import com.alibasoglu.recipeapp.presentation.model.RecipeListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    var state by mutableStateOf(RecipeListState())

    val query = mutableStateOf("")

    private var searchJob: Job? = null

    init {
        searchRecipe("")
    }

    private fun searchRecipe(query: String) {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            delay(5000L)
            val result = recipeRepository.searchRecipe(token = TOKEN, page = 1, query = query)
            state = state.copy(list = result, isLoading = false)
        }
    }

    fun onQueryChanged(query: String) {
        searchJob?.cancel()
        this.query.value = query
        if (query.length >= 2) {
            searchJob = viewModelScope.launch {
                delay(SEARCH_QUERY_DELAY)
                searchRecipe(query)
            }
        }
    }

    companion object {
        const val TOKEN = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
        const val SEARCH_QUERY_DELAY = 1000L
    }

}

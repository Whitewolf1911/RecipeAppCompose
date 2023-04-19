package com.alibasoglu.recipeapp.di

import com.alibasoglu.recipeapp.data.RecipeRepositoryImpl
import com.alibasoglu.recipeapp.data.remote.RecipeService
import com.alibasoglu.recipeapp.domain.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRecipeService(): RecipeService {
        return Retrofit.Builder()
            .baseUrl("https://food2fork.ca/api/recipe/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeService::class.java)
    }

    @Provides
    @Singleton
    fun provideRecipeRepository(recipeService: RecipeService): RecipeRepository {
        return RecipeRepositoryImpl(recipeService = recipeService)
    }
}

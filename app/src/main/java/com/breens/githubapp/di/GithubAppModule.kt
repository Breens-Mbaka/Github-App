package com.breens.githubapp.di

import android.app.Application
import androidx.room.Room
import com.breens.githubapp.data.local.GithubAppDatabase
import com.breens.githubapp.data.network.GithubApi
import com.breens.githubapp.data.repositoryimplementation.GetUserInfoRepositoryImplementation
import com.breens.githubapp.data.util.Constants.BASE_URL
import com.breens.githubapp.domain.repository.GetUserProfileRepository
import com.breens.githubapp.domain.usecases.GetUserProfileUseCase
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GithubAppModule {

    @Singleton
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun okHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @ExperimentalSerializationApi
    fun retrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        val converterFactory = Json.asConverterFactory(contentType)
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideImageDatabase(application: Application): GithubAppDatabase {
        return Room.databaseBuilder(
            application,
            GithubAppDatabase::class.java,
            "github_app_db"
        ).build()
    }

    @Provides
    @Singleton
    fun getUserProfileRepository(
        database: GithubAppDatabase,
        githubApi: GithubApi

    ): GetUserProfileRepository {
        return GetUserInfoRepositoryImplementation(
            githubApi,
            database.userDao
        )
    }

    @Provides
    @Singleton
    fun provideGetUserProfileUseCase(repository: GetUserProfileRepository): GetUserProfileUseCase {
        return GetUserProfileUseCase(
            repository
        )
    }
}
package com.example.footballmanager.data.modules

import com.example.footballmanager.BuildConfig
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GoogleToken

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @GoogleToken
    fun provideGoogleToken(): String = BuildConfig.GOOGLE_TOKEN

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}

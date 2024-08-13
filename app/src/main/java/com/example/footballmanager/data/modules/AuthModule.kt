package com.example.footballmanager.data.modules

import com.example.footballmanager.ui.screens.login.firebase.AuthService
import com.example.footballmanager.ui.screens.login.firebase.FirebaseAuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    @Binds
    @Singleton
    abstract fun bindAuthService(
        firebaseAuthRepository: FirebaseAuthRepository
    ): AuthService
}

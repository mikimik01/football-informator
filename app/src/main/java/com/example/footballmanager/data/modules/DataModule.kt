package com.example.footballmanager.data.modules

import com.example.footballmanager.data.MatchesDataSource
import com.example.footballmanager.data.MatchesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindMatchesDataSource(
        matchesRepository: MatchesRepository
    ): MatchesDataSource
}
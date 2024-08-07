package com.example.footballmanager.data

import android.app.Application
import androidx.room.Room
import com.example.footballmanager.BuildConfig
import com.example.footballmanager.data.cache.DATABASE_NAME
import com.example.footballmanager.data.network.AuthInterceptor
import com.example.footballmanager.data.network.FootballApiService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FootballApi {

    @Provides
    fun provideBaseUrl(): String = BuildConfig.BASE_URL

    @Provides
    fun provideClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor())
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .client(client)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): FootballApiService =
        retrofit.create(FootballApiService::class.java)

//    @Provides
//    @Singleton
//    fun provideDatabase(app: Application) = Room.databaseBuilder(app, FixturesRoomDatabase::class.java, DATABASE_NAME)
//        .fallbackToDestructiveMigration()
//        .build()
//
//    @Provides
//    fun provideUserDao(db: FixturesRoomDatabase) = db.fixtureDao()
}
package com.example.weathermvi.di

import com.example.weathermvi.data.repository.WeatherRepositoryImpl
import com.example.weathermvi.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(repositoryImpl: WeatherRepositoryImpl) : WeatherRepository
}
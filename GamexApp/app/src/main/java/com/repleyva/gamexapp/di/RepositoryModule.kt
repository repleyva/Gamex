package com.repleyva.gamexapp.di

import com.repleyva.data.datasource.local.LocalDataSource
import com.repleyva.data.datasource.local.LocalDataSourceImpl
import com.repleyva.data.repository.GamesRepositoryImpl
import com.repleyva.domain.repository.GamesRepository
import com.repleyva.domain.usecase.GameInteractor
import com.repleyva.domain.usecase.GameUseCase
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
    abstract fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

    @Binds
    @Singleton
    abstract fun bindRepository(repository: GamesRepositoryImpl): GamesRepository

    @Binds
    @Singleton
    abstract fun bindGameUsecase(gameInteractor: GameInteractor): GameUseCase

}
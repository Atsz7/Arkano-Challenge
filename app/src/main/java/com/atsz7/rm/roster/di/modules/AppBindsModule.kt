package com.atsz7.rm.roster.di.modules

import com.atsz7.rm.roster.data.repositories.CharactersRepositoryImpl
import com.atsz7.rm.roster.domain.repositories.CharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBindsModule {

    @Binds
    @Singleton
    abstract fun bindCharactersRepository(
        implementation: CharactersRepositoryImpl
    ): CharactersRepository
}

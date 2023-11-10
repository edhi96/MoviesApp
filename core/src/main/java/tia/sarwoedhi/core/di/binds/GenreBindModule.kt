package tia.sarwoedhi.core.di.binds

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import tia.sarwoedhi.core.data.remote.source.genre.GenreNetworkSource
import tia.sarwoedhi.core.data.remote.source.genre.GenreNetworkSourceImpl
import tia.sarwoedhi.core.data.remote.repository.GenreRepositoryImpl
import tia.sarwoedhi.core.domain.repository.genre.GenreRepository

@InstallIn(ViewModelComponent::class)
@Module
abstract class GenreBindModule {

    @Binds
    @ViewModelScoped
    abstract fun binGenreNetworkSource(impl: GenreNetworkSourceImpl): GenreNetworkSource

    @Binds
    @ViewModelScoped
    abstract fun bindAuthRepository(impl: GenreRepositoryImpl): GenreRepository

}
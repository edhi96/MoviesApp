package tia.sarwoedhi.core.di.binds

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import tia.sarwoedhi.core.data.remote.source.movie.MovieNetworkSource
import tia.sarwoedhi.core.data.remote.source.movie.MovieNetworkSourceImpl
import tia.sarwoedhi.core.data.remote.repository.MovieRepositoryImpl
import tia.sarwoedhi.core.domain.repository.movie.MovieRepository


@InstallIn(ViewModelComponent::class)
@Module
abstract class MovieBindModule {

    @Binds
    @ViewModelScoped
    abstract fun bindMovieNetworkSource(impl: MovieNetworkSourceImpl): MovieNetworkSource

    @Binds
    @ViewModelScoped
    abstract fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository
}
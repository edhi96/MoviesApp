package tia.sarwoedhi.core.data.remote.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import tia.sarwoedhi.core.data.mapper.toDetailMovieDomain
import tia.sarwoedhi.core.data.mapper.toPagingMovieDomain
import tia.sarwoedhi.core.data.mapper.toPagingReviewDomain
import tia.sarwoedhi.core.data.mapper.toRequest
import tia.sarwoedhi.core.data.mapper.toVideoDomain
import tia.sarwoedhi.core.data.remote.base.ApiResult
import tia.sarwoedhi.core.data.remote.source.movie.MovieNetworkSource
import tia.sarwoedhi.core.di.dispatcher.IoDispatcher
import tia.sarwoedhi.core.domain.DomainWrapper
import tia.sarwoedhi.core.domain.model.request.DetailMovieParam
import tia.sarwoedhi.core.domain.model.request.DiscoverParam
import tia.sarwoedhi.core.domain.model.request.ReviewParam
import tia.sarwoedhi.core.domain.model.request.VideoParam
import tia.sarwoedhi.core.domain.model.response.DetailMovieEntity
import tia.sarwoedhi.core.domain.model.response.MovieEntity
import tia.sarwoedhi.core.domain.model.response.MovieReviewEntity
import tia.sarwoedhi.core.domain.model.response.VideoEntity
import tia.sarwoedhi.core.domain.repository.movie.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val dataSource: MovieNetworkSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : MovieRepository {
    override fun getListDiscoverMovie(param: DiscoverParam): Flow<PagingData<MovieEntity>> {
        return Pager(PagingConfig(5)) {
            MoviePagingSource(dataSource, genre = param.genre)
        }.flow.map { pagingData ->
            pagingData.toPagingMovieDomain()
        }.flowOn(ioDispatcher)
    }

    override fun getListReviewMovie(param: ReviewParam): Flow<PagingData<MovieReviewEntity>> =
        Pager(PagingConfig(5)) {
            ReviewPagingSource(dataSource, id = param.id)
        }.flow.map { pagingData ->
            pagingData.toPagingReviewDomain()
        }.flowOn(ioDispatcher)


    override suspend fun getDetailMovie(param: DetailMovieParam): DomainWrapper<DetailMovieEntity> = withContext(ioDispatcher) {
            when (val result = dataSource.getDetailMovie(request = param.toRequest())) {
                is ApiResult.Success -> {
                    DomainWrapper.Success(result.data.toDetailMovieDomain())
                }

                is ApiResult.Error -> {
                    DomainWrapper.Error(result.error)
                }
            }
    }


    override fun getListVideoTrailer(param: VideoParam): Flow<DomainWrapper<List<VideoEntity>>> =
        flow {
            when (val result = dataSource.getListVideoTrailer(request = param.toRequest())) {
                is ApiResult.Success -> {

                    result.data?.results?.let {
                        emit(DomainWrapper.Success(it.toVideoDomain()))
                    }

                }

                is ApiResult.Error -> {
                    emit(DomainWrapper.Error(result.error))
                }
            }
        }.flowOn(ioDispatcher)

}
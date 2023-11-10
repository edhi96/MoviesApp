package tia.sarwoedhi.core.domain.repository.movie

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tia.sarwoedhi.core.domain.DomainWrapper
import tia.sarwoedhi.core.domain.model.request.DetailMovieParam
import tia.sarwoedhi.core.domain.model.request.DiscoverParam
import tia.sarwoedhi.core.domain.model.request.ReviewParam
import tia.sarwoedhi.core.domain.model.request.VideoParam
import tia.sarwoedhi.core.domain.model.response.DetailMovieEntity
import tia.sarwoedhi.core.domain.model.response.MovieEntity
import tia.sarwoedhi.core.domain.model.response.MovieReviewEntity
import tia.sarwoedhi.core.domain.model.response.VideoEntity

interface MovieRepository {
    fun getListDiscoverMovie(param: DiscoverParam): Flow<PagingData<MovieEntity>>
    fun getListReviewMovie(param: ReviewParam): Flow<PagingData<MovieReviewEntity>>
    suspend fun getDetailMovie(param: DetailMovieParam): DomainWrapper<DetailMovieEntity>
    fun getListVideoTrailer(param: VideoParam): Flow<DomainWrapper<List<VideoEntity>>>
}
package tia.sarwoedhi.core.data.remote.source.movie

import tia.sarwoedhi.core.data.remote.api.MovieApi
import tia.sarwoedhi.core.data.remote.base.ApiResult
import tia.sarwoedhi.core.data.remote.base.BaseDataSourceImpl
import tia.sarwoedhi.core.data.remote.model.request.DetailMovieRequest
import tia.sarwoedhi.core.data.remote.model.request.DiscoverRequest
import tia.sarwoedhi.core.data.remote.model.request.ReviewRequest
import tia.sarwoedhi.core.data.remote.model.request.VideoRequest
import tia.sarwoedhi.core.data.remote.model.response.BaseListResponse
import tia.sarwoedhi.core.data.remote.model.response.detail_movie.DetailMovie
import tia.sarwoedhi.core.data.remote.model.response.detail_movie.MovieReview
import tia.sarwoedhi.core.data.remote.model.response.detail_movie.Video
import tia.sarwoedhi.core.data.remote.model.response.movie.DiscoverMovie
import javax.inject.Inject

class MovieNetworkSourceImpl @Inject constructor(
    private val movieApi: MovieApi
) : BaseDataSourceImpl(), MovieNetworkSource {
    override suspend fun getListDiscoverByGenre(request: DiscoverRequest): ApiResult<BaseListResponse<DiscoverMovie>> {
        return executeWithResponse {
            movieApi.getListDiscoverMovieByGenre(genre = request.genre, page = request.page)
        }
    }

    override suspend fun getListReview(request: ReviewRequest): ApiResult<BaseListResponse<MovieReview>> {
        return executeWithResponse {
            movieApi.getListReviewByMovieId(id = request.movieId, pageId = request.page)
        }
    }

    override suspend fun getDetailMovie(request: DetailMovieRequest): ApiResult<DetailMovie> {
        return executeWithResponse {
            movieApi.getDetailMovie(id = request.movieId)
        }
    }

    override suspend fun getListVideoTrailer(request: VideoRequest): ApiResult<BaseListResponse<Video>> {
        return executeWithResponse {
            movieApi.getListVideoByMovieId(id = request.movieId)
        }
    }
}
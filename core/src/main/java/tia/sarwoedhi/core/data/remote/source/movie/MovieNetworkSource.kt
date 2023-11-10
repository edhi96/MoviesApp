package tia.sarwoedhi.core.data.remote.source.movie

import tia.sarwoedhi.core.data.remote.base.ApiResult
import tia.sarwoedhi.core.data.remote.model.request.DetailMovieRequest
import tia.sarwoedhi.core.data.remote.model.request.DiscoverRequest
import tia.sarwoedhi.core.data.remote.model.request.ReviewRequest
import tia.sarwoedhi.core.data.remote.model.request.VideoRequest
import tia.sarwoedhi.core.data.remote.model.response.BaseListResponse
import tia.sarwoedhi.core.data.remote.model.response.detail_movie.DetailMovie
import tia.sarwoedhi.core.data.remote.model.response.movie.DiscoverMovie
import tia.sarwoedhi.core.data.remote.model.response.detail_movie.MovieReview
import tia.sarwoedhi.core.data.remote.model.response.detail_movie.Video


interface MovieNetworkSource {
    suspend fun getListDiscoverByGenre(request: DiscoverRequest): ApiResult<BaseListResponse<DiscoverMovie>>
    suspend fun getListReview(request: ReviewRequest): ApiResult<BaseListResponse<MovieReview>>
    suspend fun getDetailMovie(request: DetailMovieRequest): ApiResult<DetailMovie>
    suspend fun getListVideoTrailer(request: VideoRequest): ApiResult<BaseListResponse<Video>>
}
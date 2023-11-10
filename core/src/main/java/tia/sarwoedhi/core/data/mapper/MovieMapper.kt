package tia.sarwoedhi.core.data.mapper

import androidx.paging.PagingData
import androidx.paging.map
import tia.sarwoedhi.core.data.remote.model.request.DetailMovieRequest
import tia.sarwoedhi.core.data.remote.model.request.VideoRequest
import tia.sarwoedhi.core.data.remote.model.response.detail_movie.DetailMovie
import tia.sarwoedhi.core.data.remote.model.response.detail_movie.Video
import tia.sarwoedhi.core.data.remote.model.response.movie.DiscoverMovie
import tia.sarwoedhi.core.domain.model.request.DetailMovieParam
import tia.sarwoedhi.core.domain.model.request.VideoParam
import tia.sarwoedhi.core.domain.model.response.DetailMovieEntity
import tia.sarwoedhi.core.domain.model.response.MovieEntity
import tia.sarwoedhi.core.domain.model.response.VideoEntity

fun DiscoverMovie?.toDomain() =
    MovieEntity(
        id = this?.id,
        title = this?.title.orEmpty(),
        overview = this?.overview.orEmpty(),
        posterPath = this?.posterPath.orEmpty(),
        releaseDate = this?.releaseDate.orEmpty(),
        voteAverage = this?.voteAverage
    )

fun PagingData<DiscoverMovie>.toPagingMovieDomain() = this.map(DiscoverMovie::toDomain)


fun Video?.toDomain() =
    VideoEntity(
        id = this?.id.orEmpty(),
        name = this?.name.orEmpty(),
        site = this?.site.orEmpty(),
        size = this?.size,
        type = this?.type.orEmpty(),
        key = this?.key.orEmpty()
    )

fun List<Video>.toVideoDomain() = this.filter { it.official == true }.map(Video::toDomain)

fun VideoParam.toRequest(): VideoRequest {
    return VideoRequest(
        movieId = id,
    )
}

fun DetailMovieParam.toRequest(): DetailMovieRequest {
    return DetailMovieRequest(
        movieId = id,
    )
}

fun DetailMovie?.toDetailMovieDomain() =
    DetailMovieEntity(
        id = this?.id,
        name = this?.title.orEmpty(),
        releaseDate = this?.releaseDate.orEmpty(),
        voteAverage = this?.voteAverage,
        posterPath = this?.posterPath.orEmpty(),
        overView = this?.overview.orEmpty(),
        listGenre = this?.genres?.toDomain() ?: emptyList(),
    )
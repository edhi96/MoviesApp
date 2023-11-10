package tia.sarwoedhi.core.data.mapper

import androidx.paging.PagingData
import androidx.paging.map
import tia.sarwoedhi.core.data.remote.model.response.detail_movie.MovieReview
import tia.sarwoedhi.core.domain.model.response.MovieReviewEntity

fun MovieReview?.toDomain() =
    MovieReviewEntity(
        id = this?.id.orEmpty(),
        userName = this?.authorDetails?.username.orEmpty(),
        content = this?.content.orEmpty(),
        rating = this?.authorDetails?.rating,
    )

fun PagingData<MovieReview>.toPagingReviewDomain() = map(MovieReview::toDomain)
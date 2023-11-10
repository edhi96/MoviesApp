package tia.sarwoedhi.moviesapp.feature.detail_movie

import androidx.paging.PagingData
import tia.sarwoedhi.core.domain.model.response.DetailMovieEntity
import tia.sarwoedhi.core.domain.model.response.MovieReviewEntity

data class DetailMovieUiState(
    val detailMovieEntity: DetailMovieEntity = DetailMovieEntity(),
    val listReview: PagingData<MovieReviewEntity> = PagingData.empty(),
    val message: String? = "",
)
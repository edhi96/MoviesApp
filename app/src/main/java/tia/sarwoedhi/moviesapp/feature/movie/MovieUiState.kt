package tia.sarwoedhi.moviesapp.feature.movie

import androidx.paging.PagingData
import tia.sarwoedhi.core.domain.model.response.MovieEntity

data class MovieUiState(
    val listMovie: PagingData<MovieEntity> = PagingData.empty(),
)
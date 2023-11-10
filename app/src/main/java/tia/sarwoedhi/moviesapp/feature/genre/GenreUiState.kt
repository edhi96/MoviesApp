package tia.sarwoedhi.moviesapp.feature.genre

import tia.sarwoedhi.core.domain.model.response.GenreEntity

data class GenreUiState(
    val listGenre: List<GenreEntity> = listOf(),
    val message: String = "",
    val isError: Boolean = false,
    val isLoading: Boolean = false
)
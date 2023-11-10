package tia.sarwoedhi.moviesapp.feature.movie

import tia.sarwoedhi.core.domain.model.request.DiscoverParam

sealed interface MovieIntent {
    data class GetListMovieByGenre(val param: DiscoverParam) : MovieIntent
}
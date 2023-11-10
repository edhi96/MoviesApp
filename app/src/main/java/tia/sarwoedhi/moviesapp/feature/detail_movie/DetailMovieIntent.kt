package tia.sarwoedhi.moviesapp.feature.detail_movie

import tia.sarwoedhi.core.domain.model.request.DetailMovieParam
import tia.sarwoedhi.core.domain.model.request.ReviewParam


sealed interface DetailMovieIntent {
    data class GetListReviewByMovie(val param: ReviewParam) : DetailMovieIntent
    data class GetDetailMovie(val param: DetailMovieParam) : DetailMovieIntent
}
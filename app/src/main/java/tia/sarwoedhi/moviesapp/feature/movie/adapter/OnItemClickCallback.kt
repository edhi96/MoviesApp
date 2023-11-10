package tia.sarwoedhi.moviesapp.feature.movie.adapter

import tia.sarwoedhi.core.domain.model.response.MovieEntity

interface OnItemClickCallback {
    fun onItemClicked(data: MovieEntity)
}
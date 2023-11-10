package tia.sarwoedhi.moviesapp.feature.genre.adapter

import tia.sarwoedhi.core.domain.model.response.GenreEntity

interface OnGenreItemClickCallback {
    fun onItemClicked(data: GenreEntity)
}
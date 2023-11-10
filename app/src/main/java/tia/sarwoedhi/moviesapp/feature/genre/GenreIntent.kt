package tia.sarwoedhi.moviesapp.feature.genre

sealed interface GenreIntent {
    object GetListGenre : GenreIntent
}
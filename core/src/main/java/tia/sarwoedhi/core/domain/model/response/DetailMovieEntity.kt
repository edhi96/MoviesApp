package tia.sarwoedhi.core.domain.model.response


class DetailMovieEntity(
    val name: String? = "",
    val id: Int? = 0,
    val releaseDate:String? = "",
    val listGenre: List<GenreEntity>? = listOf(),
    val overView: String? = "",
    val voteAverage: Double? = 0.0,
    val posterPath: String? = ""
)
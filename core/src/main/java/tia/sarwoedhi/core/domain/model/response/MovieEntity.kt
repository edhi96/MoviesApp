package tia.sarwoedhi.core.domain.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieEntity(
    val id : Int? = 0,
    val title: String = "",
    val overview: String = "",
    val posterPath: String = "",
    val releaseDate: String = "",
    val voteAverage: Double? = 0.0,
) : Parcelable
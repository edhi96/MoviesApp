package tia.sarwoedhi.core.data.remote.model.response.genre

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListGenreResponse(
    @SerialName("genres")
    val genres: List<GenreMovie> = listOf()
)
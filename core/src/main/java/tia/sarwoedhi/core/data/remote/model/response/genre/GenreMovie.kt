package tia.sarwoedhi.core.data.remote.model.response.genre

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class GenreMovie(
    @SerialName("name")
    val name: String? = null,

    @SerialName("id")
    val id: Int? = null
)
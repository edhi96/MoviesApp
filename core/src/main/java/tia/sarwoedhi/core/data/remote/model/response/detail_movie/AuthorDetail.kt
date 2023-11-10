package tia.sarwoedhi.core.data.remote.model.response.detail_movie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthorDetail(

    @SerialName("avatar_path")
    val avatarPath: String? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("rating")
    val rating: Double? = null,

    @SerialName("username")
    val username: String? = null
)
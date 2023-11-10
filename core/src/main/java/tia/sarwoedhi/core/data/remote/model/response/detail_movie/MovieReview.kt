package tia.sarwoedhi.core.data.remote.model.response.detail_movie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieReview(

    @SerialName("author_details")
    val authorDetails: AuthorDetail? = null,

    @SerialName("updated_at")
    val updatedAt: String? = null,

    @SerialName("author")
    val author: String? = null,

    @SerialName("created_at")
    val createdAt: String? = null,

    @SerialName("id")
    val id: String? = null,

    @SerialName("content")
    val content: String? = null,

    @SerialName("url")
    val url: String? = null
)

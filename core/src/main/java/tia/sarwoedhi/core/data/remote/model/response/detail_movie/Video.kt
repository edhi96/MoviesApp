package tia.sarwoedhi.core.data.remote.model.response.detail_movie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Video(

	@SerialName("site")
	val site: String? = null,

	@SerialName("size")
	val size: Int? = null,

	@SerialName("iso_3166_1")
	val iso31661: String? = null,

	@SerialName("name")
	val name: String? = null,

	@SerialName("official")
	val official: Boolean? = null,

	@SerialName("id")
	val id: String? = null,

	@SerialName("published_at")
	val publishedAt: String? = null,

	@SerialName("type")
	val type: String? = null,

	@SerialName("iso_639_1")
	val iso6391: String? = null,

	@SerialName("key")
	val key: String? = null
)

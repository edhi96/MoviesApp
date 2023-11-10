package tia.sarwoedhi.core.domain.model.response

data class VideoEntity(
    val id: String,
    val name: String,
    val key: String,
    val site: String,
    val size: Int? = 0,
    val type: String
)
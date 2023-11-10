package tia.sarwoedhi.core.domain.model.response

data class MovieReviewEntity(
    val id : String,
    val userName : String,
    val content : String,
    val rating : Double? = 0.0
)
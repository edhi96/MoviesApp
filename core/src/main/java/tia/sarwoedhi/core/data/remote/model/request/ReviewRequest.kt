package tia.sarwoedhi.core.data.remote.model.request

data class ReviewRequest(
    val movieId: Int,
    val page: Int = 1
)
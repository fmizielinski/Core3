package example.core3.data.entity

data class Comment(
    val id: Long,
    val postId: Long,
    val name: String,
    val body: String,
    val email: String
)
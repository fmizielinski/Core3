package example.core3.domain.mapper

import example.core3.data.entity.Post
import example.core3.domain.dto.PostDto

class PostMapper {

    private fun map(post: Post) =
        PostDto(post.id, post.userId, post.title, post.body)

    fun map(posts: List<Post>): List<PostDto> =
            posts.map(::map)
}
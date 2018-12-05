package example.core3.domain.mapper

import example.core3.data.entity.Comment
import example.core3.domain.dto.CommentDto

class CommentMapper {

    private fun map(comment: Comment) =
        CommentDto(comment.name, comment.body, comment.email)

    fun map(comments: List<Comment>): List<CommentDto> =
        comments.map(::map)
}
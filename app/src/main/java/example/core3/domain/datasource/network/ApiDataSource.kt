package example.core3.domain.datasource.network

import example.core3.domain.dto.CommentDto
import example.core3.domain.dto.PostDto
import example.core3.domain.dto.UserDto
import io.reactivex.Single

interface ApiDataSource {

    fun getPosts(): Single<List<PostDto>>

    fun getComments(postId: Long): Single<List<CommentDto>>

    fun getUser(userId: Long): Single<UserDto>
}
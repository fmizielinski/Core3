package example.core3.domain.datasource.network

import example.core3.data.network.ApiService
import example.core3.domain.dto.CommentDto
import example.core3.domain.dto.PostDto
import example.core3.domain.dto.UserDto
import example.core3.domain.mapper.CommentMapper
import example.core3.domain.mapper.PostMapper
import example.core3.domain.mapper.UserMapper
import io.reactivex.Single

class ApiDataSourceImpl(
    private val apiService: ApiService,
    private val postMapper: PostMapper,
    private val commentMapper: CommentMapper,
    private val userMapper: UserMapper
) : ApiDataSource {

    override fun getPosts(): Single<List<PostDto>> =
        apiService.getPosts()
            .map(postMapper::map)

    override fun getComments(postId: Long): Single<List<CommentDto>> =
        apiService.getComments(postId)
            .map(commentMapper::map)

    override fun getUser(userId: Long): Single<UserDto> =
        apiService.getUser(userId)
            .map(userMapper::map)
}
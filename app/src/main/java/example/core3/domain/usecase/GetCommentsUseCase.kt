package example.core3.domain.usecase

import example.core3.common.runAsyncReturnOnMain
import example.core3.domain.datasource.network.ApiDataSource
import example.core3.domain.dto.CommentDto
import io.reactivex.Single

class GetCommentsUseCase(private val dataSource: ApiDataSource) {

    fun execute(postId: Long): Single<List<CommentDto>> =
        dataSource.getComments(postId)
            .runAsyncReturnOnMain()
}
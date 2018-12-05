package example.core3.domain.usecase

import example.core3.common.runAsyncReturnOnMain
import example.core3.domain.datasource.network.ApiDataSource
import example.core3.domain.dto.PostDto
import io.reactivex.Single

class GetPostsUseCase(private val dataSource: ApiDataSource) {

    fun execute(): Single<List<PostDto>> =
        dataSource.getPosts()
            .runAsyncReturnOnMain()
}
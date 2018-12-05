package example.core3.domain.usecase

import example.core3.common.runAsyncReturnOnMain
import example.core3.domain.datasource.network.ApiDataSource
import example.core3.domain.dto.UserDto
import io.reactivex.Single

class GetUserUseCase(private val dataSource: ApiDataSource) {

    fun execute(userId: Long): Single<UserDto> =
        dataSource.getUser(userId)
            .runAsyncReturnOnMain()
}
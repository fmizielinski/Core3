package example.core3.di

import example.core3.domain.mapper.CommentMapper
import example.core3.domain.mapper.PostMapper
import example.core3.domain.mapper.UserMapper
import example.core3.domain.usecase.GetCommentsUseCase
import example.core3.domain.usecase.GetPostsUseCase
import example.core3.domain.usecase.GetUserUseCase
import example.core3.view.main.details.presenter.DetailsPresenter
import example.core3.view.main.list.presenter.ListPresenter
import org.koin.androidx.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.module
import org.koin.experimental.builder.factory

object DomainModule {

    val module = module {

        factory<PostMapper>()
        factory<CommentMapper>()
        factory<UserMapper>()

        factory<GetPostsUseCase>()
        factory<GetCommentsUseCase>()
        factory<GetUserUseCase>()

        viewModel<ListPresenter>()
        viewModel<DetailsPresenter>()
    }
}
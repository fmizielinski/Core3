package example.core3.view.main.list.presenter

import example.core3.base.domain.presenter.BasePresenter
import example.core3.domain.dto.PostDto
import example.core3.domain.usecase.GetPostsUseCase
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

class ListPresenter(private val getPostsUseCase: GetPostsUseCase) : BasePresenter<ListViewing>() {

    private var posts: List<PostDto> = emptyList()

    //region lifecycle

    override fun onFirstBind() {
        getPosts()
    }

    override fun onViewRestoreState() {
        present { it.displayPosts(posts) }
    }

    //endregion lifecycle

    //region get posts

    fun getPosts() {
        present {
            it.displayLoading()
            it.hideError()
            it.hidePosts()
        }
        getPostsUseCase.execute()
            .subscribe(::getPostsSuccess, ::getPostsError)
            .addTo(compositeDisposable)
    }

    private fun getPostsSuccess(posts: List<PostDto>) {
        Timber.d("getPostsSuccess")
        this.posts = posts
        present {
            it.displayPosts(posts)
            it.hideLoading()
        }
    }

    private fun getPostsError(throwable: Throwable) {
        Timber.e(throwable)
        present {
            it.displayError()
            it.hideLoading()
            it.hidePosts()
        }
    }

    //endregion get posts
}
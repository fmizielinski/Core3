package example.core3.view.main.details.presenter

import example.core3.base.domain.presenter.BasePresenter
import example.core3.domain.dto.CommentDto
import example.core3.domain.dto.PostDto
import example.core3.domain.dto.UserDto
import example.core3.domain.usecase.GetCommentsUseCase
import example.core3.domain.usecase.GetUserUseCase
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

class DetailsPresenter(
    private val getCommentsUseCase: GetCommentsUseCase,
    private val getUserUseCase: GetUserUseCase
) : BasePresenter<DetailsViewing>() {

    private var post: PostDto? = null
    private var comments: List<CommentDto> = emptyList()
    private var user: UserDto? = null

    //region lifecycle

    override fun onFirstBind() {
    }

    override fun onViewRestoreState() {
        post?.let { post ->
            present { it.displayPost(post) }

            if (comments.isEmpty())
                getComments(post.id)
            else
                present { it.displayComments(comments) }

            if (user != null)
                present { it.displayUser(user!!) }
            else
                getUser(post.userId)
        }
    }

    //endregion lifecycle

    fun setData(post: PostDto) {
        this.post = post
        present { it.displayPost(post) }
        getComments(post.id)
        getUser(post.userId)
    }

    fun reloadData() {
        post?.let { post ->
            getComments(post.id)
            getUser(post.userId)
        }
    }

    //region comments

    private fun getComments(postId: Long) {
        present {
            it.displayLoading()
            it.hideError()
            it.hideComments()
        }
        getCommentsUseCase.execute(postId)
            .subscribe(::getCommentsSuccess, ::getCommentsError)
            .addTo(compositeDisposable)
    }

    private fun getCommentsSuccess(comments: List<CommentDto>) {
        Timber.d("getCommentsSuccess")
        present {
            it.displayComments(comments)
            it.hideLoading()
        }
    }

    private fun getCommentsError(throwable: Throwable) {
        Timber.e(throwable)
        present {
            it.displayError()
            it.hideLoading()
            it.hideComments()
        }
    }

    //endregion comments

    //region user

    private fun getUser(userId: Long) {
        present(DetailsViewing::hideUser)
        getUserUseCase.execute(userId)
            .subscribe(::getUserSuccess, ::getUserError)
            .addTo(compositeDisposable)
    }

    private fun getUserSuccess(user: UserDto) {
        Timber.d("getUserSuccess")
        this.user = user
        present { it.displayUser(user) }
    }

    private fun getUserError(throwable: Throwable) {
        Timber.e(throwable)
        present(DetailsViewing::hideUser)
    }

    fun onUserClicked() {
        user?.let { user ->
            present { it.sendEmail(user.email) }
        }
    }

    //endregion user
}
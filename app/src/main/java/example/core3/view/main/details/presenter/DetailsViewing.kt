package example.core3.view.main.details.presenter

import example.core3.base.domain.viewing.BaseViewing
import example.core3.domain.dto.CommentDto
import example.core3.domain.dto.PostDto
import example.core3.domain.dto.UserDto

interface DetailsViewing : BaseViewing {

    fun displayPost(post: PostDto)

    fun displayComments(comments: List<CommentDto>)

    fun hideComments()

    fun displayUser(user: UserDto)

    fun hideUser()

    fun sendEmail(email: String)

    fun displayError()

    fun hideError()

    fun displayLoading()

    fun hideLoading()
}
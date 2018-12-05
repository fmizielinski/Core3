package example.core3.view.main.list.presenter

import example.core3.base.domain.viewing.BaseViewing
import example.core3.domain.dto.PostDto

interface ListViewing : BaseViewing {

    fun displayPosts(posts: List<PostDto>)

    fun hidePosts()

    fun displayError()

    fun hideError()

    fun displayLoading()

    fun hideLoading()
}
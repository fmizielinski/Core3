package example.core3.view.main.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import example.core3.R
import example.core3.base.view.BaseFragment
import example.core3.domain.dto.CommentDto
import example.core3.domain.dto.PostDto
import example.core3.domain.dto.UserDto
import example.core3.view.main.details.adapter.CommentAdapter
import example.core3.view.main.details.presenter.DetailsPresenter
import example.core3.view.main.details.presenter.DetailsViewing
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : BaseFragment<DetailsViewing, DetailsPresenter>(), DetailsViewing {

    //region presenter

    private val presenter: DetailsPresenter by viewModel()

    override fun providePresenter() = presenter

    //endregion presenter

    //region lifecycle

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecycler()
        setUpErrors()

        textDetailsAuthor.setOnClickListener { presenter.onUserClicked() }

        val post = DetailsFragmentArgs.fromBundle(arguments).post
        presenter.setData(post)
    }

    //endregion lifecycle

    //region setup

    private fun setUpRecycler() {
        val layoutManager = LinearLayoutManager(context)
        val adapter = CommentAdapter()

        recyclerDetailsComments.adapter = adapter
        recyclerDetailsComments.layoutManager = layoutManager
    }

    private fun setUpErrors() {
        textDetailsError.setOnClickListener { presenter.reloadData() }
        imageDetailsError.setOnClickListener { presenter.reloadData() }
    }

    //endregion setup

    //region viewing

    override fun displayPost(post: PostDto) {
        textDetailsTitle.text = post.title
        textDetailsBody.text = post.body
    }

    override fun displayComments(comments: List<CommentDto>) {
        recyclerDetailsComments.visibility = View.VISIBLE
        (recyclerDetailsComments.adapter as? CommentAdapter)?.list = comments
    }

    override fun hideComments() {
        recyclerDetailsComments.visibility = View.GONE
    }

    override fun displayUser(user: UserDto) {
        textDetailsAuthor.visibility = View.VISIBLE
        textDetailsAuthor.text = String.format(getString(R.string.all_author), user.name)
    }

    override fun hideUser() {
        textDetailsAuthor.visibility = View.GONE
    }

    override fun sendEmail(email: String) {
        val mailTo = "mailto:$email"

        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse(mailTo)

        startActivity(emailIntent)
    }

    override fun displayError() {
        imageDetailsError.visibility = View.VISIBLE
        textDetailsError.visibility = View.VISIBLE
    }

    override fun hideError() {
        imageDetailsError.visibility = View.GONE
        textDetailsError.visibility = View.GONE
    }

    override fun displayLoading() {
        progressDetails.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressDetails.visibility = View.GONE
    }

    //endregion viewing
}
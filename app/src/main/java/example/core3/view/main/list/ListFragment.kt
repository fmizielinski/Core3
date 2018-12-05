package example.core3.view.main.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import example.core3.R
import example.core3.base.view.BaseFragment
import example.core3.domain.dto.PostDto
import example.core3.view.main.list.adapter.PostAdapter
import example.core3.view.main.list.presenter.ListPresenter
import example.core3.view.main.list.presenter.ListViewing
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : BaseFragment<ListViewing, ListPresenter>(), ListViewing {

    //region presenter

    private val presenter: ListPresenter by viewModel()

    override fun providePresenter() = presenter

    //endregion presenter

    //region lifecycle

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecycler()
        setUpErrors()
    }

    //endregion lifecycle

    //region setup

    private fun setUpRecycler() {
        val layoutManager = LinearLayoutManager(context)
        val adapter = PostAdapter()
        adapter.onItemSelectedListener
            .subscribe(::openDetails)

        recyclerList.adapter = adapter
        recyclerList.layoutManager = layoutManager
    }

    private fun setUpErrors() {
        textListError.setOnClickListener { presenter.getPosts() }
        imageListError.setOnClickListener { presenter.getPosts() }
    }

    //endregion setup

    private fun openDetails(post: PostDto) {
        val action = ListFragmentDirections.actionListFragmentToDetailsFragment(post)
        findNavController().navigate(action)
    }

    //region viewing

    override fun displayPosts(posts: List<PostDto>) {
        recyclerList.visibility = View.VISIBLE
        (recyclerList.adapter as? PostAdapter)?.list = posts
    }

    override fun hidePosts() {
        recyclerList.visibility = View.GONE
    }

    override fun displayError() {
        textListError.visibility = View.VISIBLE
        imageListError.visibility = View.VISIBLE
    }

    override fun hideError() {
        textListError.visibility = View.GONE
        imageListError.visibility = View.GONE
    }

    override fun displayLoading() {
        progressList.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressList.visibility = View.GONE
    }

    //endregion viewing
}
package eu.mmassi.expensesmanager.ui.main.posts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment

import eu.mmassi.expensesmanager.R
import eu.mmassi.expensesmanager.di.app.ViewModelProviderFactory
import eu.mmassi.expensesmanager.models.Resource
import kotlinx.android.synthetic.main.fragment_posts.recycler_view
import javax.inject.Inject

class PostsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    @Inject
    lateinit var postsRecyclerAdapter: PostsRecyclerAdapter

    private lateinit var postsViewModel: PostsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        retainInstance = true
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            postsViewModel = viewModelProviderFactory.create(PostsViewModel::class.java)
        }

        initRecyclerView()
        postsViewModel.posts.observe(viewLifecycleOwner) { postsResource ->
            when (postsResource) {
                is Resource.Success -> postsRecyclerAdapter.setPosts(postsResource.data)
                is Resource.Error -> Log.e(TAG, "Error loading posts: ${postsResource.message}")
                is Resource.Loading -> {}
            }
        }
    }

    private fun initRecyclerView() =
        recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(VerticalSpaceItemDecoration(8))
            adapter = postsRecyclerAdapter
        }

    companion object {

        private val TAG = PostsFragment::class.java.name
    }
}

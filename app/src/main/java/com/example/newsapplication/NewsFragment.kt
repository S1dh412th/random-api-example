package com.example.newsapplication

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news_api.Instance
import com.example.newsapplication.databinding.FragmentNewsBinding
import kotlinx.coroutines.launch

class NewsFragment : Fragment() {
    private var binding: FragmentNewsBinding? = null
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var newsFragmentViewModel: NewsFragmentViewModel

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getNews(2)
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            getNews(1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(layoutInflater)
        newsFragmentViewModel = ViewModelProvider(this)[NewsFragmentViewModel::class.java]
        getNews(1)

        return binding!!.root
    }

    private fun getNews(noOfItem: Int) {

        manager = GridLayoutManager(this.context, noOfItem)

        lifecycleScope.launch {

            val newsRepository = Instance.getNewsRepository()

            if (newsFragmentViewModel.getSavedResponse() == null) {
                newsFragmentViewModel.saveResponse(newsRepository.getNewsTopHeadlines())
            }

            binding?.newsRecyclerView?.apply {
                val adapterRef = newsFragmentViewModel.getSavedResponse()
                    ?.let { NewsRecyclerViewAdapter(it.articles) }
                adapter = adapterRef
                adapterRef?.setOnItemClickListener(object : NewsRecyclerViewAdapter.OnItemClickListener{
                    override fun onItemClick(position: Int, externalLink: String?) {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(externalLink))
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        context.startActivity(intent)
                    }
                })
                layoutManager = manager
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = NewsFragment()
    }
}
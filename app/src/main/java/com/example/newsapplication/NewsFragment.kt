package com.example.newsapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news_api.Instance
import com.example.news_api.model.NewsResponse
import com.example.newsapplication.databinding.FragmentNewsBinding
import kotlinx.coroutines.launch

class NewsFragment : Fragment() {
    private var binding: FragmentNewsBinding? = null
    private lateinit var manager: RecyclerView.LayoutManager
    private var noOfItem: Int = 1
    private var response: NewsResponse? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(layoutInflater)
        getNews()
        return binding!!.root
    }

    private fun getNews() {

        manager = GridLayoutManager(this.context, noOfItem)

        lifecycleScope.launch {

            val newsRepository = Instance.getNewsRepository()

            if (response == null) {
                response = newsRepository.getNewsTopHeadlines()
            }

            binding?.newsRecyclerView?.apply {
                val adapterRef = NewsRecyclerViewAdapter(response!!.articles)
                adapter = adapterRef
                adapterRef.setOnItemClickListener(object : NewsRecyclerViewAdapter.OnItemClickListener{
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
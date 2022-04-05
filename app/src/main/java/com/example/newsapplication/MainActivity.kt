package com.example.newsapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news_api.Instance
import com.example.newsapplication.databinding.ActivityMainBinding
import com.example.newsapplication.databinding.BoredApiBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var binding2: BoredApiBinding
    private lateinit var manager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        execute()
    }

    private fun execute() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        manager = LinearLayoutManager(this)

        lifecycleScope.launch {

            val newsRepository = Instance.getNewsRepository()
            val response = newsRepository.getNewsTopHeadlines()

            binding.newsRecyclerView.apply {
                val adapterRef = NewsRecyclerViewAdapter(response.articles)
                adapter = adapterRef
                adapterRef.setOnItemClickListener(object : NewsRecyclerViewAdapter.OnItemClickListener{
                    override fun onItemClick(position: Int, externalLink: String?) {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(externalLink))
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        intent.setPackage("com.android.chrome")
                        context.startActivity(intent)

                    }
                })
                layoutManager = manager
            }
        }
    }

    private fun execute2() {
        binding2 = BoredApiBinding.inflate(layoutInflater)
        setContentView(binding2.root)

        getBoredData()
    }

    private fun getBoredData() {
        lifecycleScope.launch {

            binding2.apply {
                apiData = com.example.bored_api.Instance.getBoredRepository().getActivity()

                extenalLink.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(extenalLink.text.toString()))
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                    intent.setPackage("com.android.chrome")
                    startActivity(intent)
                }

                buttonRefersh.setOnClickListener {
                    getBoredData()
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.news -> execute()

            R.id.bored -> execute2()

            else -> return super.onOptionsItemSelected(item)
        }

        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item_menu, menu)
        return true
    }

}
package com.example.newsapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.news_api.model.Articles
import com.example.newsapplication.databinding.ListNewsBinding
import com.squareup.picasso.Picasso

class NewsRecyclerViewAdapter(private val items: List<Articles>) : RecyclerView.Adapter<JsonViewHolder>() {

    private lateinit var iListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int, externalLink: String?)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        iListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JsonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = ListNewsBinding.inflate(layoutInflater, parent,false)
        return JsonViewHolder(listItem, iListener)
    }

    override fun onBindViewHolder(holder: JsonViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class JsonViewHolder(private val view : ListNewsBinding, listener: NewsRecyclerViewAdapter.OnItemClickListener) : RecyclerView.ViewHolder(view.root) {

    private var externalLink: String? = null

    init {
        view.root.setOnClickListener {
            listener.onItemClick(adapterPosition, externalLink)
        }
    }

    fun bind(article: Articles) {
        val picasso = Picasso.get()
        picasso.load(article.urlToImage).into(view.newsImage)
        externalLink = article.url
        view.newsEntry = article
    }
}
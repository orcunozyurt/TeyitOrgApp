package com.nerdz.teyitorgapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.nerdz.teyitorgapp.BR
import com.nerdz.teyitorgapp.R
import com.nerdz.teyitorgapp.databinding.ViewNewsListItemBinding
import com.nerdz.teyitorgapp.model.Model
import com.nerdz.teyitorgapp.viewmodel.NewsListViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_news_list_item.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class NewsListAdapter(private val newsListViewModel: NewsListViewModel) : RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder>() {
    var newsList: List<Model.NewsOverview> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = ViewNewsListItemBinding.inflate(inflater, parent, false)
        return NewsListViewHolder(dataBinding, newsListViewModel)
    }

    override fun getItemCount() = newsList.size

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        holder.setup(newsList[position])
    }

    fun updateNewsList(repoList: List<Model.NewsOverview>) {
        this.newsList = repoList
        notifyDataSetChanged()
    }

    fun clearNewsList() {
        this.newsList = emptyList()
        notifyDataSetChanged()
    }

    class NewsListViewHolder constructor(private val dataBinding: ViewDataBinding, private val newsListViewModel: NewsListViewModel)
        : RecyclerView.ViewHolder(dataBinding.root) {

        val avatarImage = itemView.item_avatar
        fun setup(itemData: Model.NewsOverview) {
            dataBinding.setVariable(BR.itemData, itemData)
            dataBinding.executePendingBindings()

            Picasso.get().load(itemData.thumbnail).into(avatarImage)

            itemView.onClick {
                val bundle = bundleOf("url" to itemData.url_slug)
                itemView.findNavController().navigate(R.id.action_newsListFragment_to_newsDetailFragment, bundle)
            }
        }
    }

}
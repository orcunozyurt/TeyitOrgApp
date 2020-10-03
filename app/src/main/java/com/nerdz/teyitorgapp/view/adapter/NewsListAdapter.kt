package com.nerdz.teyitorgapp.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.nerdz.teyitorgapp.BR
import com.nerdz.teyitorgapp.R
import com.nerdz.teyitorgapp.databinding.ViewNewsListItemBinding
import com.nerdz.teyitorgapp.model.Model
import com.nerdz.teyitorgapp.utils.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_news_list_item.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class NewsListAdapter() : RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder>() {
    var newsList: List<Model.Post> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = ViewNewsListItemBinding.inflate(inflater, parent, false)
        return NewsListViewHolder(dataBinding)
    }

    override fun getItemCount() = newsList.size

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        holder.setup(newsList[position])
    }

    fun updateNewsList(repoList: List<Model.Post>) {
        this.newsList = repoList
        notifyDataSetChanged()
    }

    fun clearNewsList() {
        this.newsList = emptyList()
        notifyDataSetChanged()
    }

    class NewsListViewHolder constructor(private val dataBinding: ViewDataBinding)
        : RecyclerView.ViewHolder(dataBinding.root) {

        val headImage = itemView.item_head
        val avatarImage = itemView.author_image
        fun setup(itemData: Model.Post) {
            dataBinding.setVariable(BR.itemData, itemData)
            dataBinding.executePendingBindings()

            Picasso.get().load(itemData.img_src).into(headImage)
            if (itemData.authors[0].details.profile_image.isNotEmpty()) {
                avatarImage.visibility = View.VISIBLE
                Picasso.get().load(itemData.authors[0].details.profile_image)
                    .transform(CircleTransform()).into(avatarImage)
            } else {
                avatarImage.visibility = View.GONE
            }

            itemView.onClick {
                val bundle = bundleOf("url" to itemData.getSlug())
                itemView.findNavController().navigate(R.id.action_newsListFragment_to_newsDetailFragment, bundle)
            }
        }
    }

}
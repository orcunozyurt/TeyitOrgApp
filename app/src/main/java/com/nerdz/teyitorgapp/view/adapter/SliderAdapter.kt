package com.nerdz.teyitorgapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nerdz.teyitorgapp.R
import com.smarteist.autoimageslider.SliderViewAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_slider_item.view.*

class SliderAdapter() : SliderViewAdapter<SliderAdapter.SliderViewHolder>() {
    var imagesList: List<String> = emptyList()

    fun updateNewsList(imagesList: List<String>) {
        this.imagesList = imagesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?): SliderViewHolder {
        val inflater = LayoutInflater.from(parent?.context).inflate(R.layout.image_slider_item, parent,false)
        return SliderViewHolder(inflater)
    }

    override fun getCount(): Int {
        return imagesList.size
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.setup(imagesList[position])
    }

    class SliderViewHolder(itemView: View) : SliderViewAdapter.ViewHolder(itemView) {
        val avatarImage = itemView.iv_auto_image_slider
        fun setup(itemData: String?) {
            Picasso.get().load(itemData).into(avatarImage)
        }
    }
}
package com.nerdz.teyitorgapp.view.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nerdz.teyitorgapp.BR
import com.nerdz.teyitorgapp.databinding.FragmentNewsDetailBinding
import com.nerdz.teyitorgapp.view.adapter.SliderAdapter
import com.nerdz.teyitorgapp.viewmodel.NewsDetailViewModel
import kotlinx.android.synthetic.main.fragment_news_detail.*


class NewsDetailFragment : Fragment() {
    private lateinit var viewDataBinding: FragmentNewsDetailBinding
    private lateinit var adapter: SliderAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentNewsDetailBinding.inflate(inflater, container, false).apply {
            viewmodel = ViewModelProviders.of(this@NewsDetailFragment).get(NewsDetailViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val urlSlug = arguments?.let { NewsDetailFragmentArgs.fromBundle(it).url }
        urlSlug?.let { viewDataBinding.viewmodel?.fetchNewsDetail(it) }
        setupAdapter()
        setupObservers()
    }

    private fun setupObservers() {
        viewDataBinding.viewmodel?.newsDetailLive?.observe(viewLifecycleOwner, Observer {
            adapter.updateNewsList(mutableListOf(it.featured_image))
            viewDataBinding.setVariable(BR.newsItem, it)
            viewDataBinding.executePendingBindings()
        })

    }

    private fun  setupAdapter() {
            adapter = SliderAdapter()
            imageSlider.sliderAdapter = adapter
    }
}

package com.nerdz.teyitorgapp.view.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nerdz.teyitorgapp.databinding.FragmentNewsListBinding

import com.nerdz.teyitorgapp.view.adapter.NewsListAdapter
import com.nerdz.teyitorgapp.viewmodel.NewsListViewModel
import kotlinx.android.synthetic.main.fragment_news_list.*

class NewsListFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentNewsListBinding
    private lateinit var adapter: NewsListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewDataBinding = FragmentNewsListBinding.inflate(inflater, container, false).apply {
            viewmodel = ViewModelProviders.of(this@NewsListFragment).get(NewsListViewModel::class.java)
            setLifecycleOwner(viewLifecycleOwner)
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeContainer.isRefreshing = true
        viewDataBinding.viewmodel?.fetchNewsList()
        setupAdapter()
        setupObservers()

        swipeContainer?.setOnRefreshListener {
            adapter.clearNewsList()
            viewDataBinding.viewmodel?.fetchNewsList()
        }
    }

    private fun setupObservers() {
        viewDataBinding.viewmodel?.newsListLive?.observe(viewLifecycleOwner, Observer {
            adapter.updateNewsList(it)
            swipeContainer.isRefreshing = false
        })

    }

    private fun  setupAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            adapter = NewsListAdapter()
            val layoutManager = LinearLayoutManager(activity)
            news_list_rv.layoutManager = layoutManager
            news_list_rv.adapter = adapter
        }
    }
}

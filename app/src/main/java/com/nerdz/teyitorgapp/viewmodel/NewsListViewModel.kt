package com.nerdz.teyitorgapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.nerdz.teyitorgapp.model.Model
import com.nerdz.teyitorgapp.model.NewsRepository

class NewsListViewModel : BaseViewModel() {
    val newsListLive = MutableLiveData<List<Model.NewsOverview>>()

    fun fetchNewsList() {
        dataLoading.value = true
        NewsRepository.getInstance().getNewsList { isSuccess, response ->
            dataLoading.value = false
            if (isSuccess) {
                newsListLive.value = response
                empty.value = false
            } else {
                empty.value = true
            }
        }
    }
}
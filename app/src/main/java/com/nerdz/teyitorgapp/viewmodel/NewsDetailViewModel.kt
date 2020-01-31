package com.nerdz.teyitorgapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.nerdz.teyitorgapp.model.Model
import com.nerdz.teyitorgapp.model.NewsRepository

class NewsDetailViewModel : BaseViewModel() {
    val newsDetailLive = MutableLiveData<Model.NewsSingle>()

    fun fetchNewsDetail(url_slug : String) {
        dataLoading.value = true
        NewsRepository.getInstance().getNewsDetail(url_slug) { isSuccess, response ->
            dataLoading.value = false
            if (isSuccess) {
                //Log.d("fetchNewsDetail", response.toString())
                newsDetailLive.value = response
                empty.value = false
            } else {
                empty.value = true
            }
        }
    }
}
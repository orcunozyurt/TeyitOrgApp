package com.nerdz.teyitorgapp.model

import com.nerdz.teyitorgapp.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository {

    fun getNewsList(onResult: (isSuccess: Boolean, response: Model.NewsOverview?) -> Unit) {
        ApiClient.instance.getNews().enqueue(object : Callback<Model.NewsOverview> {
            override fun onFailure(call: Call<Model.NewsOverview>, t: Throwable) {
                onResult(false, null)
            }

            override fun onResponse(call: Call<Model.NewsOverview>, response: Response<Model.NewsOverview>?) {
                if (response != null && response.isSuccessful)
                    onResult(true, response.body()!!)
                else
                    onResult(false, null)
            }

        })
    }

    fun getNewsDetail(url_slug: String, onResult: (isSuccess: Boolean, response: Model.NewsSingle?) -> Unit) {
        ApiClient.instance_TYT.getNewsDetail(url_slug).enqueue(object : Callback<Model.NewsSingle> {
            override fun onFailure(call: Call<Model.NewsSingle>, t: Throwable) {
                onResult(false, null)
            }

            override fun onResponse(call: Call<Model.NewsSingle>, response: Response<Model.NewsSingle>) {
                if (response != null && response.isSuccessful)
                    onResult(true, response.body()!!)
                else
                    onResult(false, null)
            }

        })
    }

    companion object {
        private var INSTANCE: NewsRepository? = null
        fun getInstance() = INSTANCE
            ?: NewsRepository().also {
                INSTANCE = it
            }
    }
}
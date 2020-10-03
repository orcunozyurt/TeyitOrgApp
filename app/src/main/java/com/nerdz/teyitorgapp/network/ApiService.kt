package com.nerdz.teyitorgapp.network

import com.nerdz.teyitorgapp.model.Model
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/home-page")
    fun getNews(): Call<Model.NewsOverview>

    @GET("/fact/{url_slug}")
    fun getNewsDetail(@Path("url_slug") url_slug : String): Call<Model.NewsSingle>
}
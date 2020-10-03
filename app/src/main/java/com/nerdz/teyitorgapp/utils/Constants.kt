package com.nerdz.teyitorgapp.utils

import com.nerdz.teyitorgapp.BuildConfig

class Constants {
    companion object {
        const val BASE_URL = "https://teyit-scrapper-api.herokuapp.com/"
        const val BASE_URL_V2 = "https://tyt.teyit.org/"
        const val REQUEST_TIMEOUT_DURATION = 10
        val DEBUG = BuildConfig.DEBUG
    }
}
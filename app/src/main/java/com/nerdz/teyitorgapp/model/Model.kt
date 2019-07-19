package com.nerdz.teyitorgapp.model

import java.util.Arrays

object Model {
    data class NewsSingle(val title: String,
                          val claim: String,
                          val author: String,
                          val proven: Boolean,
                          val date: String,
                          val content: String,
                          val images: List<String>) {

        override fun toString(): String {
            return "NewsSingle(title='$title', claim='$claim', author='$author', proven=$proven, date='$date', images=$images, content='$content')"
        }
    }

    data class NewsOverview(val title: String,
                            val url_slug: String,
                            val author: String,
                            val thumbnail: String,
                            val link: String,
                            val date: String) {
        override fun toString(): String {
            return "NewsSummary(title='$title', url_slug='$url_slug', author='$author', thumbnail='$thumbnail', link='$link', date='$date')"
        }
    }
}
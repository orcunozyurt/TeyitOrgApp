package com.nerdz.teyitorgapp.model

import java.util.Arrays

object Model {
    data class MainHeadline(
        val id: Long,
        val date: Long,
        val title: String,
        val target_url: String,
        val image_src: String,
        val authors: ArrayList<Author>
    ){

        override fun toString(): String {
            return "MainHeadline(id=$id, date=$date, title='$title', target_url='$target_url', image_src='$image_src')"
        }
    }

    data class Author(
        val id: Long,
        val name: String,
        val create_time: String,
        val details: AuthorDetail

    ) {
        override fun toString(): String {
            return "Author(id=$id, name='$name', create_time='$create_time', details=$details)"
        }
    }

    data class AuthorDetail(
        val summary: String,
        val profile_image: String,
        val visible_role: String,
        val twitter: String,
        val medium: String,
        val web_page: String
    ) {
        override fun toString(): String {
            return "AuthorDetail(summary='$summary', profile_image='$profile_image', visible_role='$visible_role', twitter='$twitter', medium='$medium', web_page='$web_page')"
        }
    }

    data class Post(
        val id: Long,
        val date: Long,
        val title: String,
        val target_url: String,
        val img_src: String,
        val authors: ArrayList<Author>,
        val post_type: String,
        val summary: String,
        val topics: ArrayList<String>) {

        fun getSlug(): String {
            return target_url.removeRange(0,1)
        }

        override fun toString(): String {
            return "Posts(id=$id, date=$date, title='$title', target_url='$target_url', image_src='$img_src', authors=$authors, post_type='$post_type', summary='$summary', topics=$topics)"
        }
    }

    data class NewsSingle(val title: String,
                          val claim: String,
                          val authors: ArrayList<Author>,
                          val correctness: String,
                          val date: Long,
                          val content: ArrayList<String>,
                          val featured_image: String) {

        override fun toString(): String {
            return "NewsSingle(title='$title', claim='$claim', authors='$authors', correctness=$correctness, date='$date', featured_image=$featured_image, content='$content')"
        }
    }

    data class NewsOverview(val main_headline: MainHeadline,
                            val posts: ArrayList<Post>) {
        override fun toString(): String {
            return "NewsOverview(main_headline=$main_headline, posts=$posts)"
        }
    }
}
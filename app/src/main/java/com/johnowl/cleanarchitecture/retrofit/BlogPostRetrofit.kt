package com.johnowl.cleanarchitecture.retrofit

import retrofit2.http.GET

interface BlogPostRetrofit {

    @GET("blogs")
    suspend fun getBlogPosts(): List<BlogPostApiEntity>
}
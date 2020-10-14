package com.johnowl.cleanarchitecture.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BlogPostApiEntity(

    @Expose
    @SerializedName("pk")
    var id: Long,

    @Expose
    @SerializedName("title")
    var title: String,

    @Expose
    @SerializedName("body")
    var body: String,

    @Expose
    @SerializedName("image")
    var image: String,

    @Expose
    @SerializedName("category")
    var category: String
)
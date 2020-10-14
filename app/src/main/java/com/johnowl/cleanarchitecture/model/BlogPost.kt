package com.johnowl.cleanarchitecture.model

data class BlogPost(
    var id: Long,
    var title: String,
    var body: String,
    var image: String,
    var category: String
)
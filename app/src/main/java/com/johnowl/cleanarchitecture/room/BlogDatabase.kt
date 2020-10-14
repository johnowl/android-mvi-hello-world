package com.johnowl.cleanarchitecture.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BlogPostCacheEntity::class], version = 1)
abstract class BlogDatabase: RoomDatabase() {

    abstract fun blogPostDao(): BlogPostDao

    companion object {
        val DATABASE_NAME = "blog_db"
    }

}
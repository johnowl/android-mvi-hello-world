package com.johnowl.cleanarchitecture.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BlogPostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(blogPostCacheEntity: BlogPostCacheEntity): Long

    @Query("SELECT * FROM blogs")
    suspend fun listAll(): List<BlogPostCacheEntity>

}

package com.johnowl.cleanarchitecture.repository

import com.johnowl.cleanarchitecture.model.BlogPost
import com.johnowl.cleanarchitecture.retrofit.BlogPostApiMapper
import com.johnowl.cleanarchitecture.retrofit.BlogPostRetrofit
import com.johnowl.cleanarchitecture.room.BlogPostCacheMapper
import com.johnowl.cleanarchitecture.room.BlogPostDao
import com.johnowl.cleanarchitecture.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BlogPostRepository @Inject constructor(
    private val blogPostDao: BlogPostDao,
    private val blogPostRetrofit: BlogPostRetrofit,
    private val cacheMapper: BlogPostCacheMapper,
    private val blogPostApiMapper: BlogPostApiMapper
) {

    suspend fun getBlogPosts(): Flow<DataState<List<BlogPost>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val networkBlogPosts = blogPostRetrofit.getBlogPosts()
            val posts = blogPostApiMapper.mapFromEntityList(networkBlogPosts)
            for (post in posts) {
                blogPostDao.insert(cacheMapper.mapToEntity(post))
            }
            val cachedPosts = blogPostDao.listAll()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedPosts)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}
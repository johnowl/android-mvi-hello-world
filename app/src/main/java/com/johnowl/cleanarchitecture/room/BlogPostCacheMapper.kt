package com.johnowl.cleanarchitecture.room

import com.johnowl.cleanarchitecture.model.BlogPost
import com.johnowl.cleanarchitecture.util.EntityMapper
import javax.inject.Inject

class BlogPostCacheMapper @Inject constructor(): EntityMapper<BlogPostCacheEntity, BlogPost> {
    override fun mapFromEntity(entity: BlogPostCacheEntity): BlogPost {
        return BlogPost(
            id = entity.id,
            title = entity.title,
            body = entity.body,
            image = entity.image,
            category = entity.category
        )
    }

    override fun mapToEntity(domainModel: BlogPost): BlogPostCacheEntity {
        return BlogPostCacheEntity(
            id = domainModel.id,
            title = domainModel.title,
            body = domainModel.body,
            image = domainModel.image,
            category = domainModel.category
        )
    }

    fun mapFromEntityList(entities: List<BlogPostCacheEntity>): List<BlogPost> {
        return entities.map { mapFromEntity(it) }
    }
}
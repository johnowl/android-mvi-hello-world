package com.johnowl.cleanarchitecture.retrofit

import com.johnowl.cleanarchitecture.model.BlogPost
import com.johnowl.cleanarchitecture.util.EntityMapper
import javax.inject.Inject

class BlogPostApiMapper @Inject constructor() : EntityMapper<BlogPostApiEntity, BlogPost>  {
    override fun mapFromEntity(entity: BlogPostApiEntity): BlogPost {
        return BlogPost(
            id = entity.id,
            title = entity.title,
            body = entity.body,
            image = entity.image,
            category = entity.category
        )
    }

    override fun mapToEntity(domainModel: BlogPost): BlogPostApiEntity {
        return BlogPostApiEntity(
            id = domainModel.id,
            title = domainModel.title,
            body = domainModel.body,
            image = domainModel.image,
            category = domainModel.category
        )
    }

    fun mapFromEntityList(entities: List<BlogPostApiEntity>): List<BlogPost> {
        return entities.map { mapFromEntity(it) }
    }
}
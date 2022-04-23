package com.tlb.testleboncoin.framework.db

import com.tlb.core.domain.Title

class TitleMapper {

    fun toTitle(entity: TitleEntity) = Title(
        entity.id,
        entity.title,
        entity.albumId,
        entity.url,
        entity.thumbnailUrl
    )

    fun toEntity(title: Title) = TitleEntity(
        title.id,
        title.title,
        title.albumId,
        title.url,
        title.thumbnailUrl
    )
}
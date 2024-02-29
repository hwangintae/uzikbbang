package com.khpl.uzikbbang.domain.gallery;

import java.util.List;

import com.khpl.uzikbbang.api.controller.Page;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GalleryRepositoryImpl implements GalleryRepositoryDsl{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Gallery> getList(Page page) {
        // return jpaQueryFactory.selectFrom(gallery)
        //         .limit(page.getSize())
        //         .offset(page.getOffset())
        //         .orderBy(gallery.id.desc())
        //     .fetch();
        return null;
    }
    
}

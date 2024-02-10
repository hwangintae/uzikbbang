package com.khpl.uzikbbang.repository;

import static com.khpl.uzikbbang.entity.QGallery.gallery;

import java.util.List;

import com.khpl.uzikbbang.entity.Gallery;
import com.khpl.uzikbbang.entity.QGallery;
import com.khpl.uzikbbang.request.Page;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GalleryRepositoryImpl implements GalleryRepositoryDsl{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Gallery> getList(Page page) {

        QGallery dd = gallery;
        return jpaQueryFactory.selectFrom(gallery)
                .limit(page.getSize())
                .offset(page.getOffset())
                .orderBy(gallery.id.desc())
            .fetch();
    }
    
}

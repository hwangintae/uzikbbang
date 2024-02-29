package com.khpl.uzikbbang.domain.notice;

import java.util.List;

import com.khpl.uzikbbang.api.controller.Page;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NoticeRepositoryImpl implements NoticeRepositoryDsl {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Notice> getList(Page page) {
        // return jpaQueryFactory.selectFrom(notice)
        //                     .limit(page.getSize())
        //                     .offset(page.getOffset())
        //                     .orderBy(notice.id.desc())
        //                 .fetch();
        return null;
    }
    
}

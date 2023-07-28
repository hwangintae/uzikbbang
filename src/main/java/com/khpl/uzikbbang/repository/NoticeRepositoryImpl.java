package com.khpl.uzikbbang.repository;

import static com.khpl.uzikbbang.domain.QNotice.notice;

import java.util.List;

import com.khpl.uzikbbang.domain.Notice;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.request.Page;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NoticeRepositoryImpl implements NoticeRepositoryDsl {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Notice> getList(Page page) {
        return jpaQueryFactory.selectFrom(notice)
                            .limit(page.getSize())
                            .offset(page.getOffset())
                            .orderBy(notice.id.desc())
                        .fetch();
    }
    
}

package com.khpl.uzikbbang.repository;

import static com.khpl.uzikbbang.entity.QUzikUser.uzikUser;

import java.util.List;

import com.khpl.uzikbbang.entity.UzikUser;
import com.khpl.uzikbbang.request.Page;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryDsl {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<UzikUser> getList(Page page) {
        return jpaQueryFactory.selectFrom(uzikUser)
            .limit(page.getSize())
            .offset(page.getOffset())
            .orderBy(uzikUser.id.desc())
        .fetch();
    }
    
}

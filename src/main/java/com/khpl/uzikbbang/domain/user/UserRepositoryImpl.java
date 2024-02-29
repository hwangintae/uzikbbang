package com.khpl.uzikbbang.domain.user;

import java.util.List;

import com.khpl.uzikbbang.api.controller.Page;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryDsl {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<UzikUser> getList(Page page) {
        QUzikUser uzikUser = QUzikUser.uzikUser;
        
        return jpaQueryFactory.selectFrom(uzikUser)
            .limit(page.getSize())
            .offset(page.getOffset())
            .orderBy(uzikUser.id.desc())
        .fetch();
    }
    
}

package com.khpl.uzikbbang.domain.user;

import static com.khpl.uzikbbang.domain.user.QUser.user;

import java.util.List;

import com.khpl.uzikbbang.api.controller.Page;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryDsl {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<User> getList(Page page) {
        return jpaQueryFactory.selectFrom(user)
            .limit(page.getSize())
            .offset(page.getOffset())
            .orderBy(user.id.desc())
        .fetch();
    }
    
}

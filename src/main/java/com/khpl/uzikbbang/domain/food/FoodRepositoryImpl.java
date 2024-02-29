package com.khpl.uzikbbang.domain.food;

import static com.khpl.uzikbbang.domain.product.QFood.food;

import java.util.List;

import com.khpl.uzikbbang.api.controller.Page;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FoodRepositoryImpl implements FoodRepositoryDsl {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Food> getList(Page page) {
        return jpaQueryFactory.selectFrom(food)
                            .limit(page.getSize())
                            .offset(page.getOffset())
                            .orderBy(food.id.desc())
                        .fetch();
    }
}

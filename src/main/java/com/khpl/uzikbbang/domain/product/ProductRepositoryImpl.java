package com.khpl.uzikbbang.domain.product;

import static com.khpl.uzikbbang.domain.product.QProduct.product;

import java.util.List;

import com.khpl.uzikbbang.api.controller.Page;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryDsl {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Product> getList(Page page) {
        return jpaQueryFactory.selectFrom(product)
                            .limit(page.getSize())
                            .offset(page.getOffset())
                            .orderBy(product.id.desc())
                        .fetch();
    }
}

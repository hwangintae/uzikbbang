package com.khpl.uzikbbang.repository;

import static com.khpl.uzikbbang.domain.QProduct.product;

import java.util.List;

import com.khpl.uzikbbang.domain.Product;
import com.khpl.uzikbbang.request.Page;
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

package com.khpl.uzikbbang.repository;

import static com.khpl.uzikbbang.dto.QUzikOrder.uzikOrder;
import static com.khpl.uzikbbang.dto.QUzikOrderProduct.uzikOrderProduct;

import java.util.List;

import com.khpl.uzikbbang.dto.UzikOrderProduct;
import com.khpl.uzikbbang.request.Page;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderProductRepositoryImpl implements OrderProductRepositoryDsl {
    
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<UzikOrderProduct> getList(Page page, Long userId) {
        return jpaQueryFactory.selectFrom(uzikOrderProduct)
            .join(uzikOrderProduct.order, uzikOrder).fetchJoin()
            .where(uzikOrder.user.id.eq(userId))
        .fetch();
    }
    
}

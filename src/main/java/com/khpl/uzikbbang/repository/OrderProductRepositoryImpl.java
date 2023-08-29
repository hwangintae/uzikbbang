package com.khpl.uzikbbang.repository;

import static com.khpl.uzikbbang.domain.QUzikOrder.uzikOrder;
import static com.khpl.uzikbbang.domain.QUzikOrderProduct.uzikOrderProduct;

import java.util.List;

import com.khpl.uzikbbang.domain.UzikOrderProduct;
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

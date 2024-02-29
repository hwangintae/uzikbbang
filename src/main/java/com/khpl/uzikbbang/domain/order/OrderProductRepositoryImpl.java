package com.khpl.uzikbbang.domain.order;

import static com.khpl.uzikbbang.api.controller.order.QUzikOrder.uzikOrder;
import static com.khpl.uzikbbang.api.controller.order.QUzikOrderProduct.uzikOrderProduct;

import java.util.List;

import com.khpl.uzikbbang.api.controller.Page;
import com.khpl.uzikbbang.api.controller.order.UzikOrderProduct;
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

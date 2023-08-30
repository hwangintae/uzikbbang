package com.khpl.uzikbbang.repository;

import static com.khpl.uzikbbang.domain.QCartItem.cartItem;

import java.util.Optional;

import com.khpl.uzikbbang.domain.CartItem;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CartItemRepositoryImpl implements CartItemRepositoryDsl {

    private final JPAQueryFactory jpaQueryFactory;

    // TODO queryDsl 로 useAt True 일 경우에만 조회 하고 싶은데
    // fetchOne 했을 경우 NPE 발생함..
    // [v] jpaQueryFactory에 final이 없어서 생성자 주입이 안되고 있었음.
    @Override
    public Optional<CartItem> getCartItem(Long userId, Long productId) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(cartItem)
                        .where(cartItem.user.id.eq(userId), cartItem.product.id.eq(productId), cartItem.useAt.eq(true))
                        .fetchOne());
    }

}

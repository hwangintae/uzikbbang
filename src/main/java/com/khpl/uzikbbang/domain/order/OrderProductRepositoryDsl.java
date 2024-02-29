package com.khpl.uzikbbang.domain.order;

import java.util.List;

import com.khpl.uzikbbang.api.controller.Page;
import com.khpl.uzikbbang.api.controller.order.UzikOrderProduct;

public interface OrderProductRepositoryDsl {
    List<UzikOrderProduct> getList(Page page, Long userId);
}

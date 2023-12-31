package com.khpl.uzikbbang.repository;

import java.util.List;

import com.khpl.uzikbbang.domain.UzikOrderProduct;
import com.khpl.uzikbbang.request.Page;

public interface OrderProductRepositoryDsl {
    List<UzikOrderProduct> getList(Page page, Long userId);
}

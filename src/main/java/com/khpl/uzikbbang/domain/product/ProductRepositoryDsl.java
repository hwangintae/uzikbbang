package com.khpl.uzikbbang.domain.product;

import java.util.List;

import com.khpl.uzikbbang.api.controller.Page;

public interface ProductRepositoryDsl {
    List<Product> getList(Page page);
}

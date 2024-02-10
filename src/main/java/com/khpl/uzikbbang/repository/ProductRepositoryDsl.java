package com.khpl.uzikbbang.repository;

import java.util.List;

import com.khpl.uzikbbang.entity.Product;
import com.khpl.uzikbbang.request.Page;

public interface ProductRepositoryDsl {
    List<Product> getList(Page page);
}

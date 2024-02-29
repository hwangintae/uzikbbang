package com.khpl.uzikbbang.domain.food;

import java.util.List;

import com.khpl.uzikbbang.api.controller.Page;

public interface FoodRepositoryDsl {
    List<Food> getList(Page page);
}

package com.khpl.uzikbbang.domain.user;

import java.util.List;

import com.khpl.uzikbbang.api.controller.Page;

public interface UserRepositoryDsl {
    List<UzikUser> getList(Page page);
}

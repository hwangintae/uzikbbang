package com.khpl.uzikbbang.repository;

import java.util.List;

import com.khpl.uzikbbang.domain.UzikUser;
import com.khpl.uzikbbang.request.Page;

public interface UserRepositoryDsl {
    List<UzikUser> getList(Page page);
}

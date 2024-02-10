package com.khpl.uzikbbang.repository;

import java.util.List;

import com.khpl.uzikbbang.entity.Notice;
import com.khpl.uzikbbang.request.Page;

public interface NoticeRepositoryDsl {
    List<Notice> getList(Page page);
}

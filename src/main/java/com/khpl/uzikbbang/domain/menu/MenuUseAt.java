package com.khpl.uzikbbang.domain.menu;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MenuUseAt {
    Y(true),
    N(false)
    ;

    private final Boolean useAt;
}

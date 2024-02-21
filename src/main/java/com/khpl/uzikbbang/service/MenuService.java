package com.khpl.uzikbbang.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.khpl.uzikbbang.domain.menu.MenuRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {
    private final MenuRepository menuRepository;

    @Transactional
    public Long save(MenuServiceEdit menuServiceEdit) {
        return menuRepository.save(menuServiceEdit.toEntity()).getId();
    }
    
}

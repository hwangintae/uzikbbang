package com.khpl.uzikbbang.service;

import org.springframework.stereotype.Service;

import com.khpl.uzikbbang.domain.menu.MenuRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    public Long save(MenuServiceEdit menuServiceEdit) {
        
        return menuRepository.save(menuServiceEdit.toEntity()).getId();
    }
    
}

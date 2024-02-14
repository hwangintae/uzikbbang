package com.khpl.uzikbbang.service;

import org.springframework.stereotype.Service;

import com.khpl.uzikbbang.dto.MenuEdit;
import com.khpl.uzikbbang.entity.Menu;
import com.khpl.uzikbbang.repository.MenuRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    public Long save(MenuEdit menuEdit) {

        Menu menu = Menu.builder()
                .menuEdit(menuEdit)
                .build();
        
        return menuRepository.save(menu).getId();
    }
    
}

package com.khpl.uzikbbang.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khpl.uzikbbang.request.Page;
import com.khpl.uzikbbang.request.ProductCreate;
import com.khpl.uzikbbang.request.ProductEdit;
import com.khpl.uzikbbang.response.ProductResponse;
import com.khpl.uzikbbang.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    
    @PostMapping(value="")
    public void post(@RequestBody ProductCreate request) {
        productService.add(request);
    }

    @GetMapping(value="/list")
    public List<ProductResponse> getNoticeList(@ModelAttribute Page page) {
        return productService.getList(page);
    }

    @PatchMapping(value = "/{productId}")
    public void edit(@PathVariable Long productId, @RequestBody ProductEdit productEdit) {
        productService.edit(productId, productEdit);
    }

    
}

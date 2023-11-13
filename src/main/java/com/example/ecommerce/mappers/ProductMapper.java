package com.example.ecommerce.mappers;

import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.model.Product;

public class ProductMapper {

    public static ProductDto maptoProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setProductName(product.getProductName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        return productDto;
    }

}

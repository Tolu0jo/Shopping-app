package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;

import java.util.List;

public interface ProductService {
Product createProduct(ProductDto productDto);

List<ProductDto> getAllProducts();

ProductDto updateProduct(String productId, ProductDto productDto);
ProductDto getProduct(String productId);

void deleteProduct(String productId);
}

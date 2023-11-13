package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements  ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Product createProduct(ProductDto productDto) {

        Optional<Category> existingCategory= categoryRepository.findById(productDto.getCategoryId());
        if(existingCategory.isEmpty()) throw new HttpClientErrorException(HttpStatusCode.valueOf(404),"Category not found");
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());
        product.setCategory(existingCategory.get());
        product.setImageUrl(productDto.getImageUrl());
        product.setPrice(productDto.getPrice());
       return productRepository.save(product);
    }

    public ProductDto maptoProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setProductName(product.getProductName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        return productDto;
    }

    public List<ProductDto> getAllProducts(){
        List<Product> products = productRepository.findAll();
       return products.stream().map(this::maptoProductDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto updateProduct(String productId,ProductDto productDto){
        Optional<Product> product =productRepository.findById(productId);
        if(product.isEmpty()) throw new HttpClientErrorException(HttpStatusCode.valueOf(404),"Product not found");
        Product updatedProduct = product.get();
        updatedProduct.setProductName(productDto.getProductName());
        updatedProduct.setDescription(productDto.getDescription());
        updatedProduct.setImageUrl(productDto.getImageUrl());
        updatedProduct.setPrice(productDto.getPrice());
        productDto.setCategoryId(productDto.getCategoryId());
        return maptoProductDto(productRepository.save(updatedProduct));

    }

    @Override
    public ProductDto getProduct(String productId)  {
        Optional<Product> product =productRepository.findById(productId);
        if(product.isEmpty()) throw new HttpClientErrorException(HttpStatusCode.valueOf(404),"Product not found");
        return maptoProductDto(product.get());
    }

    @Override
    public void deleteProduct(String productId){
        Optional<Product> product =productRepository.findById(productId);
        if(product.isEmpty()) throw new HttpClientErrorException(HttpStatusCode.valueOf(404),"Product not found");
        productRepository.deleteById(productId);
    }


}



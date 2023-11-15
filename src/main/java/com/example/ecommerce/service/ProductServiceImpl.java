package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.mappers.ProductMapper;
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
    public Product createProduct(String categoryId,ProductDto productDto) {

        Optional<Category> existingCategory= categoryRepository.findById(categoryId);
        if(existingCategory.isEmpty()) throw new HttpClientErrorException(HttpStatusCode.valueOf(404),"Category not found");
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());
        product.setCategory(existingCategory.get());
        product.setImageUrl(productDto.getImageUrl());
        product.setPrice(productDto.getPrice());
       return productRepository.save(product);
    }



    public List<ProductDto> getAllProducts(){
        List<Product> products = productRepository.findAll();
       return products.stream().map(ProductMapper::maptoProductDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto updateProduct(String productId,ProductDto productDto){
        Optional<Product> product =productRepository.findById(productId);
        if(product.isEmpty()) throw new HttpClientErrorException(HttpStatusCode.valueOf(404),"Product not found");
        Optional<Category> category =categoryRepository.findById(productDto.getCategoryId());
        if(category.isEmpty()) throw new HttpClientErrorException(HttpStatusCode.valueOf(404),"Category not found");
        Product updatedProduct = product.get();
        updatedProduct.setProductName(productDto.getProductName());
        updatedProduct.setDescription(productDto.getDescription());
        updatedProduct.setImageUrl(productDto.getImageUrl());
        updatedProduct.setPrice(productDto.getPrice());
        updatedProduct.setCategory(category.get());
        return ProductMapper.maptoProductDto(productRepository.save(updatedProduct));

    }

    @Override
    public ProductDto getProduct(String productId)  {
        Optional<Product> product =productRepository.findById(productId);
        if(product.isEmpty()) throw new HttpClientErrorException(HttpStatusCode.valueOf(404),"Product not found");
        return ProductMapper.maptoProductDto(product.get());
    }

    @Override
    public Product getProductDetails(String productId) {
        Optional<Product> product =productRepository.findById(productId);
        if(product.isEmpty()) throw new HttpClientErrorException(HttpStatusCode.valueOf(404),"Product not found");
        return product.get();
    }

    @Override
    public void deleteProduct(String productId){
        Optional<Product> product =productRepository.findById(productId);
        if(product.isEmpty()) throw new HttpClientErrorException(HttpStatusCode.valueOf(404),"Product not found");
        productRepository.deleteById(productId);
    }


}



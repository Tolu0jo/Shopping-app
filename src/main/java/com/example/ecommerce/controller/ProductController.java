package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ProductDto;

import com.example.ecommerce.model.Product;

import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/products")
public class ProductController {

   private final ProductService productService;




    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;


    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
    @PostMapping("/add/{categoryId}")
    public ResponseEntity<Product> createProduct(@PathVariable("categoryId") String categoryId,
            @RequestBody ProductDto productDto){

      Product product = productService.createProduct(categoryId,productDto);
      return new ResponseEntity<>(product,HttpStatus.CREATED);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto,
                                                    @PathVariable String productId) {
          ProductDto product =  productService.updateProduct(productId,productDto);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable String productId){
        productService.deleteProduct(productId);
        return  ResponseEntity.ok(null);
    }
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable String productId)  {
        ProductDto productDto =productService.getProduct(productId);
        return  ResponseEntity.ok(productDto);
    }
}

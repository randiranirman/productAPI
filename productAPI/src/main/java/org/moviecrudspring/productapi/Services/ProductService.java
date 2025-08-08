package org.moviecrudspring.productapi.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.moviecrudspring.productapi.Dtos.ProductRequest;
import org.moviecrudspring.productapi.Dtos.ProductResponse;
import org.moviecrudspring.productapi.Models.Product;
import org.moviecrudspring.productapi.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;


    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product =Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();


        productRepository.save(product);

        log.info("product is created successfully ");


        return new ProductResponse(product.getId(),product.getName(), product.getDescription(), product.getPrice());



    }

    public List<ProductResponse> getAllProducts() {
        var products = productRepository.findAll().stream().map(
                product -> new ProductResponse(product.getId(),product.getName(),product.getDescription(),product.getPrice())).toList();

        log.info("products are fetched successfully ");
        return products;










    }



}

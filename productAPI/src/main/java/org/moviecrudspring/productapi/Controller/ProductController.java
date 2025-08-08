package org.moviecrudspring.productapi.Controller;


import lombok.RequiredArgsConstructor;
import org.moviecrudspring.productapi.Dtos.ProductRequest;
import org.moviecrudspring.productapi.Dtos.ProductResponse;
import org.moviecrudspring.productapi.Models.Product;
import org.moviecrudspring.productapi.Services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct (@RequestBody ProductRequest productRequest) {
        var product = productService.createProduct(productRequest);

        return product;

    }


    @GetMapping
    @ResponseStatus( HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        var products = productService.getAllProducts();
        return products;



    }


}

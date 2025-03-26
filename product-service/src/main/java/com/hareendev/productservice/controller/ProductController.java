package com.hareendev.productservice.controller;

import com.hareendev.productservice.dto.ProductDTO;
import com.hareendev.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/getproducts")
    public List<ProductDTO> getProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/addproduct")
    public ProductDTO saveProduct(@RequestBody ProductDTO productDTO) {
        return productService.saveProduct(productDTO);
    }

    @PutMapping("/updateproduct")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO) {
        return productService.updateProduct(productDTO);
    }

    @DeleteMapping("/deleteproduct/{productId}")
    public String deleteProduct(@PathVariable Integer productId) {
        return productService.deleteProduct(productId);
    }

//    @GetMapping("/product/{productId}")
//    public ProductDTO getProductById(@PathVariable Integer productId) {
//        return productService.getProductById(productId);
//    }
}

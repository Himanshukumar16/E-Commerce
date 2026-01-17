package E_commerce.practice2.controller;

import E_commerce.practice2.model.Product;
import E_commerce.practice2.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/addProducts")
    public String addProduct(@RequestBody List<Product> productDetails) {
        return productService.addProduct(productDetails);
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestBody Product productDetails) {
        return productService.addProduct(productDetails);
    }
}
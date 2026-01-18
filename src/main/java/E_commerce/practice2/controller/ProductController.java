package E_commerce.practice2.controller;

import E_commerce.practice2.model.Product;
import E_commerce.practice2.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.FOUND);
    }

    @PostMapping("/addProducts")
    public ResponseEntity<String> addProduct(@RequestBody List<Product> productDetails) {
        return new ResponseEntity<>(productService.addProduct(productDetails), HttpStatus.CREATED);
    }

    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(@RequestBody Product productDetails) {
        return new ResponseEntity<>(productService.addProduct(productDetails), HttpStatus.CREATED);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.FOUND);
    }
}
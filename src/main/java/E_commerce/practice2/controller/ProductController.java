package E_commerce.practice2.controller;

import E_commerce.practice2.model.Product;
import E_commerce.practice2.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @PostMapping("/addProducts")
    public ResponseEntity<String> addProduct(@RequestBody List<Product> productDetails) {
        return new ResponseEntity<>(productService.addProduct(productDetails), HttpStatus.CREATED);
    }

    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(@RequestBody Product productDetails) {
        return new ResponseEntity<>(productService.addProduct(productDetails), HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getProductImage(@PathVariable Integer productId) {
        Product product = productService.getProductById(productId);
        if (product != null && product.getImageData() != null) {
            return ResponseEntity.ok()
                    .header("Content-Type", product.getImageType())
                    .header("Content-Disposition", "attachment; filename=\"" + product.getImageName() + "\"")
                    .body(product.getImageData());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/product")
    public ResponseEntity<?> createProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile) {
        Product createdProduct = null;
        try {
            createdProduct = productService.addProduct(product, imageFile);
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Integer id, @RequestPart Product product, @RequestPart MultipartFile imageFile) {
        Product updateProduct = null;
        try {
            updateProduct = productService.UpdateProduct(id,product,imageFile);
            return new ResponseEntity<>("Updated !", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
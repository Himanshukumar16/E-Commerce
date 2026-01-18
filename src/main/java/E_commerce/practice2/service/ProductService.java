package E_commerce.practice2.service;

import E_commerce.practice2.model.Product;
import E_commerce.practice2.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public String addProduct(List<Product> productDetails) {
        productRepository.saveAll(productDetails);
        return "Products added successfully";
    }

    public String addProduct(Product productDetails) {
        productRepository.save(productDetails);
        return "Product added successfully";
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }
}

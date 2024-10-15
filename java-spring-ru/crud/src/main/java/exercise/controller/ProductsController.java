package exercise.controller;

import java.util.List;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.mapper.ProductMapper;
import exercise.model.Product;
import exercise.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.ProductRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    // BEGIN
    @GetMapping
    public List<ProductDTO> getProducts() {
        var products = productRepository.findAll();
        return products.stream().map(productMapper::map).toList();
    }

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable Long id) {
        var currentProduct = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found!"));
        return productMapper.map(currentProduct);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createProduct(@Valid @RequestBody ProductCreateDTO productData) {
        var product = productMapper.map(productData);
        var newProduct = productRepository.save(product);
        return productMapper.map(newProduct);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @Valid @RequestBody ProductUpdateDTO productData) {
        var currentProduct = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found!"));
        productMapper.update(productData, currentProduct);
        var category = categoryRepository.findById(productData.getCategoryId().get()).get();
        currentProduct.setCategory(category);
        productRepository.save(currentProduct);
        return productMapper.map(currentProduct);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
    // END
}
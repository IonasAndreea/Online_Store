package com.example.demo.Services;

import com.example.demo.Entity.Products;
import com.example.demo.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Products> getAllProducts(){
        return productRepository.findAll();
    }

    public Optional<Products> getProductById(long id){
        return productRepository.findById(id);
    }

    public Products createProduct(Products product){
        return productRepository.save(product);
    }

    public void deleteProduct(long id){
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
        }
    }

    public Products updateProduct(long id, Products product) {
        Optional<Products> optionalExistingProduct = productRepository.findById(id);
        if (optionalExistingProduct.isPresent()) {
            Products existingProduct = optionalExistingProduct.get();
            existingProduct.setNameProduct(product.getNameProduct());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setStock(product.getStock());
            return productRepository.save(existingProduct);
        } else {
            return null;
        }
    }

}

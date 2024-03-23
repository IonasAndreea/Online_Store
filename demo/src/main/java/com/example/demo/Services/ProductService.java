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

    /**
     *
     * @return a list of all products.
     */
    public List<Products> getAllProducts(){
        return productRepository.findAll();
    }

    /**
     *
     * @param id is the id of the product to retrieve
     * @return Representing the retrieved product
     */
    public Optional<Products> getProductById(long id){
        return productRepository.findById(id);
    }

    /**
     *
     * @param product is the product that we want to create
     * @return the actual created product
     */
    public Products createProduct(Products product){
        return productRepository.save(product);
    }

    /**
     *
     * @param id the ID of the product to delete
     */
    public void deleteProduct(long id){
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
        }
    }

    /**
     *
     * @param id the id of the product we need to update
     * @param product representing the updated product data
     * @return Representing the updated product
     */
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

package com.example.demo.services;

import com.example.demo.entity.Products;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * The service class responsible for managing products within the system.
 * It provides methods for retrieving, creating, updating, and deleting products.
 * This class acts as a bridge between the controller layer and the data access layer,
 * ensuring proper handling and manipulation of product data.
 */
@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Retrieves a list of all products stored in the system.
     *
     * @return A list containing all products available in the system.
     */
    public List<Products> getAllProducts(){
        return productRepository.findAll();
    }

    /**
     * Retrieves a specific product based on its unique identifier.
     *
     * @param id The unique identifier of the product to retrieve.
     * @return An Optional containing the retrieved product, if found; otherwise, an empty Optional.
     */
    public Optional<Products> getProductById(long id){
        return productRepository.findById(id);
    }

    /**
     * Creates a new product entity within the system.
     *
     * @param product The product object to be created.
     * @return The newly created product entity after successful persistence.
     */
    public Products createProduct(Products product){
        return productRepository.save(product);
    }

    /**
     * Deletes a product from the system based on its unique identifier.
     *
     * @param id The unique identifier of the product to delete.
     */
    public void deleteProduct(long id){
        //if(productRepository.existsById(id)){
            productRepository.deleteById(id);
        //}
    }

    /**
     * Updates an existing product entity with new information provided.
     *
     * @param id The unique identifier of the product to update.
     * @param product The updated product object containing new attribute values.
     * @return The updated product entity after successful persistence,
     * or null if no product with the provided ID exists.
     */
    public Products updateProduct(long id, Products product) {
        Optional<Products> optionalExistingProduct = productRepository.findById(id);
        if (optionalExistingProduct.isPresent()) {
            Products existingProduct = optionalExistingProduct.get();
            existingProduct.setNameProduct(product.getNameProduct());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setStock(product.getStock());
            //existingProduct.setPrice(existingProduct.getNameProduct(), existingProduct.getPrice());
            return productRepository.save(existingProduct);
        } else {
            return null;
        }
    }

}

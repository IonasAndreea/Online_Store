package com.example.demo.controllers;

import com.example.demo.entity.Products;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The controller class responsible for managing HTTP requests related to products.
 * It provides endpoints for retrieving, creating, updating, and deleting products.
 * This class acts as the interface between the client-side applications and the product management system.
 */
@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * Retrieves a list of all products stored in the system.
     *
     * @return A ResponseEntity containing a list of all products and an HTTP status code 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<Products>> getAllProducts(){
        List<Products> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }


    /**
     * Retrieves a specific product based on its unique identifier.
     *
     * @param id The unique identifier of the product to retrieve.
     * @return A ResponseEntity containing the retrieved product and an HTTP status code 200 (OK) if found,
     * or an HTTP status code 404 (Not Found) if the product is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable Long id){
        Optional <Products> optionalProduct = productService.getProductById(id);
        if(optionalProduct.isPresent()){
            return ResponseEntity.ok(optionalProduct.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Creates a new product entity within the system.
     *
     * @param product The product object to be created.
     * @return A ResponseEntity containing the created product and an HTTP status code 201 (Created).
     */
    @PostMapping("/insertProduct")
    public ResponseEntity<Products> createProduct(@RequestBody Products product){
        return  ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(product));

    }


    /**
     * Updates an existing product entity with new information provided.
     *
     * @param id The unique identifier of the product to update.
     * @param product The updated product object containing new attribute values.
     * @return A ResponseEntity containing the updated product and an HTTP status code 200 (OK) if successful,
     * or an HTTP status code 404 (Not Found) if the product with the given ID is not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Products> updateProduct(@PathVariable Long id, @RequestBody Products product){
        Products updateProduct = productService.updateProduct(id, product);
        if(updateProduct != null){
            updateProduct.setPrice(updateProduct.getNameProduct(),updateProduct.getPrice());
            return ResponseEntity.ok(updateProduct);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Deletes a product from the system based on its unique identifier.
     *
     * @param id The unique identifier of the product to delete.
     * @return A ResponseEntity with an HTTP status code 204 (No Content) indicating successful deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }


}

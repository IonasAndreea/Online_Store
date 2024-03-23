package com.example.demo.Controllers;

import com.example.demo.Entity.Products;
import com.example.demo.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     *
     * @return the list with all the products
     */
    @GetMapping
    public ResponseEntity<List<Products>> getAllProducts(){
        List<Products> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable Long id){
        Optional <Products> optionalProduct = productService.getProductById(id);
        if(optionalProduct.isPresent()){
            return ResponseEntity.ok(optionalProduct.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/insertProduct")
    public ResponseEntity<Products> createProduct(@RequestBody Products product){
        return  ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(product));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Products> updateProduct(@PathVariable Long id, @RequestBody Products product){
        Products updateProduct = productService.updateProduct(id, product);
        if(updateProduct != null){
            return ResponseEntity.ok(updateProduct);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }


}

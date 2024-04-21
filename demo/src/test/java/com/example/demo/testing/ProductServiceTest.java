package com.example.demo.testing;

import com.example.demo.entity.Clients;
import com.example.demo.entity.Products;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class ProductServiceTest {
    private ProductService productService;
    @Mock
    private ProductRepository productRepositoryMock;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.productService = new ProductService(productRepositoryMock);
    }

    @Test
    public void TestCreate(){
        //this.productService = new ProductService(productRepositoryMock);
        Products product = new Products(10, "Ciocolata", "Milka", 12.99, 9);
        this.productService.createProduct(product);
        Mockito.verify(this.productRepositoryMock).save(product);
        //when(this.productService.createProduct(product)).thenReturn();

    }

    @Test
    public void TestGetAll(){
        Products product1 = new Products(10, "Ciocolata", "Milka", 12.99, 9);
        Products product2 = new Products(80, "Lapte", "Zuzu", 10.99, 25);
        List<Products> productsList = List.of(product1, product2);
        when(this.productRepositoryMock.findAll()).thenReturn(productsList);
        List<Products> res = this.productService.getAllProducts();
        Mockito.verify(this.productRepositoryMock).findAll();
        assertEquals(productsList, res);

    }

    @Test
    public void testGetById() {
        long productId = 10;
        Products product = new Products(productId, "Ciocolata", "Milka", 12.99, 9);
        when(this.productRepositoryMock.findById(productId)).thenReturn(Optional.of(product));
        Optional<Products> res = this.productService.getProductById(productId);
        Mockito.verify(this.productRepositoryMock).findById(productId);
        assertTrue(res.isPresent());
        assertEquals(product, res.get());
    }

    @Test
    public void testDelete() {
        long productId = 10;
        doNothing().when(this.productRepositoryMock).deleteById(productId);
        this.productService.deleteProduct(productId);
        Mockito.verify(this.productRepositoryMock).deleteById(productId);
    }

    @Test
    public void testUpdateProduct_ExistingProduct() {

        long productId = 1;
        Products existingProduct = new Products(productId, "ProductA","DescriptionA", 100.0, 21);
        Products updatedProduct = new Products(productId, "ProductB","DescriptionA", 19.0, 30);
        when(this.productRepositoryMock.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(this.productRepositoryMock.save(Mockito.any(Products.class))).thenReturn(updatedProduct);

        Products result = this.productService.updateProduct(productId, updatedProduct);

        Mockito.verify(this.productRepositoryMock).findById(productId);
        Mockito.verify(this.productRepositoryMock).save(existingProduct);
        assertNotNull(result);
        assertEquals(updatedProduct.getIdProduct(), result.getIdProduct());
        assertEquals(updatedProduct.getNameProduct(), result.getNameProduct());
        assertEquals(updatedProduct.getPrice(), result.getPrice(), 0.01);
        assertEquals(updatedProduct.getDescription(), result.getDescription());
    }

    @Test
    public void testUpdateProduct_NonExistingProduct() {
        long nonExistingProductId = 999;
        Products updatedProduct = new Products(nonExistingProductId, "NewProduct", "New Description", 200.0, 32);
        when(this.productRepositoryMock.findById(nonExistingProductId)).thenReturn(Optional.empty());

        Products result = this.productService.updateProduct(nonExistingProductId, updatedProduct);

        Mockito.verify(this.productRepositoryMock).findById(nonExistingProductId);
        assertNull(result);
    }
}



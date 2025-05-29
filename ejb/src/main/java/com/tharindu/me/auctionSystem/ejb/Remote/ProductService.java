package com.tharindu.me.auctionSystem.ejb.Remote;

import com.tharindu.me.auctionSystem.DTO.ProductDTO;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface ProductService {
    void addProduct(ProductDTO productDTO);
    ProductDTO getProductById(Long id);
    List<ProductDTO> getAllProducts();
    void updateProduct(ProductDTO productDTO);
    void deleteProduct(Long id);
}
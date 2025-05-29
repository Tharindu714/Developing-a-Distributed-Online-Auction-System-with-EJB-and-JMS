package com.tharindu.me.auctionSystem.ejb.Bean;

import com.tharindu.me.auctionSystem.DTO.ProductDTO;
import com.tharindu.me.auctionSystem.Entity.Product;
import com.tharindu.me.auctionSystem.ejb.Remote.ProductService;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ProductServiceBean implements ProductService {

    @PersistenceContext(unitName = "AuctionUnit")
    private EntityManager em;

    @Override
    public void addProduct(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setStartingPrice(dto.getStartingPrice());
        em.persist(product);
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = em.find(Product.class, id);
        if (product == null) return null;

        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setStartingPrice(product.getStartingPrice());
        return dto;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return em.createQuery("SELECT p FROM Product p", Product.class)
                .getResultList()
                .stream()
                .map(p -> {
                    ProductDTO dto = new ProductDTO();
                    dto.setId(p.getId());
                    dto.setName(p.getName());
                    dto.setDescription(p.getDescription());
                    dto.setStartingPrice(p.getStartingPrice());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void updateProduct(ProductDTO dto) {
        Product product = em.find(Product.class, dto.getId());
        if (product != null) {
            product.setName(dto.getName());
            product.setDescription(dto.getDescription());
            product.setStartingPrice(dto.getStartingPrice());
            em.merge(product);
        }
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = em.find(Product.class, id);
        if (product != null) {
            em.remove(product);
        }
    }
}

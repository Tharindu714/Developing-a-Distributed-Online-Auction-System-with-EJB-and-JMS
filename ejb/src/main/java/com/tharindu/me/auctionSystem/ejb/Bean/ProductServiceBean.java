package com.tharindu.me.auctionSystem.ejb.Bean;

import com.tharindu.me.auctionSystem.DTO.ProductDTO;
import com.tharindu.me.auctionSystem.Entity.Product;
import com.tharindu.me.auctionSystem.ejb.Remote.ProductService;
import jakarta.ejb.Stateless;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Stateless
public class ProductServiceBean implements ProductService {

    // Thread-safe list to hold products in memory
    private static final List<ProductDTO> PRODUCTS = new CopyOnWriteArrayList<>();
    private static final AtomicLong ID_GENERATOR = new AtomicLong(1);

    @Override
    public void addProduct(ProductDTO dto) {
        // assign an ID and store
        dto.setId(ID_GENERATOR.getAndIncrement());
        PRODUCTS.add(dto);
    }

    @Override
    public ProductDTO getProductById(Long id) {
        return PRODUCTS.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        // return an unmodifiable snapshot
//        return PRODUCTS.stream()
//                .map(p -> new ProductDTO(p.getId(), p.getName(), p.getDescription(), p.getStartingPrice()))
//                .collect(Collectors.toList());
        return List.of(
                new ProductDTO(1L, "PlayStation 5 – Disc Edition", "Experience lightning-fast loading with ultra-high speed SSD and deeper immersion with haptic feedback and adaptive triggers.", 198000.00),
                new ProductDTO(2L, "Xbox Series X", "Microsoft's fastest and most powerful console with 4K gaming and 1TB SSD for seamless experience.", 185000.00),
                new ProductDTO(3L, "Nintendo Switch OLED", "Hybrid console with a vibrant 7-inch OLED screen, improved audio, and 64GB internal storage.", 120000.00),
                new ProductDTO(4L, "PlayStation 5 DualSense Wireless Controller", "Immersive haptic feedback, adaptive triggers, and built-in microphone for next-gen gaming.", 18500.00),
                new ProductDTO(5L, "Xbox Elite Wireless Controller Series 2", "Pro-level customization with interchangeable thumbsticks and hair trigger locks.", 34000.00),
                new ProductDTO(6L, "Meta Quest 3 VR Headset – 128GB", "Mixed reality headset with next-gen graphics and immersive VR gameplay.", 175000.00),
                new ProductDTO(7L, "PlayStation VR2", "Next-gen virtual reality system for PS5 with 4K HDR and eye-tracking support.", 210000.00),
                new ProductDTO(8L, "Logitech G923 Racing Wheel", "TrueForce feedback and dual-clutch system for realistic racing simulation.", 95000.00),
                new ProductDTO(9L, "ASUS ROG Ally – Gaming Handheld", "Windows-based handheld gaming device with AMD Ryzen Z1 Extreme chip.", 185000.00)

        );
    }

    @Override
    public void updateProduct(ProductDTO dto) {
        Optional<ProductDTO> existing = PRODUCTS.stream()
                .filter(p -> p.getId().equals(dto.getId()))
                .findFirst();
        existing.ifPresent(p -> {
            p.setName(dto.getName());
            p.setDescription(dto.getDescription());
            p.setStartingPrice(dto.getStartingPrice());
        });
    }

    @Override
    public void deleteProduct(Long id) {
        PRODUCTS.removeIf(p -> p.getId().equals(id));
    }
}
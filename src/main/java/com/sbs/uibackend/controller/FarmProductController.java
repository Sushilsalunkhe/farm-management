package com.sbs.uibackend.controller;

import com.sbs.uibackend.dto.FarmProductDTO;
import com.sbs.uibackend.entity.FarmProduct;
import com.sbs.uibackend.service.FarmProductServic;
import com.sbs.uibackend.service.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class FarmProductController {

    @Autowired
    private FarmProductServic productService;

    @Autowired
    private ImageStorageService imageStorageService;
    /* ================= ADD PRODUCT ================= */


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public FarmProduct addProduct(
            @RequestPart("product") FarmProductDTO dto,
            @RequestPart("image") MultipartFile image
    ) throws IOException {

        String imageUrl = imageStorageService.saveImage(image);

        FarmProduct product = new FarmProduct();
        product.setProductName(dto.getProductName());
        product.setCategory(dto.getCategory());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        product.setDescription(dto.getDescription());
        product.setImagePath(imageUrl);

        return productService.saveProduct(product);
    }

    /* ================= GET PRODUCTS BY FARM ================= */

    @GetMapping("/farm/{farmId}")
    public Page<FarmProduct> getProducts(
            @PathVariable Long farmId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search) {

        return productService.getProducts(farmId, page, size, search);
    }

    /* ================= GET ALL PRODUCTS ================= */

    @GetMapping
    public List<FarmProduct> getAllProducts() {
        return productService.findAll();
    }

    /* ================= DELETE PRODUCT ================= */

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }
}

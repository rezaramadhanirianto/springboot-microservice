package com.rezaramadhanirianto.inventoryservice.controller;

import com.rezaramadhanirianto.inventoryservice.dto.InventoryResponse;
import com.rezaramadhanirianto.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    // /api/inventory?sku-code=ipad_10&sku-code=ipad_11
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){
        return inventoryService.isInStock(skuCode);
    }
}

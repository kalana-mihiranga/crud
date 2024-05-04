package com.ijse.database.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.database.entity.Item;
import com.ijse.database.service.ItemService;

@CrossOrigin(origins="*")
@RestController
public class ItemController {
    private ItemService itemService;
    
    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems() {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.getAllItems());
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        try {
            Item item = itemService.getItemById(id);
            return ResponseEntity.status(HttpStatus.OK).body(item);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/items")
    public ResponseEntity<Item> saveItem(@RequestBody Item item) {
        try {
            Item newItem = itemService.createItem(item);
            return ResponseEntity.status(HttpStatus.CREATED).body(newItem);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/categories/{categoryId}/items")
    public ResponseEntity<List<Item>> getItemsByCategoryId(@PathVariable Long categoryId) {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.getItemsByCategoryId(categoryId));
    }


    // url/categories/:categoryId/items
}

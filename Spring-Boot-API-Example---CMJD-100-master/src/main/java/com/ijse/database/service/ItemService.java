package com.ijse.database.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.database.entity.Item;

@Service
public interface ItemService {
    List<Item> getAllItems();
    Item getItemById(Long id);
    Item createItem(Item item);
    List<Item> getItemsByCategoryId(Long categoryId);
}

package com.ijse.database.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.database.entity.Item;
import com.ijse.database.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {
    private ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Item Not Found"));
    }

    @Override
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }
    
    @Override
    public List<Item> getItemsByCategoryId(Long categoryId) {
        return itemRepository.findItemsByCategoryId(categoryId);
    }
}

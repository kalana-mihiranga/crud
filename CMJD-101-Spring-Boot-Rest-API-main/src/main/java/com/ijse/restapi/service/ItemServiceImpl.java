package com.ijse.restapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.restapi.entity.Item;
import com.ijse.restapi.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {
    
    @Autowired
    private ItemRepository itemRepository;

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public Item updateItem(Long id, Item item) {

        //find existing item by ID or else return null
        Item existItem = itemRepository.findById(id).orElseThrow(null);

        //if item is found
        if(existItem != null) {

            existItem.setName(item.getName());
            existItem.setPrice(item.getPrice());
            existItem.setQty(item.getQty());
            itemRepository.save(existItem);
            
            return existItem;
        } else { //if item is not found
            return null;
        }
    }
}

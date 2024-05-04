package com.ijse.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.restapi.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    //Custom Queries
}

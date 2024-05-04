package com.ijse.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ijse.database.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
    //Custom Queries
    @Query("SELECT item FROM Item item WHERE item.category.id = :categoryId") //SELECT * FROM items WHERE category_id=:categoryId
    List<Item> findItemsByCategoryId(@Param("categoryId") Long categoryId);
}

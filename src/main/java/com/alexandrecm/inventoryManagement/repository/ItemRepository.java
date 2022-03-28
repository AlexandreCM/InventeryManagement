package com.alexandrecm.inventoryManagement.repository;

import com.alexandrecm.inventoryManagement.modele.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}

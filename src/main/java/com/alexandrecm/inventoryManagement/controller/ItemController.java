package com.alexandrecm.inventoryManagement.controller;

import com.alexandrecm.inventoryManagement.exception.item.ItemNotFoundException;
import com.alexandrecm.inventoryManagement.modele.Item;
import com.alexandrecm.inventoryManagement.repository.ItemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

  private final ItemRepository itemRepository;

  ItemController(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  @GetMapping("/items")
  List<Item> getItems() {
    return itemRepository.findAll();
  }

  @PostMapping("/items")
  Item newItem(@RequestBody Item newItem) {
    return itemRepository.save(newItem);
  }

  @GetMapping("/items/{id}")
  Item getItem(@PathVariable int id) {
    return itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
  }

  @PutMapping("/items/{id}")
  Item replaceItem(@RequestBody Item newItem, @PathVariable int id) {
    return itemRepository
        .findById(id)
        .map(
            item -> {
              item.setName(newItem.getName());
              return itemRepository.save(item);
            })
        .orElseGet(
            () -> {
              newItem.setId(id);
              return itemRepository.save(newItem);
            });
  }

  @DeleteMapping("/items/{id}")
  void deleteItem(@PathVariable int id) {
    itemRepository.deleteById(id);
  }
}

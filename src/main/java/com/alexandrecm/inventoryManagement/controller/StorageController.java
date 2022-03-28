package com.alexandrecm.inventoryManagement.controller;

import com.alexandrecm.inventoryManagement.exception.storage.StorageNotFoundException;
import com.alexandrecm.inventoryManagement.modele.Storage;
import com.alexandrecm.inventoryManagement.repository.StorageRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StorageController {

  private final StorageRepository storageRepository;

  StorageController(final StorageRepository storageRepository) {
    this.storageRepository = storageRepository;
  }

  @GetMapping("/storages")
  List<Storage> getStorages() {
    return storageRepository.findAll();
  }

  @PostMapping("/storages")
  Storage newStorage(@RequestBody final Storage newStorage) {
    return storageRepository.save(newStorage);
  }

  @GetMapping("/storages/{id}")
  Storage getStorage(@PathVariable final int id) {
    return storageRepository.findById(id).orElseThrow(() -> new StorageNotFoundException(id));
  }

  @PutMapping("/storages/{id}")
  Storage replaceStorage(@RequestBody final Storage newStorage, @PathVariable final int id) {
    return storageRepository
        .findById(id)
        .map(
            storage -> {
              storage.setName(newStorage.getName());
              return storageRepository.save(storage);
            })
        .orElseGet(
            () -> {
              newStorage.setId(id);
              return storageRepository.save(newStorage);
            });
  }

  @DeleteMapping("/storages/{id}")
  void deleteStorage(@PathVariable final int id) {
    storageRepository.deleteById(id);
  }
}

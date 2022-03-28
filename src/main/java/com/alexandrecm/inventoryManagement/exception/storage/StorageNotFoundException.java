package com.alexandrecm.inventoryManagement.exception.storage;

public class StorageNotFoundException extends RuntimeException {

  public StorageNotFoundException(int id) {
    super("Could not find storage " + id);
  }
}

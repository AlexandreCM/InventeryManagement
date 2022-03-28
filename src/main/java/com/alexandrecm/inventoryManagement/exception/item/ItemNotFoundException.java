package com.alexandrecm.inventoryManagement.exception.item;

public class ItemNotFoundException extends RuntimeException {

  public ItemNotFoundException(int id) {
    super("Could not find item " + id);
  }
}

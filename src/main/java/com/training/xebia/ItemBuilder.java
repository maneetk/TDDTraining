package com.training.xebia;

public class ItemBuilder {

	Item item = new Item();
	
	public ItemBuilder buildItemWithBarcode(String barcode) {
		item.setBarcode(barcode);
		return this;
	}
	
	public Item get(){
		return item;
	}

}

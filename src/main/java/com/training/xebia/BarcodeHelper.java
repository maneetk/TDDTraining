package com.training.xebia;

public class BarcodeHelper {

	public static String scanBarCode(Item item) {
		String barCode = item.getBarcode();
		char[] charArray = barCode.toCharArray();
		int sum = 0;
		for(char c: charArray){
			sum += Integer.parseInt(String.valueOf(c));
		}
		
		return String.valueOf(sum);
	}

}

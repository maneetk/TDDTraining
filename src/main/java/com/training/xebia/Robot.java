package com.training.xebia;

public class Robot{

	private int currentLoad = 0;
	private String currentStatus = "Welcome";
	private boolean redLightOn = false;
	private double batteryPercent = 100;

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void putWeight(int i) {
		this.currentLoad  += i;
		checkAndSetDisplayStatus();
	}

	private void checkAndSetDisplayStatus() {
		if(currentLoad > 10){
			setCurrentStatus("Overweight");
		}
	}

	public void removeWeight(int i) {
		this.currentLoad  -= i;
	}

	public boolean isRedLightOn() {
		return redLightOn ;
	}

	public void walk(double distanceInKm) throws CannotWalkException {
		System.out.println("Walking distance ...." + distanceInKm);
		setBatteryStatusAfterWalk(distanceInKm);
	}

	private void setBatteryStatusAfterWalk(double distanceInKm) throws CannotWalkException {
		double distanceInMeters = distanceInKm * 1000;
		batteryPercent  = batteryPercent - 2 * distanceInMeters / 100;
		
		if(currentLoad >10) {
			throw new CannotWalkException("Overweight");
		}
		batteryPercent = batteryPercent - 2 * currentLoad;
		if(batteryPercent < 15){
			setRightLightOn();
		}
	}

	private void setRightLightOn() {
		redLightOn = true;
	}

	public double getBatteryPercent() {
		return batteryPercent;
	}

	public void scan(Item item) {
		setCurrentStatus(BarcodeHelper.scanBarCode(item));
	}

	private void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	
}
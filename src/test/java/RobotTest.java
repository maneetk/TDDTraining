import static org.junit.Assert.*;

import org.junit.Test;

import com.training.xebia.BarcodeHelper;
import com.training.xebia.CannotWalkException;
import com.training.xebia.Item;
import com.training.xebia.ItemBuilder;
import com.training.xebia.Robot;

public class RobotTest {

	Robot r = new Robot();
	ItemBuilder itemBuilder = new ItemBuilder();
	
	@Test
	public void initialDisplayWelcome(){
		assertEquals( "Welcome", r.getCurrentStatus());
	}
	
	@Test
	public void displayMessageOverweight(){
		r.putWeight(11);
		assertEquals("Overweight", r.getCurrentStatus());
	}
	
	@Test
	public void doesNotDisplayOverWeight(){
		r.removeWeight(1);
		assertNotEquals("Overweight", r.getCurrentStatus());
	}
	
	@Test
	public void redLightOff(){
		assertEquals(false, r.isRedLightOn());
	}
	
	@Test
	public void redLightOn() throws CannotWalkException{
		r.walk(4.5);
		assertEquals(true, r.isRedLightOn());
	}
	
	@Test
	public void batteryStatusForHalfDistance() throws CannotWalkException{
		assertEquals(100, r.getBatteryPercent(), 0.5);
		r.walk(2.5);
		assertEquals(50, r.getBatteryPercent(), 0.5);
	}
	
	
	@Test
	public void walkingWithWeight() throws CannotWalkException{
		r.putWeight(5);
		r.walk(1);
		assertEquals(70, r.getBatteryPercent(), 0.5);
	}
	
	@Test(expected = CannotWalkException.class)
	public void shouldNotWalk() throws CannotWalkException{
		r.putWeight(12);
		r.walk(1);
	}
	
	@Test
	public void barCodeScanReturns20(){
		
		Item item = itemBuilder.buildItemWithBarcode("5555").get();
		r.scan(item);
		assertEquals("20", r.getCurrentStatus());
	}
	
	@Test
	public void barCodeScanReturns40(){
		Item item = itemBuilder.buildItemWithBarcode("88888").get();
		r.scan(item);
		assertEquals("40", r.getCurrentStatus());
	}
	
	@Test
	public void walkWithThreeAndHalfKm() throws CannotWalkException{
		r.walk(3.5);
		assertEquals(30, r.getBatteryPercent(), 0.5);
	}
	
	@Test
	public void walkTwoKmAndThreeKg() throws CannotWalkException{
		r.putWeight(3);
		r.walk(2);
		assertEquals(54, r.getBatteryPercent(), 0.5);
	}
	
}

/**
 * @author Doğa Yolcu ID:22098727  email:dogayolcuu@gmail.com
 * Car.java
 * Sisteme yüklenecek olan arabaların attributeleri ve constructor'ı burada tanımlanmıştır.
 *
 */
public class Car {

	private String gear;
	private String fuel_type;
	private String vehicle_group;
	private String pick_up_location;
	private String return_location;
	private int door;
	private boolean available;
	private String number_plate;
	private int rent;
	private int rentOfWeekEnd;
	
	public  Car(String gear,String fuel_type,String vehicle_group,String pick_up_location,String return_location,int door,boolean available,String number_plate,int rent,int rentOfWeekEnd)
	{
		this.gear=gear;
		this.fuel_type=fuel_type;
		this.vehicle_group=vehicle_group;
		this.pick_up_location=pick_up_location;
		this.return_location=return_location;
		this.door=door;
		this.available=available;
		this.number_plate=number_plate;
		this.rent=rent;
		this.rentOfWeekEnd=rentOfWeekEnd;
	}

	public int getRentOfWeekEnd() {
		return rentOfWeekEnd;
	}

	public void setRentOfWeekEnd(int rentOfWeekEnd) {
		this.rentOfWeekEnd = rentOfWeekEnd;
	}

	public String toString()
	{
		return gear +","+ fuel_type +" ," + vehicle_group +","+ pick_up_location + ", "+ return_location + "," +door +", " + available + ", " + number_plate;
	}

	public String getGear() {
		return gear; 
	}


	public void setGear(String gear) {
		this.gear = gear;
	}


	public String getFuel_type() {
		return fuel_type;
	}


	public void setFuel_type(String fuel_type) {
		this.fuel_type = fuel_type;
	}


	public String getVehicle_group() {
		return vehicle_group;
	}


	public void setVehicle_group(String vehicle_group) {
		this.vehicle_group = vehicle_group;
	}


	public String getPick_up_location() {
		return pick_up_location;
	}


	public void setPick_up_location(String pick_up_location) {
		this.pick_up_location = pick_up_location;
	}


	public String getReturn_location() {
		return return_location;
	}


	public void setReturn_location(String return_location) {
		this.return_location = return_location;
	}


	public int getDoor() {
		return door;
	}


	public void setDoor(int door) {
		this.door = door;
	}


	public boolean isAvailable() {
		return available;
	}


	public void setAvailable(boolean available) {
		this.available = available;
	}


	public String getNumber_plate() {
		return number_plate;
	}


	public void setNumber_plate(String number_plate) {
		this.number_plate = number_plate;
	}

	public int getRent() {
		return rent;
	}

	public void setRent(int rent) {
		this.rent = rent;
	}
	
	
	
	
	
	
	
	
}

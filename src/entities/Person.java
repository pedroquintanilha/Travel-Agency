package entities;


public class Person {

	private String name;
	private String email;
	private Integer cityNumber;
	private Integer hotelNumber;
	
	public Person() {
	}
	
	 
	public Person(String name, String email, Integer cityNumber) {
		this.name = name;
		this.email = email;
		this.cityNumber = cityNumber;
	}
	
	
	public Person(String name, String email, Integer cityNumber, Integer hotelNumber) {
		this.name = name;
		this.email = email;
		this.cityNumber = cityNumber;
		this.hotelNumber = hotelNumber;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getCityNumber() {
		return cityNumber;
	}
	public void setCityNumber(Integer cityNumber) {
		this.cityNumber = cityNumber;
	}
	public Integer getHotelNumber() {
		return hotelNumber;
	}
	
	
	@Override
	 public String toString() {
		return "Name: "+getName()+", email: "+getEmail();
	}
	
	
}

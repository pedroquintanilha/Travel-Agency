package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import entities.Person;
import java.util.Locale;
import java.util.Random;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner s= new Scanner(System.in);
		Random rand = new Random(); 
		
		List<Person> listBerlin=new ArrayList<>();
		List<Person> listHamburg=new ArrayList<>();
		List<Person> listMunich=new ArrayList<>();
		List<List<Person>> listCities=new ArrayList<>();
		
		System.out.println("Hi There!;) We are the BerlinTrips, and we are glad that you want to travel with our company!");
		System.out.println();
		System.out.println("The numbers so you can enter the city you live:");
		System.out.println("Berlin -> 1");
		System.out.println("Hamburg -> 2");
		System.out.println("Munich -> 3");
		System.out.println();
		System.out.println("The numbers so you can choose the hotel that you want to stay during your trip in Berlin:");
		System.out.println("Adlon Kempinski Hotel, Room rate: 9.3 --> 1");
		System.out.println("Gorki Apartments, Room rate: 9.4 --> 2");
		System.out.println("Regent Hotel, Room rate: 9.3 --> 3\n");
		System.out.println("One of the people staying at a hotel will be randomly chosen to receive a discount!");
		System.out.println("The hotel will also be chosen at random.");
		System.out.println("All available hotels are located in Berlin.");
		System.out.println();
		
		//Reading all the data and creating the sublists and objects.
		int cont1=0;
		int cont23=0;
	
		do {
			if(cont1==2) {
				System.out.print("From now on there are no more places left for people from Berlin! Only from Hamburg or Munich available!\n");
			}
	
			if(cont23==3) {
				System.out.print("The places for people from Hamburg and Munich ran out! Only from Berlin available!\n");
			}
		
			System.out.print("Please enter the city you live:");
			Integer cityNumber=s.nextInt();
			
			while(cityNumber!=1 && cityNumber!=2 && cityNumber!=3) {
				System.out.print("Please enter an available city (numbers 1 to 3): ");
			    cityNumber=s.nextInt();
			}
			
			while(cityNumber==1 && cont1==2) {
				System.out.print("People from Berlin can no longer participate, please enter another City!");
				cityNumber=s.nextInt();
			}
			
			while(cityNumber !=1 && cont23==3) {
				System.out.print("People from Hamburg or Munich can no longe participate, please enter another City! ");
				cityNumber=s.nextInt();	
			}
			
			if(cityNumber==1) {
				cont1++;
				System.out.print("Please enter your name: ");
				s.nextLine();
				String name=s.nextLine();
				System.out.print("Please enter your email: ");
				String email=s.nextLine();
				System.out.println("Data: "+name+", "+email+", "+cityNumber);
				System.out.println();
				Person person=new Person(name,email,cityNumber);
				listBerlin.add(person);
			}
			else { 
				cont23++;
				System.out.print("Please enter your name: ");
				s.nextLine();
				String name=s.nextLine();
				System.out.print("Please enter your email: ");
				String email=s.nextLine();
				System.out.print("Please enter the hotel number: ");
				Integer hotelNumber=s.nextInt();
				
				while(hotelNumber!= 1 && hotelNumber!=2 && hotelNumber!=3) {
					System.out.print("Please enter an available hotel (numbers 1 to 3): ");
					hotelNumber=s.nextInt();
				} 
				
				System.out.println("Data: "+name+", "+email+", "+cityNumber+", "+hotelNumber);
				System.out.println();
				Person person=new Person(name,email,cityNumber,hotelNumber);
				if(cityNumber==2) {
					listHamburg.add(person);
				}
				else {
					listMunich.add(person);
				}
			}
			
		} while(cont1<2 || cont23<3);
		
		//Creating the list of lists.
		listCities.add(listBerlin);
		listCities.add(listHamburg);
		listCities.add(listMunich);
		
		int totalOfPeople = 0;
		List<Person> listHotels=new ArrayList<>();
		
		System.out.println("List of the germans who are joining the trip: ");
		
		//To practice more lists. The total number of people is already known. Total=cont1+cont23=5
		for (List<Person> city : listCities) {
			for(Person person : city) {
				System.out.println(person.getName());
				totalOfPeople++;
			}
		}
		
		System.out.println();
		System.out.println("A total of "+totalOfPeople+" people are interested! We are already excited for it!:)");
		System.out.println();
		System.out.println("A hotel won't be necessary for the people from Berlin. The Berliners are: ");
				
		for(Person fromBerlin : listBerlin) {
			System.out.println(fromBerlin);
		}
		
		System.out.println();
		System.out.println("The rest of the people who are not from Berlin are all able to receive de hotel discount. ");
		System.out.println("The discount is always equivalent to the percentage of people who are not from Berlin.");
		System.out.println("The hotel that will have a discount will be selected randomly, and only one person will win it, also randomly!");
		
		
		//Which hotel will have a discount. Randomly selected, considering that rand.nextInt() could return 0.
		List<Integer> randomHotel=new ArrayList<Integer>();
		randomHotel.add(1);
		randomHotel.add(2);
		randomHotel.add(3);
		int randomIndex1= rand.nextInt(randomHotel.size())+1;
		
		
		
		//Putting all the ones who chose the selected hotel in a list. Only from Hamburg or Munich could have chosen a hotel.
		//It is not yet known where all the people are from, but there must be 3 people that are not from Berlin.
		if(listCities.get(1).size()>=1 && listCities.get(2).size()>=1) {
			System.out.printf("The %d people/person from Hamburg and the %d from Munich are all competing for a %.2f%% discount!\n",listHamburg.size(),listMunich.size(),howWeAlwaysCalculateTheDiscount(totalOfPeople, listBerlin.size()));
			for (Person person : listCities.get(1)) {
				if(person.getHotelNumber()==randomIndex1) {
					listHotels.add(person);	
				}
			}
			for(Person person : listCities.get(2)) {
				if(person.getHotelNumber()==randomIndex1) {
					listHotels.add(person);
				}
			}	
		}
		//The three left must be from Hamburg or Munich.
		else if(listCities.get(1).size()==3) {
			System.out.printf("The %d people from Hamburg are all competing for a %.2f%% discount!\n",listHamburg.size(),howWeAlwaysCalculateTheDiscount(totalOfPeople, listBerlin.size()));
			for (Person person : listCities.get(1)) {
				if(person.getHotelNumber()==randomIndex1) {
					listHotels.add(person);	
				}
			}
		}  
		else {
			System.out.printf("The %d people from Munich are all competing for a %.2f%% discount!\n",listMunich.size(),howWeAlwaysCalculateTheDiscount(totalOfPeople, listBerlin.size()));
			for(Person person : listCities.get(2)) {
				if(person.getHotelNumber()==randomIndex1) {
					listHotels.add(person);
				}
			}	
		}
		
		
		//Randomly getting a person from the created list.
		if(listHotels.size()>=1) {
			int randomIndex2 = rand.nextInt(listHotels.size());
			String person=listHotels.get(randomIndex2).getName();
			System.out.print(person+" is receiving the discount!!");
		}
		else {
			System.out.println("The hotel randomly selected for the discount was hotel number "+randomIndex1+". No one chose this hotel, so no one receives the discount.");
		}
		
	
		//Creating a matrix N x M. N= total of people , M= 3 columns (name, email and chosen hotel)
		String[][] outputMatrix= new String[totalOfPeople][3];
		
		int i=0;
		for (List<Person> city : listCities) {
			for(Person x : city) {
					outputMatrix[i][0]=x.getName();
					outputMatrix[i][1]=x.getEmail();
					int cityNumber=x.getCityNumber();
					if(cityNumber==1) {
						outputMatrix[i][2]="No hotel";
					}
					else if(x.getHotelNumber()==1) {
						outputMatrix[i][2]="Adlon Kempinski";
					}
					else if(x.getHotelNumber()==2) {
						outputMatrix[i][2]="Gorki Apartments";
					}
					else {
						outputMatrix[i][2]="Regent Hotel";
					}	
					i++;
			}		
		}
		
		
		System.out.println();
		System.out.println();
		System.out.println("List of the people who are joining the trip (name, email and then the chosen hotel, if the person is not from Berlin:");
		
		//Showing the list of the people who are joining the trip.
		for(int k=0; k<outputMatrix.length; k++) {
			for(int j=0; j<outputMatrix[k].length; j++) {
				System.out.print(outputMatrix[k][j]+"   ");
				
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println("Get ready for your dream trip!");
		System.out.println("Machen Sie sich bereit für Ihre Traumreise!");
		
		s.close();

}
	//So i could use one more static in this class. The discount is always 60%.
	public static double howWeAlwaysCalculateTheDiscount(int total, int takeAway) {
		return (100*(total-takeAway))/total;
	}
}

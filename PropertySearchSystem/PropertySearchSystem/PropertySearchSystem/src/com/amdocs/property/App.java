package com.amdocs.property;

import java.sql.SQLException;
import java.util.*;
import com.amdocs.property.model.*;
import com.amdocs.property.dao.*;
import com.amdocs.property.exception.PropertyNotFoundException;

public class App{
	static List<Property> propertylist=new ArrayList<Property>();
	protected static Property createProperty() {
		Scanner sc=new Scanner(System.in);
		Property obj = new Property();
		try {
			System.out.println("Enter propertyId: ");
			int id = sc.nextInt();
			obj.setPropertyId(id);
			id=obj.getPropertyId();
		
			sc.nextLine();
			System.out.println("Enter no of Rooms: ");
			String noRooms = sc.nextLine();
			obj.setNoOfRooms(noRooms);
			noRooms=obj.getNoOfRooms();
			
			System.out.println("Enter no of Area in Sqft: ");
//			double areaSqft = Double.parseDouble(scanner.nextLine());
			double areaSqft = sc.nextDouble();
			obj.setAreaInSqft(areaSqft);
			areaSqft=obj.getAreaInSqft();
			
			System.out.println("Enter the floor number: ");
			int floorNo = sc.nextInt();
			obj.setFloorNo(floorNo);
			floorNo=obj.getFloorNo();
			
			System.out.println("Enter city: ");
			sc.nextLine();
			String city = sc.nextLine();
			obj.setCity(city);
			city=obj.getCity();
			
			System.out.println("Enter State: ");
			String state = sc.nextLine();
			obj.setState(state);
			state=obj.getState();
			
			System.out.println("Enter the property price: ");
//			double areaSqft = Double.parseDouble(scanner.nextLine());
			double cost = sc.nextDouble();
			obj.setCost(cost);
			cost=obj.getCost();
			
			System.out.println("Enter Owner name: ");
			sc.nextLine();
			String oname = sc.nextLine();
			obj.setOwnerName(oname);
			oname=obj.getOwnerName();
			
			System.out.println("Enter Owner contact no: ");
			String ocontact = sc.nextLine();
			obj.setOwnerContactNo(ocontact);
			ocontact=obj.getOwnerContactNo();
			
		} catch (NumberFormatException e) {
			System.out.println("Please Provide a number value\n " + e.getMessage());
		}

		return obj;
	}

	protected static void displayProperty(Property property) {
		System.out.println(property);							// from - to string 
	}

	protected static PropertyDao dao = new PropertyDaoImp();
	

	protected static void addProperty() {
		Property createProperty;
//		try {
			createProperty = createProperty();
//			propertylist.add(createProperty);
			int id= dao.addProperty(createProperty);
//		} catch (SQLException e) {
//			System.out.println(e);
//		}	
//		}catch (SystemException e) {
//			System.out.println(e);
//		}
			System.out.println("The property details are updated ,your property ID is "+ id);
		
	}
	
	protected static void deleteProperty() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the property id you wanna delete: ");
		int id=sc.nextInt();
		int del=dao.deleteProperty(id);	
		try {
			if(del==0)
				throw new PropertyNotFoundException("No Property match");
			else {
				System.out.println("The property details with ID: "+ del + " is deleted");
			}
		
	       }
	    catch(PropertyNotFoundException ex) {
	    	System.out.println("Enter correct Property id \n");
	    }
	}
	protected static void updatePropertyCost() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the property id number you want to update: ");
		int id=sc.nextInt();
		System.out.println("Enter the new cost: ");
		Double cost=sc.nextDouble();
		boolean bol=dao.updatePropertyCost(id,cost);
		try {
			if(bol==false)
				throw new PropertyNotFoundException("No Property match");
			else {
				System.out.println("The property details with ID: "+ id + " is updated");
			}
		}
		 catch(PropertyNotFoundException ex) {
		    	System.out.println("Enter correct Property id \n");
	}
	}
	
	protected static void searchByCity() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the city name you wanna search: ");
		String city=sc.nextLine();
		List<Property> list1=dao.ByCity(city);
		try {
			if(list1.size()==0)
				throw new PropertyNotFoundException("No Property match");
			else {
				for (Property property :list1) {
					System.out.println(property);
				}
			}
	}
		 catch(PropertyNotFoundException ex) {
		    	System.out.println("look for other city \n");
	}
	}
	protected static void showAllProperty() {
		PropertyDao dao=new PropertyDaoImp();
		List<Property> allProperty = dao.showAllProperties();
		try {
			if(allProperty.size()==0)
				throw new PropertyNotFoundException("No Property match");
			else {
				for (Property property : allProperty) {
					if (property !=null)
					System.out.println(property);
			}
		  }
	    }
		catch(PropertyNotFoundException ex) {
		    	System.out.println("\n");
		}
		}
	protected static void searchByCost() {
		PropertyDao dao=new PropertyDaoImp();
		System.out.println("Minimum price: ");
		Scanner sc=new Scanner(System.in);
		Double min=sc.nextDouble();
		System.out.println("Maximum price: ");
		Double max=sc.nextDouble();
		List<Property> allProperty = dao.searchByCost(min, max);
		try {
			if(allProperty.size()==0)
				throw new PropertyNotFoundException("No Property match");
			else {
				for (Property property : allProperty) {
					if (property != null)
						System.out.println(property);
			}
		}
	}
		catch(PropertyNotFoundException ex) {
	    	System.out.println("look for other cost range \n");
	}
	}
	protected static void searchByNoOfRoomsAndCity() {
		PropertyDao dao=new PropertyDaoImp();
		System.out.println("Enter the city name: ");
		Scanner sc=new Scanner(System.in);
		String room=sc.nextLine();
		System.out.println("Enter the no of rooms in terms of BHK: ");
		String city=sc.nextLine();
		List<Property> allProperty = dao.searchByNoOfRoomsAndCity(room,city);
		try {
			if(allProperty.size()==0)
				throw new PropertyNotFoundException("No Property match");
			else {
				for (Property property : allProperty) {
					if (property != null)
						System.out.println(property);
				}
			}
		}
		catch(PropertyNotFoundException ex) {
	    	System.out.println("look for other city and room \n");
		}
	}
}


package com.amdocs.property.dao;

import java.sql.*;
import java.util.*;
import com.amdocs.property.*;

import com.amdocs.property.model.Property;

public class PropertyDaoImp implements PropertyDao{
	public static Connection getConnection() {
		Connection con=null;
		 try {
			 Class.forName("oracle.jdbc.driver.OracleDriver"); //registration
			 con=DriverManager.getConnection("Jdbc:Oracle:thin:@localhost:1521:orcl","SYSTEM","Amdocs123"); //connection
		 }catch(Exception e) {
			 System.out.println("error in connection");
		 }
		 return con;
	 }
	
	private static final String INSERT_PROPERTY="insert into propertylist(property_id,no_rooms,area_in_sqft,floor_no,city,state_name,price,owner_name,owner_number) values(?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_PROPERTY="update propertylist set price=? where property_id=?";
	private static final String DELETE_PROPERTY="delete from propertylist where property_id=?";
	private static final String SEARCH_CITY="Select * from propertylist where city=?";
	private static final String DISPLAY_ALL="Select * from propertylist";
	private static final String SEARCH_BY_COST="select * from propertylist where price between ? and ?";
	private static final String CITY_ROOM="select * from propertylist where city=? and no_rooms=? ";
	
	

	
	@Override
	public int addProperty(Property property) {
		// TODO Auto-generated method stub
		 try {
			 PreparedStatement pst=getConnection().prepareStatement(INSERT_PROPERTY);
			 pst.setInt(1, property.getPropertyId());
			 pst.setString(2, property.getNoOfRooms());
			 pst.setDouble(3,property.getAreaInSqft());
			 pst.setInt(4, property.getFloorNo());
			 pst.setString(5,property.getCity() );
			 pst.setString(6, property.getState());
			 pst.setDouble(7,property.getCost());
			 pst.setString(8, property.getOwnerName());
			 pst.setString(9, property.getOwnerContactNo());
			  pst.executeUpdate();
			
		 }catch(Exception e) {
			 System.out.println("error");
		 }
		 return property.getPropertyId();
	}

	@Override
	public int deleteProperty(int id) {
		int count=0;
		try {
			
			 PreparedStatement pst=getConnection().prepareStatement(DELETE_PROPERTY);
			 pst.setInt(1, id);
			 pst.executeUpdate();
			
		 }catch(Exception e) {
			 System.out.println("error");
		 }
		return count;
	}

	@Override
	public boolean updatePropertyCost(int id, double cost) {
		// TODO Auto-generated method stub
		int count=0;
		try {
			 PreparedStatement pst=getConnection().prepareStatement(UPDATE_PROPERTY);
			 pst.setInt(1,(int)cost);
			 pst.setInt(2,id);
			 count= pst.executeUpdate();
			
		 }catch(Exception e) {
			 System.out.println("error");
		 }
		if(count>0)
			return true;
		else
			return false;
	}

	@Override
	public List<Property> ByCity(String city) 
	{
		List<Property> list1 = new ArrayList<Property>();
		// TODO Auto-generated method stub
//		Statement stmt;
		ResultSet rs;
//		Connection con;
		try{
			PreparedStatement pst=getConnection().prepareStatement(SEARCH_CITY);
		pst.setString(1,city);
		rs=pst.executeQuery();
		while(rs.next()) {
//			System.out.println(rs.getString(1)+" "+rs.getInt(2));
			Property prop = new Property();
			prop.setPropertyId(rs.getInt(1));
			prop.setNoOfRooms(rs.getString(2));
			prop.setAreaInSqft(rs.getDouble(3));
			prop.setFloorNo(rs.getInt(4));
			prop.setCity(rs.getString(5));
			prop.setState(rs.getString(6));
			prop.setCost(rs.getDouble(7));
			prop.setOwnerName(rs.getString(8));
			prop.setOwnerContactNo(rs.getString(9));
			list1.add(prop);
			
		}
		}
			catch(Exception e){e.printStackTrace();}
		return list1;
		}

	@Override
	public List<Property> showAllProperties() {
		List<Property> list1 = new ArrayList<Property>();
		// TODO Auto-generated method stub
//		Statement stmt;
		ResultSet rs;
//		Connection con;
		try{
			PreparedStatement pst=getConnection().prepareStatement(DISPLAY_ALL);
		rs=pst.executeQuery();
		while(rs.next()) {
//			System.out.println(rs.getString(1)+" "+rs.getInt(2));
			Property prop = new Property();
			prop.setPropertyId(rs.getInt(1));
			prop.setNoOfRooms(rs.getString(2));
			prop.setAreaInSqft(rs.getDouble(3));
			prop.setFloorNo(rs.getInt(4));
			prop.setCity(rs.getString(5));
			prop.setState(rs.getString(6));
			prop.setCost(rs.getDouble(7));
			prop.setOwnerName(rs.getString(8));
			prop.setOwnerContactNo(rs.getString(9));
			list1.add(prop);
			
		}
		}
			catch(Exception e){e.printStackTrace();}
		return list1;
	}

	@Override
	public List<Property> searchByCost(double min, double max) {
		List<Property> list1 = new ArrayList<Property>();
		// TODO Auto-generated method stub
//		Statement stmt;
		ResultSet rs;
//		Connection con;
		try{
			PreparedStatement pst=getConnection().prepareStatement(SEARCH_BY_COST);
		pst.setInt(1,(int)min);
		pst.setInt(2,(int)max);
		rs=pst.executeQuery();
		while(rs.next()) {
//			System.out.println(rs.getString(1)+" "+rs.getInt(2));
			Property prop = new Property();
			prop.setPropertyId(rs.getInt(1));
			prop.setNoOfRooms(rs.getString(2));
			prop.setAreaInSqft(rs.getDouble(3));
			prop.setFloorNo(rs.getInt(4));
			prop.setCity(rs.getString(5));
			prop.setState(rs.getString(6));
			prop.setCost(rs.getDouble(7));
			prop.setOwnerName(rs.getString(8));
			prop.setOwnerContactNo(rs.getString(9));
			list1.add(prop);
			
		}
		}
			catch(Exception e){e.printStackTrace();}
		return list1;
	}

	@Override
	public List<Property> searchByNoOfRoomsAndCity(String room, String city) {
		List<Property> list1 = new ArrayList<Property>();
		// TODO Auto-generated method stub
//		Statement stmt;
		ResultSet rs;
//		Connection con;
		try{
			PreparedStatement pst=getConnection().prepareStatement(CITY_ROOM);
		pst.setString(1,room);
		pst.setString(2,city);
		rs=pst.executeQuery();
		while(rs.next()) {
//			System.out.println(rs.getString(1)+" "+rs.getInt(2));
			Property prop = new Property();
			prop.setPropertyId(rs.getInt(1));
			prop.setNoOfRooms(rs.getString(2));
			prop.setAreaInSqft(rs.getDouble(3));
			prop.setFloorNo(rs.getInt(4));
			prop.setCity(rs.getString(5));
			prop.setState(rs.getString(6));
			prop.setCost(rs.getDouble(7));
			prop.setOwnerName(rs.getString(8));
			prop.setOwnerContactNo(rs.getString(9));
			list1.add(prop);
			
		}
		}
			catch(Exception e){e.printStackTrace();}
		return list1;
	}

}

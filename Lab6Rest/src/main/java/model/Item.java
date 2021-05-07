package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Item {


	//DB Connection
	public Connection connect()
	{ 
	 Connection con = null; 
	 
	 try 
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 con= DriverManager.getConnection("jdbc:mysql://localhost:3306/paflab", "root", ""); 
	 
	 //For testing
	 System.out.print("Successfully Connected"); 
	 
	 } 
	 catch(Exception e) 
	 { 
	 e.printStackTrace(); 
	 } 
	 
	 return con; 
	
	}
	
	
	
	// Insert Items
		public String insertItem(String code, String category, String name, String brand, String desc, String price) {
			Connection con = connect();
			String output = "";
			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " insert into lab (`itemID`,`itemCode`,`itemCategory`,`itemName`,`itemBrand`,`itemDesc`,`itemPrice`)" + " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt;
			try {
				preparedStmt = con.prepareStatement(query);

				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, code);
				preparedStmt.setString(3, category);
				preparedStmt.setString(4, name);
				preparedStmt.setString(5, brand);
				preparedStmt.setString(6, desc);
				preparedStmt.setDouble(7, Double.parseDouble(price));
				

				preparedStmt.execute();
				con.close();
				output = "Inserted Successfully";
				
			} catch (SQLException e) {
				output = "Error while inserting";
				System.err.println(e.getMessage());
			}
			// binding values

			return output;
		}
		
		
		

		// Read Items
		public String readItems() {
			String output = "";

			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for reading.";
				}

				// Prepare the html table to be displayed
				output = "<table border='1'>" + "<tr><th>Item Code</th><th>Item Category</th><th>Item Name</th><th>Item Brand</th><th>Item Description</th>"
						+ "<th>Item Price</th><th>Update</th><th>Remove</th></tr>";

				String query = "select * from lab";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				// iterate through the rows in the result set
				while (rs.next()) {
					String itemID = Integer.toString(rs.getInt("itemID"));
					String itemCode = rs.getString("itemCode");
					String itemCategory = rs.getString("itemCategory");
					String itemName = rs.getString("itemName");
					String itemBrand = rs.getString("itemBrand");
					String itemDesc = rs.getString("itemDesc");
					String itemPrice = Double.toString(rs.getDouble("itemPrice"));

					
					// Add a row into the html table
					output += "<tr><td>" + itemCode + "</td>";
					output += "<td>" + itemCategory + "</td>";
					output += "<td>" + itemName + "</td>";
					output += "<td>" + itemBrand + "</td>";
					output += "<td>" + itemDesc + "</td>";
					output += "<td>" + itemPrice + "</td>";
					
					// Buttons
					output += "<td>" + "<form method='post' action='itemps.jsp'>"
							+ "<input name='btnUpdate' type='submit' value='Update' class='btn btn-info' >" 
							+ "<input name='update_itemID' type='hidden' value='" + itemID + "'>" + "</form>" + "<td>"
							+ "<form method='post' action='itemps.jsp'>"
							+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
							+ "<input name='itemID' type='hidden' value='" + itemID + "'>" + "</form></td></tr>";
				}
				
			} catch (Exception e) {
				output = "Error while reading the items.";
				System.err.println(e.getMessage());
			}

			return output;
		}
		
		
		
		//Update Items
		public String updateItem(String ID, String code, String category, String name, String brand, String desc, String price)
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 String query = "UPDATE lab SET itemCode=?,itemCategory=?,itemName=?,itemBrand=?,itemDesc=?,itemPrice=? WHERE itemID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, code); 
		 preparedStmt.setString(2, category); 
		 preparedStmt.setString(3, name); 
		 preparedStmt.setString(4, brand); 
		 preparedStmt.setString(5, desc); 
		 preparedStmt.setDouble(6, Double.parseDouble(price));  
		 preparedStmt.setInt(7, Integer.parseInt(ID)); 
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Updated Successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while updating the item."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		
		
		
		
		// Delete Items
		public String deleteItem(String itemID)
		{ 
		 String output = ""; 
		try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for deleting."; 
		 } 
		 // create a prepared statement
		 String query = "delete from lab where itemID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(itemID)); 
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Deleted Successfully"; 
		 } 
		catch (Exception e) 
		 { 
		 output = "Error while deleting the item."; 
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
		}
	
	
}

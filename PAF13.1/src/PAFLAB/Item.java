package PAFLAB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;


public class Item {

	public Connection connect()
	{ 
	 Connection con = null; 
	 
	 try 
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 con= DriverManager.getConnection("jdbc:mysql://localhost:3306/paf", "root", ""); 
	 
	 //For testing
	 System.out.print("Successfully connected"); 
	 } 
	 catch(Exception e) 
	 { 
	 e.printStackTrace(); 
	 } 
	 
	 return con; 
	
	}
	
	// insert method
		public String insertItem(String code, String name, String price, String desc) {
			Connection con = connect();
			String output = "";
			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " insert into lab (`itemID`,`itemCode`,`itemName`,`itemPrice`,`itemDesc`)" + " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt;
			try {
				preparedStmt = con.prepareStatement(query);

				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, code);
				preparedStmt.setString(3, name);
				preparedStmt.setDouble(4, Double.parseDouble(price));
				preparedStmt.setString(5, desc);

				preparedStmt.execute();
				con.close();
				output = "Inserted successfully";
				
			} catch (SQLException e) {
				output = "Error while inserting";
				System.err.println(e.getMessage());
			}
			// binding values

			return output;
		}

		// read items
		public String readItems() {
			String output = "";

			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for reading.";
				}

				// Prepare the html table to be displayed
				output = "<table border='1'>" + "<tr><th>Item Code</th><th>Item Name</th><th>Item Price</th>"
						+ "<th>Item Description</th><th>Update</th><th>Remove</th></tr>";

				String query = "select * from lab";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				// iterate through the rows in the result set
				while (rs.next()) {
					String itemID = Integer.toString(rs.getInt("itemID"));
					String itemCode = rs.getString("itemCode");
					String itemName = rs.getString("itemName");
					String itemPrice = Double.toString(rs.getDouble("itemPrice"));

					String itemDesc = rs.getString("itemDesc");
					// Add a row into the html table
					output += "<tr><td>" + itemCode + "</td>";
					output += "<td>" + itemName + "</td>";
					output += "<td>" + itemPrice + "</td>";
					output += "<td>" + itemDesc + "</td>";
					
					// buttons
					output += "<td>" + "<form method='post' action='itemps.jsp'>"
							+ "<input name='btnUpdate' type='submit' value='Update'></td>"
							+ "<input name='update_itemID' type='hidden' value='" + itemID + "'>" + "</form>" + "<td>"
							+ "<form method='post' action='items.jsp'>"
							+ "<input name='btnRemove' type='submit' value='Remove'>"
							+ "<input name='itemID' type='hidden' value='" + itemID + "'>" + "</form></td></tr>";
				}
				
			} catch (Exception e) {
				output = "Error while reading the items.";
				System.err.println(e.getMessage());
			}

			return output;
		}

}
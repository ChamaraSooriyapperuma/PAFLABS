package com;


import model.Item;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
//import com.google.gson.*;
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Item")


public class ItemService {

	Item itemObj = new Item();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return itemObj.readItems();
	}

	//Insert Item() method
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertItem(@FormParam("itemCode") String itemCode, 
							@FormParam("itemCategory") String itemCategory, 
							@FormParam("itemName") String itemName, 
							@FormParam("itemBrand") String itemBrand, 	 
							@FormParam("itemDesc") String itemDesc,
							@FormParam("itemPrice") String itemPrice)
	{ 
	 String output = itemObj.insertItem(itemCode, itemCategory, itemName, itemBrand, itemDesc, itemPrice); 
	return output; 
	}

	
	//Update Item() method
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	
	public String updateItem(String itemData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String itemID = itemObject.get("itemID").getAsString(); 
	 String itemCode = itemObject.get("itemCode").getAsString(); 
	 String itemCategory = itemObject.get("itemCategory").getAsString(); 
	 String itemName = itemObject.get("itemName").getAsString(); 
	 String itemBrand = itemObject.get("itemBrand").getAsString(); 
	 String itemDesc = itemObject.get("itemDesc").getAsString(); 
	 String itemPrice = itemObject.get("itemPrice").getAsString(); 
	 String output = itemObj.updateItem(itemID, itemCode, itemCategory, itemName, itemBrand, itemDesc, itemPrice); 
	return output; 
	}
	
	
	//Delete Item() method
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteItem(String itemData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String itemID = doc.select("itemID").text(); 
	 String output = itemObj.deleteItem(itemID); 
	return output; 
	}
	
}

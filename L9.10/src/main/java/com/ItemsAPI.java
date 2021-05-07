package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;
import com.Item;

/**
 * Servlet implementation class ItemsAPI
 */
@WebServlet("/ItemsAPI")
public class ItemsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Item itemObj = new Item();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemsAPI() {
        super();
        
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//Not Used
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String output = itemObj.insertItem(request.getParameter("itemCode"), 
						     request.getParameter("itemCategory"), 
						     request.getParameter("itemName"), 
						     request.getParameter("itemBrand"), 
				           	 request.getParameter("itemDesc"), 
					         request.getParameter("itemPrice")); 
		
		response.getWriter().write(output);
		
		
	}
	
	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
         Map paras = getParasMap(request); 
        	
		 String output = itemObj.updateItem(paras.get("hidItemIDSave").toString(), 
		                     paras.get("itemCode").toString(), 
		                     paras.get("itemCategory").toString(), 
		                     paras.get("itemName").toString(), 
		                     paras.get("itemBrand").toString(), 
		                     paras.get("itemDesc").toString(), 
		                     paras.get("itemPrice").toString()); 
		 
		response.getWriter().write(output);
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			Map paras = getParasMap(request); 
			
		    String output = itemObj.deleteItem(paras.get("itemID").toString()); 
		
		    response.getWriter().write(output); 
		
	}
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) 
	{ 
		Map<String, String> map = new HashMap<String, String>(); 
		try
		{ 
				Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
				String queryString = scanner.hasNext() ? 
						             scanner.useDelimiter("\\A").next() : ""; 
				
			    scanner.close(); 
			    
			    String[] params = queryString.split("&"); 
			    for (String param : params) 
			   { 
				
				String[] p = param.split("=");
				map.put(p[0], p[1]); 
			   } 
	 } 
	catch (Exception e) 
	 { 
	 } 
	return map; 
	}
	

}

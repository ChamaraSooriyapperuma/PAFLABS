<%@page import="com.Item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Item Management</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/items.js"></script>


</head>
<body>

<div class="jumbotron jumbotron-fluid">

<div class="container"><div class="row"><div class="col-10">
 
	<center><h1 style="font-size:380%;">ITEMS Management</h1></center>
	<br><br>
 
 	
<form id="formItem" name="formItem" method="post" action="item.jsp">

 		Item code: 
 		<input id="itemCode" name="itemCode" type="text" 
					 class="form-control form-control-sm">
					 
 		<br> Item category: 
 		<input id="itemCategory" name="itemCategory" type="text" 
 					class="form-control form-control-sm">
 					
 		 <br> Item name: 
 		<input id="itemName" name="itemName" type="text" 
 					class="form-control form-control-sm">
 					
  		<br> Item brand: 
 		<input id="itemBrand" name="itemBrand" type="text" 
 					class="form-control form-control-sm">
 					
 		<br> Item description: 
 		<input id="itemDesc" name="itemDesc" type="text" 
 					class="form-control form-control-sm">
 					
 		<br> Item price: 
		 <input id="itemPrice" name="itemPrice" type="text" 
 					class="form-control form-control-sm">	
 					
 		<br>
 		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
 		<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
</form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>

<br>
<div id="divItemsGrid">
 <%
 	Item itemObj = new Item(); 
 	out.print(itemObj.readItems()); 
 %>
</div>

</div> </div> </div> 

</body>
</html>   

 
<?php 

 require  "conn.php";
 //creating a query
//$user_name = $_POST["username"];
$user_name=$_POST["username"];

 $stmt = $conn->prepare("SELECT login.username,assign.vehicle_no FROM `login` INNER JOIN assign ON login.NIC_no=assign.owner_NIC_no WHERE login.username='$user_name'");
 
 //executing the query 
 $stmt->execute();
 
 //binding results to the query 
 $stmt->bind_result($username, $password);
 
 $products = array(); 
 
 //traversing through all the result 
 while($stmt->fetch()){
 $temp = array();
 $temp['username'] = $username; 
 $temp['password'] = $password; 
 array_push($products, $temp);
 }
 
 //displaying the result in json format 
echo json_encode($products);
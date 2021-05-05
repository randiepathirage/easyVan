 
<?php 

 require  "conn.php";
 //creating a query
//$user_name = $_POST["username"];


//$driver_NIC_no =  mysqli_query($conn,$abc);

 $stmt = $conn->prepare("SELECT amount,date,type,vehicle_no
 	FROM expense ");
 
 //executing the query 
 $stmt->execute();

 
 //binding results to the query 
 $stmt->bind_result($amount, $date,$type, $vehicle);
 
 $products = array(); 

 
 //traversing through all the result 
 while($stmt->fetch()){
 $temp = array();
 $temp['vehicle'] = $vehicle; 
 $temp['type'] = $type;
 $temp['date'] = $date;
 $temp['amount'] = $amount;


 array_push($products, $temp);
 }
 
 //displaying the result in json format 
echo json_encode($products);
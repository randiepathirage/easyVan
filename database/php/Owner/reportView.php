 
<?php 

 require  "conn.php";
 //creating a query
//$user_name = $_POST["username"];
//$user_name="owner";
$vehicle = $_POST["vehicle"];
$expType = $_POST["expType"];
$date1 = $_POST["date1"];
$date2 = $_POST["date2"];


if ($expType == "expense") {
	
	$stmt = $conn->prepare("SELECT `vehicle_no`,`type`,`amount`,`date`  FROM `expense` WHERE vehicle_no = '$vehicle' AND type = '$expType' AND date BETWEEN '$date1' AND '$date2' ");
  $stmt = $conn->prepare("SELECT `vehicle_no`,`type`,`amount`,`date`  FROM `expense` WHERE vehicle_no = '$vehicle' ");
  $stmt->execute();

 
 //binding results to the query 
 $stmt->bind_result($VehicleNo, $type, $amount,$date);
 
 $products = array(); 

 
 //traversing through all the result 
 while($stmt->fetch()){
 $temp = array();
 $temp['vehicleNo'] = $VehicleNo; 
 $temp['type'] = $type;
 $temp['amount'] = $amount; 
 $temp['date'] = $date;


 array_push($products, $temp);
 }
 
 //displaying the result in json format 
echo json_encode($products);
}

else if($expType =="Fuel"){

 $stmt = $conn->prepare("SELECT `vehicle_no`,`type`,`amount`,`date`  FROM `expense` WHERE vehicle_no = '$vehicle' AND type = '$expType' ");
  $stmt = $conn->prepare("SELECT `vehicle_no`,`type`,`amount`,`date`  FROM `expense` WHERE vehicle_no = '$vehicle' AND type = '$expType' AND date BETWEEN '$date1' AND '$date2'");
  $stmt->execute();

 
 //binding results to the query 
 $stmt->bind_result($VehicleNo, $type, $amount,$date);
 
 $products = array(); 

 
 //traversing through all the result 
 while($stmt->fetch()){
 $temp = array();
 $temp['vehicleNo'] = $VehicleNo; 
 $temp['type'] = $type;
 $temp['amount'] = $amount; 
 $temp['date'] = $date;


 array_push($products, $temp);
 }
 
 //displaying the result in json format 
echo json_encode($products);

}


/*$vehicle = "KK 2332";
$expType = "fuel";
*/
//$driver_NIC_no =  mysqli_query($conn,$abc);

 
 //executing the query 
 
?>
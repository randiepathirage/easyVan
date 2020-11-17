 
<?php 

 require  "conn.php";
 //creating a query
$user_name = $_POST["username"];
//$user_name="hasindu";

//$driver_NIC_no =  mysqli_query($conn,$abc);

 $stmt = $conn->prepare("SELECT vehicle.number,vehicle.no_of_seats_available
 	FROM `login` 
 	INNER JOIN vehicle ON vehicle.owner_NIC_no = login.NIC_no
 	WHERE login.username='$user_name' ");
 
 //executing the query 
 $stmt->execute();

 
 //binding results to the query 
 $stmt->bind_result($VehicleNo, $NoOfSeat);
 
 $products = array(); 

 
 //traversing through all the result 
 while($stmt->fetch()){
 $temp = array();
 $temp['vehicleNo'] = $VehicleNo; 
 $temp['NoOfSeatsAV'] = $NoOfSeat;
 /*$temp['licenceNo'] = $licenseNo;
 $temp['contactNO'] = $contactNO;
 $temp['email'] = $email;*/


 array_push($products, $temp);
 }
 
 //displaying the result in json format 
echo json_encode($products);
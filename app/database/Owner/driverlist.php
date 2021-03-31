 
<?php 

 require  "conn.php";
 //creating a query
$user_name = $_POST["username"];
//$user_name="dimuthu";

//$driver_NIC_no =  mysqli_query($conn,$abc);

 $stmt = $conn->prepare("SELECT user.first_name,assign.vehicle_no, parent_owner_driver.license_no, user.contact_no, login.email 
 	FROM `login` 
 	INNER JOIN assign ON login.NIC_no=assign.owner_NIC_no
 	INNER JOIN parent_owner_driver ON assign.driver_NIC_no = parent_owner_driver.NIC_no
 	INNER JOIN user ON user.NIC_no = assign.driver_NIC_no
 	WHERE login.username='$user_name' ");
 
 //executing the query 
 $stmt->execute();

 
 //binding results to the query 
 $stmt->bind_result($username, $vehicleNo,$licenseNo, $contactNO, $email);
 
 $products = array(); 

 
 //traversing through all the result 
 while($stmt->fetch()){
 $temp = array();
 $temp['username'] = $username; 
 $temp['vehicleNo'] = $vehicleNo;
 $temp['licenceNo'] = $licenseNo;
 $temp['contactNO'] = $contactNO;
 $temp['email'] = $email;


 array_push($products, $temp);
 }
 
 //displaying the result in json format 
echo json_encode($products);
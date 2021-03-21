 
<?php 

 require  "conn.php";
 //creating a query
//$user_name = $_POST["username"];


//$driver_NIC_no =  mysqli_query($conn,$abc);

stmt = $conn->prepare();
 
 //executing the query 
 $stmt->execute();

 
 //binding results to the query 
 $stmt->bind_result($username, $vehicleNo,$licenseNo, $contactNO, $email);
 
 $products = array(); */

 
 //traversing through all the result 
$vehicle = "KK 2134";
$type = "Van";
$date = "2020-03-21";
$amount = "20000";



 $temp = array();
 $temp['vehicle'] = $username; 
 $temp['type'] = $vehicleNo;
 $temp['date'] = $licenseNo;
 $temp['amount'] = $contactNO;


 array_push($products, $temp);

 
 //displaying the result in json format 
echo json_encode($products);
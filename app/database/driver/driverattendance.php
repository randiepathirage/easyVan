
 
<?php 

require  "conn.php";
//creating a query
$user_name=$_POST['username'];
//$user_name="pqr";

// genarate driver NIC using username>>>>>>>>>

$NIC = "SELECT NIC_no FROM login  WHERE username = '$user_name' ";
$query_NIC= mysqli_query($conn,$NIC);
$NIC_result = mysqli_fetch_assoc($query_NIC);
$Driver_NIC= $NIC_result['NIC_no'];
//>>>>>>>>>>>>

//Genarate vehicle number.........................
$VehicelNO =
"SELECT vehicle_no FROM assign WHERE driver_NIC_no='$Driver_NIC';";

$query_VehicelNO= mysqli_query($conn,
$VehicelNO);

$VN_result = mysqli_fetch_assoc($query_VehicelNO);

$Result_VehicelNO= $VN_result ['vehicle_no'];
//...............................................



$stmt = $conn->prepare("SELECT first_name,last_name FROM child WHERE vehicle_no = '$Result_VehicelNO' ");

//executing the query 
$stmt->execute();

//binding results to the query 
$stmt->bind_result($first_name,$last_name);

$products = array(); 

//traversing through all the result 
while($stmt->fetch()){
$temp = array();

$temp['fname'] =  $first_name; 
$temp['lname'] =  $last_name; 
array_push($products, $temp);
}

//displaying the result in json format 
echo json_encode($products);

?>
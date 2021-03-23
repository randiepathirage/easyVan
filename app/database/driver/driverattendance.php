
 
<?php 

require  "conn.php";
//creating a query
$user_name=$_POST['username'];
$user_name="Nimal";

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

//Genarate child details.........................

$stmt = $conn->prepare("SELECT child_no 
FROM child WHERE child_no IN (SELECT child_no FROM child_assign WHERE vehicle_no = '$Result_VehicelNO'); ");

//...........................................



//executing the query 
$stmt->execute();

//binding results to the query 
$stmt->bind_result($child_no);

$products = array(); 

//traversing through all the result 
while($stmt->fetch()){
$temp = array();

$temp['c_no'] =  $child_no; 

array_push($products, $temp);
}

//displaying the result in json format 
echo json_encode($products);

?>
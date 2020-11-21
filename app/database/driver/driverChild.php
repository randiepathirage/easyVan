
 
<?php 

require  "conn.php";
//creating a query
//$user_name = $_POST["username"];
//$user_name=$_POST["username"];

$stmt = $conn->prepare("SELECT child_no,first_name,last_name,grade,school,pickup_location,dropoff_location FROM child;");

//executing the query 
$stmt->execute();

//binding results to the query 
$stmt->bind_result($child_no, $first_name,$last_name,$grade,$school,$pickup_location,$dropoff_location);

$products = array(); 

//traversing through all the result 
while($stmt->fetch()){
$temp = array();
$temp['number'] = $child_no; 
$temp['fname'] =  $first_name; 
$temp['lname'] =  $last_name; 
$temp['grade'] =  $grade; 
$temp['school'] =  $school; 
$temp['pick_loc'] =  $pickup_location; 
$temp['dropoff_loc'] =  $dropoff_location; 
array_push($products, $temp);
}

//displaying the result in json format 
echo json_encode($products);

?>
 <?php
 require "conn.php";

 	$result = array();
 	$result['data'] = array();

 	$select= "SELECT child.child_no,child.parent_NIC_no,child.first_name,child.last_name,child.school,child.grade,child.pickup_location,child.vehicle_no,child.fees, user.first_name as parent_name FROM user JOIN child ON user.NIC_no = child.parent_NIC_no";
 	$responce = mysqli_query($conn,$select);

 	while($row = mysqli_fetch_array($responce))
 		{
 			$index['child_no']      = $row['0'];
 			$index['parent_NIC_no']    = $row['1'];
 			$index['first_name']   = $row['2'];
 			$index['last_name'] = $row['3'];
 			$index['school'] = $row['4'];
 			$index['grade'] = $row['5'];
 			$index['pickup_location'] = $row['6'];
 			$index['vehicle_no'] = $row['7'];
 			$index['fees'] = $row['8'];
 			$index['parent_name'] = $row['9'];

 			//pushing index array to data field
 			array_push($result['data'], $index);
 		}

 			$result["success"]="1";
 			echo json_encode($result);
 			mysqli_close($connection);

  ?>
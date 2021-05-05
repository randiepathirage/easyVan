 <?php
 require "conn.php";

 	$result = array();
 	$result['data'] = array();
 	$select= "SELECT user.*, login.username,login.email FROM user JOIN login ON user.NIC_no = login.NIC_no JOIN parent_owner_driver ON user.NIC_no = parent_owner_driver.NIC_no where parent_owner_driver.parent_flag=1";

 	$responce = mysqli_query($conn,$select);

 	while($row = mysqli_fetch_array($responce))
 		{
 			$index['NIC_no']      = $row['0'];
 			$index['contact_no']    = $row['1'];
 			$index['last_name']   = $row['2'];
 			$index['first_name'] = $row['3'];
 			$index['address'] = $row['4'];
 			$index['username'] = $row['5'];
 			$index['email'] = $row['6'];

 			// push index array to data field
 			array_push($result['data'], $index);
 		}

 			$result["success"]="1";
 			echo json_encode($result);
 			mysqli_close($connection);

  ?>
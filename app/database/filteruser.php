 <?php
 require "conn.php";

 	$result = array();
 	$result['data'] = array();
 	$select= "SELECT first_name,last_name FROM user INNER JOIN parent_owner_driver ON parent_owner_driver.NIC_no = user.NIC_no where owner_flag=1";
 	$response = mysqli_query($conn,$select);

 	while($row = mysqli_fetch_array($response))
 		{
 			$index['NIC_no']      = $row['0'];
 			$index['contact_no']    = $row['1'];
 			$index['last_name']   = $row['2'];
 			$index['first_name'] = $row['3'];
 			$index['address'] = $row['4'];

 			array_push($result['data'], $index);
 		}

 			$result["success"]="1";
 			echo json_encode($result);
 			mysqli_close($connection);

  ?>
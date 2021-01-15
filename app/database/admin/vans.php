 <?php
 require "conn.php";

 	$result = array();
 	$result['data'] = array();
 	$select= "SELECT vehicle.number,vehicle.model,assign.driver_NIC_no,assign.owner_NIC_no,vehicle.total_no_of_seats,vehicle.no_of_seats_available,vehicle.start_location,insurance.insurance_no,license.license_no FROM vehicle JOIN assign ON vehicle.number = assign.vehicle_no JOIN insurance ON vehicle.number = insurance.vehicle_no JOIN license ON vehicle.number = license.vehicle_no";
 	$responce = mysqli_query($conn,$select);

 	while($row = mysqli_fetch_array($responce))
 		{
 			$index['number']      = $row['0'];
 			$index['model']    = $row['1'];
 			$index['driver_NIC_no']   = $row['2'];
 			$index['owner_NIC_no'] = $row['3'];
 			$index['total_no_of_seats'] = $row['4'];
 			$index['no_of_seats_available'] = $row['5'];
 			$index['start_location'] = $row['6'];
 			$index['insurance_no'] = $row['7'];
 			$index['license_no'] = $row['8'];

 			array_push($result['data'], $index);
 		}

 			$result["success"]="1";
 			echo json_encode($result);
 			mysqli_close($conn);

  ?>
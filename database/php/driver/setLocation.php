<?php

	//randie..........................................................
    require  "conn.php";

    $username=$_POST["username"];
    $longitude=$_POST["longitude"];
    $latitude=$_POST["latitude"];

    $day=date("Y-m-d");
    date_default_timezone_set("Asia/Colombo");
    $time=date("h:i:sa");


    //get driver nic
    $query_driverID="SELECT NIC_no FROM login WHERE username='$username'";
    $result_driverID=mysqli_query($conn,$query_driverID);

    $nic="";

    while($row=mysqli_fetch_assoc($result_driverID)){
        
        $nic=$row['NIC_no'];
    }

    //get vehicle no
    $query_vehicleNo="SELECT vehicle_no FROM assign WHERE driver_NIC_no='$nic'";
    $result_vehicleNo=mysqli_query($conn,$query_vehicleNo);

    $no="";

    while($row=mysqli_fetch_assoc($result_vehicleNo)){
        
        $no=$row['vehicle_no'];
    }


    $query="INSERT INTO location(vehicle_no,longitude,latitude,time,date) VALUES ('$no','$longitude','$latitude','$time','$day')";
    $result=mysqli_query($conn,$query);


    if($result){

        echo 'Location started';
        
	}else{

		$query_update="UPDATE location SET longitude='$longitude',latitude='$latitude',time='$time',date='$day' WHERE vehicle_no='$no'";
  		$result_update=mysqli_query($conn,$query_update);

  		if($result_update){
  			echo 'Location started';
  		}else{
       		echo "Error".$query_update."<br>".$conn->error; 			
  		}

	}


 	mysqli_close($conn)




?>
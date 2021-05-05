<?php

 require "conn.php";


  //$licenseNo=$_POST['license_no'];
  $vehicleNo=$_POST['vehicleNo'];
  $username=$_POST['username'];

  //get driver nic
  $nic_no="";

  $query="SELECT NIC_no FROM login WHERE username='$username'";
  $result=mysqli_query($conn,$query);

  while($row=mysqli_fetch_assoc($result)){
        
        $nic_no=$row['NIC_no'];

  }

  $query_check="SELECT * FROM assign WHERE vehicle_no='$vehicleNo'";
  $Query_result= mysqli_query($conn,$query_check);

  if(mysqli_num_rows($Query_result)>0){

        echo "has a driver";
    }else{
      $query_assign="UPDATE assign SET vehicle_no='$vehicleNo' WHERE driver_NIC_no='$nic_no' ";

          if($conn->query($query_assign)===TRUE ){
            echo "Assign Successful";
                
        }
        else{
            echo "Error".$query_login."<br>".$conn->error;

        }
    }




 mysqli_close($conn);

?>
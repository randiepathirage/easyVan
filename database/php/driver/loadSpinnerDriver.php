<?php
    //randie................................................................
    require  "conn.php";

    $userName=$_GET['userName'];
    //$userName='driver1';

    //get driver id
    $query_ID="SELECT NIC_no FROM login WHERE username='$userName'";
    $result_ID=mysqli_query($conn,$query_ID);

    $nic="";

    $row=mysqli_fetch_assoc($result_ID);
    $nic=$row['NIC_no'];

    //get vehicle number
    $query_no="SELECT vehicle_no FROM assign WHERE driver_NIC_no='$nic'";
    $result_no=mysqli_query($conn,$query_no);

    $no="";

    $row=mysqli_fetch_assoc($result_no);
    $no=$row['vehicle_no'];
    

    $query="SELECT first_name FROM child WHERE vehicle_no='$no'";
    $result=mysqli_query($conn,$query);


    if($result){
        $return_arr['children']=array();
        while($row=mysqli_fetch_assoc($result))
        {
        	array_push($return_arr['children'],array('child_name'=>$row['first_name']));
        }

        echo json_encode($return_arr);
    }
    else{
        echo "Error".$query_assign."<br>".$conn->error;
    }


  ?>
<?php
    //randie................................................................
    require  "conn.php";
  
    $child_no=$_POST['childNo'];
    $nic=$_POST['nic'];
    $firstname=$_POST['firstName'];
    $lastname=$_POST['lastName'];
    $school=$_POST['school'];
    $grade=$_POST['grade'];
    $pick=$_POST['pick'];
    $drop=$_POST['drop'];
    $owner_NIC_no=$_POST['ownerID'];
    $vehicle_no=$_POST['vehicleNo'];


    $status='pending';
    //status =0 -->not assigned
    //status =1-->assinged 


    $day=date("Y-m-d");
    date_default_timezone_set("Asia/Colombo");
    $time=date("h:i:sa");

 /* 
    $nic="345p";
    $firstname="malit";
    $lastname="fernando";
    $school="abc collage";
    $grade="4";
    $pick="wrre";
    $drop="rerer";
    $vehicle_no="CBF-7375";
    $owner_NIC_no="34352o";*/



        $query_insert="INSERT INTO child(parent_NIC_no,grade,school,first_name,last_name,pickup_location,dropoff_location) VALUES ('$nic','$grade','$school','$firstname','$lastname','$pick','$drop')";
        $result_insert = mysqli_query($conn,$query_insert);

        $last_id=$conn->insert_id;


        $query_assign="INSERT INTO request(status,parent_NIC_no,child_no,vehicle_no,owner_NIC_no,date,time) VALUES ('$status','$nic','$last_id','$vehicle_no','$owner_NIC_no','$day','$time')";
        $result_assign=mysqli_query($conn,$query_assign);


        if($result_insert){
            if($result_assign){
                echo "request sent";
         }
         else{
            echo "Error".$query_assign."<br>".$conn->error;
        }
         
        }else{
            echo "Error".$query_insert."<br>".$conn->error;
        }

    $conn->close();

?>
<?php
    //randie................................................................
    require  "conn.php";
  
    $child_no=$_POST['childNo'];
    $nicv=$_POST['nic'];
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
    $pick="wrfvgbhre";
    $drop="homagama";
    $vehicle_no="CBF-7375";
    $owner_NIC_no="34352o";
    $child_no="4";*/

    $query_check="SELECT * FROM request WHERE child_no='$child_no' AND status='pending'";
    $result_check=mysqli_query($conn,$query_check);

    if(mysqli_num_rows($result_check)>0){
        echo 'you have already requested a school van';
    }else{

        $query_update="UPDATE child SET grade='$grade',school='$school',pickup_location='$pick',dropoff_location='$drop' WHERE child_no=$child_no";
        $result_update = mysqli_query($conn,$query_update);


        $query_assign="INSERT INTO request(status,parent_NIC_no,child_no,vehicle_no,owner_NIC_no,date,time) VALUES ('$status','$nicv','$child_no','$vehicle_no','$owner_NIC_no','$day','$time')";
        $result_assign=mysqli_query($conn,$query_assign);


        if($result_update){
            if($result_assign){
                echo "request sent";
            }
            else{
                echo "Error".$query_assign."<br>".$conn->error;
            }
             
         }
         else{
             echo "Error".$query_insert."<br>".$conn->error;
         }
    }

   

    $conn->close();

?>

<?php
    //randie................................................................
    require  "conn.php";
  
    $child_no=$_POST['childNo'];
    $parentUsername=$_POST['parentUsername'];
    $firstname=$_POST['firstName'];
    $lastname=$_POST['lastName'];
    $school=$_POST['school'];
    $grade=$_POST['grade'];
    $pick=$_POST['pick'];
    $drop=$_POST['drop'];
    $vehicle_no=$_POST['vehicleNo'];


    $status='pending';
    //status =0 -->not assigned
    //status =1-->assinged 


    $day=date("Y-m-d");
    date_default_timezone_set("Asia/Colombo");
    $time=date("h:i:sa");

/*
    $child_no='2';
    $parentUsername="randie";
    $firstname="malit";
    $lastname="fernando";
    $school="abc collage";
    $grade="4";
    $pick="wrre";
    $drop="rerer";
    $vehicle_no="CBF-7375";*/


    //check if thischild already requested a van and pending
    $query_check="SELECT * FROM request WHERE child_no='$child_no' AND status='pending'";
    $result_check=mysqli_query($conn,$query_check);

    if(mysqli_num_rows($result_check)>0){
        echo 'you have already requested a school van';
    }else{

        $query_update="UPDATE child SET grade='$grade',school='$school',pickup_location='$pick',dropoff_location='$drop' WHERE child_no='$child_no'";
        $result_update = mysqli_query($conn,$query_update);


//get ownerid and parent id 
        $query_parentID="SELECT NIC_no FROM login WHERE username='$parentUsername'";
        $result_parentID=mysqli_query($conn,$query_parentID);

        $nic="";

        while($row=mysqli_fetch_assoc($result_parentID)){
        
            $nic=$row['NIC_no'];
        }


        $query_ownerID="SELECT owner_NIC_no FROM vehicle WHERE number='$vehicle_no'";
        $result_ownerID=mysqli_query($conn,$query_ownerID);

        $owner_NIC_no="";

        while($row=mysqli_fetch_assoc($result_ownerID)){
        
            $owner_NIC_no=$row['owner_NIC_no'];
        }


        $query_assign="INSERT INTO request(status,parent_NIC_no,child_no,vehicle_no,owner_NIC_no,date,time) VALUES ('$status','$nic','$child_no','$vehicle_no','$owner_NIC_no','$day','$time')";
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
             echo "Error".$query_update."<br>".$conn->error;
         }
    }

    $conn->close();

?>

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
    $parentUsername="randie";
    $firstname="malit";
    $lastname="fernando";
    $school="abc collage";
    $grade="4";
    $pick="wrre";
    $drop="rerer";
    $vehicle_no="CBF-7375";*/


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


        //check if this child is already regitered in the system
        //one parent cant have two children from same frst name and last name
        $query_check="SELECT * FROM child WHERE first_name='$firstname' AND last_name='$lastname'AND parent_NIC_no='$nic'";
        $result_check=mysqli_query($conn,$query_check);

        if(mysqli_num_rows($result_check)>0){
            echo "This child is already registered in the system";
        }else{


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
        }
    $conn->close();

?>
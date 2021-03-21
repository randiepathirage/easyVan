<?php
    //randie................................................................
    require  "conn.php";

    $parentUsername=$_GET['parentUsername'];
    //$parentUsername='randie';

    $query_ID="SELECT NIC_no FROM login WHERE username='$parentUsername'";
    $result_ID=mysqli_query($conn,$query_ID);

    $nic="";

    while($row=mysqli_fetch_assoc($result_ID)){
        
        $nic=$row['NIC_no'];
    }


    $query="SELECT first_name FROM child WHERE parent_NIC_no='$nic'";
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
        echo "Error".$query."<br>".$conn->error;
    }


  ?>
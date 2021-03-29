<?php
   
    require  "conn.php";

    $parentUsername=$_GET['parentUsername'];
    //$parentUsername='owner1';

    $query_ID="SELECT NIC_no FROM login WHERE username='$parentUsername'";
    $result_ID=mysqli_query($conn,$query_ID);

    //$nic="972941999";

    while($row=mysqli_fetch_assoc($result_ID)){
        
        $nic=$row['NIC_no'];
    }


    $query="SELECT number FROM vehicle WHERE owner_NIC_no='$nic'";
    $result=mysqli_query($conn,$query);


    if($result){
        $return_arr['vehicle']=array();
        while($row=mysqli_fetch_assoc($result))
        {
        	array_push($return_arr['vehicle'],array('number'=>$row['number']));
        }

        echo json_encode($return_arr);
    }
    else{
        echo "Error".$query_assign."<br>".$conn->error;
    }


  ?>
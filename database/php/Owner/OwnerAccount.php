

<?php 
  
    require  "conn.php";

  $user_name=$_POST["username"];
    //$user_name="hasindu";



    $user = array();

  
    $stmt = "SELECT user.NIC_no,user.contact_no,login.email ,user.address
            FROM `user`     
            INNER JOIN login ON user.NIC_no=login.NIC_no 
            WHERE login.username='$user_name'";


    $stmt_exe=mysqli_query($conn,$stmt);

   $result=array();
   $result['data'] = array();


    while($row=mysqli_fetch_assoc($stmt_exe)){

        $index['NIC_no']= $row['NIC_no'];
        $index['contact_no']= $row['contact_no'];
        $index['email'] = $row['email'];
        $index['address'] = $row['address'];

    

        array_push($result['data'], $index);
    }

    echo json_encode($result);

   
    

    ?>
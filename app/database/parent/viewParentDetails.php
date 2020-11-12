

<?php 
    //randie...............................................................
    require  "conn.php";

    $user_name=$_POST["username"];
    //$user_name="randie";

    $user = array();

    //access user table
    //$stmt = "SELECT user.NIC_no , user.contact_no , user.address,login.email  FROM user INNER JOIN login ON user.NIC_no= login.NIC_no WHERE login.username='$user_name')";//creating a query
    $stmt = "SELECT user.NIC_no,user.contact_no,user.address,login.email FROM `user` INNER JOIN login ON user.NIC_no=login.NIC_no WHERE login.username='$user_name'";//creating a query

    //SELECT first_name,last_name FROM user INNER JOIN parent_owner_driver ON parent_owner_driver.NIC_no = user.NIC_no where owner_flag=1";

    $stmt_exe=mysqli_query($conn,$stmt);//executing the query

   $result=array();
   $result['data'] = array();

    //traversing through all the result 
    while($row=mysqli_fetch_assoc($stmt_exe)){

        $index['NIC_no']= $row['NIC_no'];
        $index['contact_no']= $row['contact_no'];
        $index['address'] = $row['address'];
        $index['email'] = $row['email'];

      /*  $temp = array();
        $temp['NIC_no'] = $NIC_no; 
        $temp['contact_no'] = $contact_no; 
        $temp['last_name'] = $last_name; 
        $temp['first_name'] = $first_name; 
        $temp['address'] = $address; 
        array_push($user, $temp);*/

        array_push($result['data'], $index);
    }

    echo json_encode($result);

    //access login table
  /*  $stmt_email = $conn->prepare("SELECT email  FROM login WHERE username='$user_name'");//creating a query
    $stmt_email->execute();//executing the query
    $stmt_email->bind_result($email);//binding results to the query  
    

    while($stmt_email->fetch()){

        $temp = array();
        $temp['email'] = $email; 
     
        array_push($user, $temp);
        }*/
    
    //displaying the result in json format 
    

    ?>
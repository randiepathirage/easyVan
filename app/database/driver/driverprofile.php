
<?php 

    require  "conn.php";

    $stmt = $conn->prepare("SELECT NIC_no,first_name,middle_name,last_name,contact_no,address FROM user WHERE NIC_no = '96';");

    $stmt->execute();

    $stmt->bind_result($NIC_no, $first_name, $middle_name, $last_name,$contact_no,$address);

    $products = array(); 

    while($stmt->fetch()){
        $temp = array();
        $temp['nic'] = $NIC_no; 
        $temp['fname'] =  $first_name; 
        $temp['mname'] = $middle_name;
        $temp['lname'] =  $last_name;  
        $temp['contact'] =  $contact_no; 
        $temp['address'] =  $address; 
       
        array_push($products, $temp);
    }

    echo json_encode($products);

?>
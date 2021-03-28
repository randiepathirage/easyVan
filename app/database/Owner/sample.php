<?php
 require  "conn.php";



$query_assign = "INSERT INTO school (driver_NIC_no, owner_NIC_no) VALUES ('121212','123456789')";
if($conn->query($query_assign) ==TRUE){
	echo "Insert Successfull";

}
else {
	echo "Fail";
	# code...
}
	

?>
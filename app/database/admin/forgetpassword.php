<?php
    require 'conn.php';
    require 'PHPMailer/PHPMailerAutoload.php';
    require 'PHPMailer/class.phpmailer.php';
    require 'PHPMailer/class.smtp.php';
    require 'admin.php';

    $nic_no = $_POST["nic_no"];
    $email = $_POST["e_mail"];
    //$nic_no = "555";
    //$email = "mruv98@gmail.com";


    $sql = "SELECT password FROM login where email LIKE '$email' AND NIC_no LIKE '$nic_no'";

    $result = mysqli_query($conn,$sql);
    $response = array();

   if(mysqli_num_rows($result)>0)
   {
        $row = mysqli_fetch_assoc($result);

        $mail = new PHPMailer;

        //$mail->SMTPDebug = 3;                               // Enable verbose debug output

        $mail->isSMTP();                                      // Set mailer to use SMTP
        $mail->Host = 'smtp.gmail.com';  // Specify main and backup SMTP servers
        $mail->SMTPAuth = true;                               // Enable SMTP authentication
        $mail->Username = $adminemail;                 // SMTP username
        $mail->Password = $adminpassword;                           // SMTP password
        $mail->SMTPSecure = 'tls';                            // Enable TLS encryption, `ssl` also accepted
        $mail->Port = 587;                                    // TCP port to connect to

        $mail->setFrom( $adminemail, 'EZvan');
        $mail->addAddress($email);     // Add a recipient
        $mail->addReplyTo( $adminemail, 'EZvan');


        $mail->Subject = 'Forgot Password for EZvan';
        $mail->Body    = "Your Account Password is : " .$row["password"];


        if(!$mail->send()) {
            echo 'Message could not be sent.';
            echo 'Mailer Error: ' . $mail->ErrorInfo;
        } else {
            echo 'SUCCESS';
        }
    }
    else
    {
        echo "Failed";
    }
    mysqli_close($conn);

?>
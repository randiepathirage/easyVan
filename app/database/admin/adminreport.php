<?php 
  
    require  "conn.php";
    require "fpdf/fpdf.php";
    require 'PHPMailer/PHPMailer.php';
    require 'PHPMailer/SMTP.php';
    require 'PHPMailer/Exception.php';
    require 'admin.php';

    use PHPMailer\PHPMailer\PHPMailer;
    use PHPMailer\PHPMailer\SMTP;
    use PHPMailer\PHPMailer\Exception;


    $username=$_POST["owner"];
    $date1=$_POST["date1"];
    $date2=$_POST["date2"];

    /*$username="ruvidu";
    $date1="2021-03-01";
    $date2="2021-03-31";*/



    $owner = "SELECT * FROM login WHERE username='$username'";
    $data = mysqli_query($conn,$owner);

    $oemail= mysqli_query($conn,"SELECT email FROM login WHERE username = '$username'");
    $d6 = mysqli_fetch_assoc($oemail);
    $email = $d6['email'];

    $nic_no= mysqli_query($conn,"SELECT NIC_no FROM login WHERE username = '$username'");
    $d1 = mysqli_fetch_assoc($nic_no);
    $nic = $d1['NIC_no'];
    
    $vehicle_no = mysqli_query($conn,"SELECT vehicle_no FROM assign WHERE owner_NIC_no = '$nic'");
    $d2 = mysqli_fetch_assoc($vehicle_no);
    $vehicle = $d2['vehicle_no'];

    $exp = mysqli_query($conn,"SELECT SUM(amount) FROM expense WHERE vehicle_no='$vehicle' AND date BETWEEN '$date1' AND '$date2'");
    if($exp){
            $d3 = mysqli_fetch_assoc($exp);
            $expense = $d3['SUM(amount)'];
        }
     else
        $expense = '0';    

    $fee = mysqli_query($conn,"SELECT SUM(fee.amount) FROM fee JOIN child on fee.child_no = child.child_no WHERE child.vehicle_no = '$vehicle' AND fee.paid_date BETWEEN '$date1' AND '$date2'");
    if($fee)
    {
            $d4 = mysqli_fetch_assoc($fee);
            $fees = $d4['SUM(fee.amount)'];
    }
    else
        $fees = '0'; 
          
    $income = $fees-$expense;

     class PDF extends FPDF
    {
    // Page header
    function Header()
    {
        // Logo
        $this->Image('logo.jpg',90,10,30);
        $this->SetFont('Arial','B',18);
        // Move to the right
        $this->Cell(65);
        // Line break
        $this->Ln(20);
        $this->Cell(65);
        $this->Cell(65,40,'Vehicle Expense And Income Report',0,0,'C');
        $this->Cell(10);
        $this->Ln(40);
    }


    }
 
    $pdf = new PDF('p','mm','a4');
    $pdf->AddPage();
    $pdf->setTitle("EasyVan");
    $pdf->SetFont('arial','i','10');
    $pdf->cell('80','10','Time Period : '.$date1 .' to '. $date2,'0','0','C');
    $pdf->Ln(15);
    $pdf->SetFont('arial','','10');

    $pdf->Cell(20);
    $pdf->cell('40','10','Owner Username       :    ','0','1','R');
    $pdf->Cell(20);
    $pdf->cell('40','10','Vehicle              :    ','0','1','R');
    $pdf->Cell(20);
    $pdf->cell('40','10','Expenses             :    ','0','1','R');
    $pdf->Cell(20);
    $pdf->cell('40','10','Income from Fees     :    ','0','1','R');
    $pdf->Cell(20);
    $pdf->cell('40','10','Total Income         :    ','0','1','R');
    $pdf->SetXY(80,85);

    while($row = mysqli_fetch_assoc($data))
    {
        $pdf->cell('40','10',$username,'0','1','L');
        $pdf->Cell(70);
        $pdf->cell('40','10',$vehicle,'0','1','L');
        $pdf->Cell(70);
        $pdf->cell('40','10','Rs.'.$expense,'0','1','L');
        $pdf->Cell(70);
        $pdf->cell('40','10','Rs.'.$fees,'0','1','L');
        $pdf->Cell(70);
        $pdf->cell('40','10','Rs.'.$income,'0','1','L');
    }
        $attachment= $pdf->Output('', 'S');
    
        $mail = new PHPMailer();
        $mail->isSMTP();                                      // Set 
        $mail->Host = 'smtp.gmail.com';  // Specify main and backup 
        $mail->SMTPAuth = true;                               // 
        $mail->Username = $adminemail;                 // SMTP 
        $mail->Password = $adminpassword;                           
        $mail->SMTPSecure = 'tls';                            // 
        $mail->Port = "587";                                    // 

        $mail->setFrom( $adminemail, 'EasyVan');
        $mail->addAddress($email);     // Add a recipient
        $mail->addReplyTo( $adminemail, 'EasyVan');

        $mail->addStringAttachment($attachment,'report.pdf');

        $mail->Subject = 'Income and Expense Report';
        $mail->isHTML(true);
        $mail->Body = 'PDF has been attached.';


        if(!$mail->send()) {
            echo 'Message could not be sent.';

        } else {
            echo 'send';
        }

        $mail->smtpClose();

   
   
    mysqli_close($conn);
    
?>
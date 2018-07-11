<?php
require 'DBConn.php';
$Gebruiker_id = $_POST["Gebruiker_id"];
$Quiz_Vragen = $_POST["Quiz_Vragen"];
$Score = $_POST["Score"];

$statement = mysqli_prepare($con, "INSERT INTO Score (Gebruiker_id, Quiz_Vragen, Score) VALUES (?, ?, ?)");
mysqli_stmt_bind_param($statement, "siss", $Gebruiker_id, $Quiz_Vragen, $Score);
mysqli_stmt_execute($statement);

$response = array();
if(mysqli_stmt_execute($statement)){
    $response["success"] = true;  
}else{
    $response["success"] = false;  
}


echo json_encode($response);
?>
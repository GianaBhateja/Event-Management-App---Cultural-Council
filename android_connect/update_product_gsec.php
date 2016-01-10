<?php
 
/*
 * Following code will update a product information
 * A product is identified by product id (pid)
 */
 
// array for JSON response
$response = array();
 
// check for required fields
if (isset($_POST['pid']) && isset($_POST['status'])) {
 
 
    $pid = $_POST['pid'];
	$e_status = $_POST['status'];
    //$price = $_POST['price'];
   // $description = $_POST['description'];
 
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
	$ev_status='C';
    // connecting to db
    $db = new DB_CONNECT();
	$con =mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
    // mysql update row with matched pid
    $result = mysqli_query($con,"UPDATE events SET e_status = '$e_status' WHERE pid = $pid");
 
    // check if row inserted or not
    if ($result) {
        // successfully updated
        $response["success"] = 1;
        $response["message"] = "Product successfully updated.";
 
        // echoing JSON response
        echo json_encode($response);
    } else {
 
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}
?>
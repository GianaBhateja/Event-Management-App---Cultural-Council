<?php
 
/*
 * Following code will get single product details
 * A product is identified by product id (pid)
 */
 
// array for JSON response
$response = array();
 
// include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();
$con =mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
// check for post data
if (isset($_GET["pid"])) {
    $pid = $_GET['pid'];
	
    // get a product from products table
    $result = mysqli_query($con,"SELECT *FROM clubs WHERE pid = $pid");
 
    if (!empty($result)) {
        // check for empty result
        if (mysqli_num_rows($result) > 0) {
 
            $result = mysqli_fetch_assoc($result);
 
            $product = array();
            $product["pid"] = $result["pid"];
            $product["name"] = $result["name"];
            $product["coordinator"] = $row["coordinator"];
			$product["contact_no"] = $row["contact_no"];
			$product["members"] = $row["members"];
			$product["description"] = $row["description"];
				
            // success
            $response["success"] = 1;
 
            // user node
            $response["product"] = array();
 
            array_push($response["product"], $product);
 
            // echoing JSON response
            echo json_encode($response);
        } else {
            // no product found
            $response["success"] = 0;
            $response["message"] = "No product found";
 
            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no product found
        $response["success"] = 0;
        $response["message"] = "No product found";
 
        // echo no users JSON
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}
?>
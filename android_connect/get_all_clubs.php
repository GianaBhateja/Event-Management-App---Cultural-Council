<?php
 
/*
 * Following code will list all the products
 */
 
// array for JSON response
$response = array();
 
// include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();
 $con =mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
// get all products from products table
$result = mysqli_query($con,"SELECT * FROM clubs");
 
// check for empty result
if (mysqli_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["products"] = array();
 
    while ($row = mysqli_fetch_assoc($result)) {
        // temp user array
        $product = array();
        $product["pid"] = $row["pid"];
        $product["name"] = $row["name"];
        $product["coordinator"] = $row["coordinator"];
        $product["contact_no"] = $row["contact_no"];
        $product["members"] = $row["members"];
        $product["description"] = $row["description"];
	
 
        // push single product into final response array
        array_push($response["products"], $product);
    }
    // success
    $response["success"] = 1;
 
    // echoing JSON response
    echo json_encode($response);
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "No products found";
 
    // echo no users JSON
    echo json_encode($response);
}
?>
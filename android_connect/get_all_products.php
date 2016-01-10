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
 $fc_status = 'C';
// get all products from products table
$result = mysqli_query($con,"SELECT * FROM events WHERE e_status= '$fc_status' ");
 
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
        $product["Date"] = $row["e_date"];
        $product["Venue"] = $row["venue"];
        $product["Club"] = $row["club"];
		$product["Starting_time"] = $row["startingTime"];
        $product["Ending_time"] = $row["endingTime"];
        $product["coordinator"] = $row["coordinator"];
        $product["sub_events"] = $row["subevent"];
        $product["Description"] = $row["description"];
		$product["Inventory"] = $row["inventory"];
        $product["Budget"] = $row["budget"];
        $product["e_status"] = $row["e_status"];
 
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
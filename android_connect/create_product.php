<?php
 
/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */
 
// array for JSON response
$response = array();
 
// check for required fields
if (isset($_POST['name']) && isset($_POST['Date']) && isset($_POST['Venue']) && isset($_POST['Starting_time']) && isset($_POST['Ending_time']) && isset($_POST['Club']) && isset($_POST['Coordinator']) && isset($_POST['sub_events']) && isset($_POST['Description']) && isset($_POST['Inventory']) && isset($_POST['Budget']) ) {
 
    $name = $_POST['name'];
    $e_date = $_POST['Date'];
	$venue = $_POST['Venue'];
    $starting_time = $_POST['Starting_time'];
    $ending_time = $_POST['Ending_time'];
	$club = $_POST['Club'];
    $coordinator = $_POST['Coordinator'];
    $sub_event = $_POST['sub_events'];
    $description = $_POST['Description'];
	$inventory = $_POST['Inventory'];
    $budget = $_POST['Budget'];
    
 
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
 
    // connecting to db
    $db = new DB_CONNECT();
	$con =mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
    // mysql inserting a new row
    $result = mysqli_query($con,"INSERT INTO events(name, e_date, venue, startingTime, endingTime, club, coordinator, subevent, description, inventory, budget) VALUES('$name', '$e_date', '$venue', '$starting_time', '$ending_time', '$club', '$coordinator', '$sub_event',  '$description', '$inventory', '$budget')");
 
    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Product successfully created.";
 
        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
 
        // echoing JSON response
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
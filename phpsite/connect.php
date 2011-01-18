<?php

	requires('../../connectionInfo.php');


	function openConnection()
	{		
		// We connect to the database.
		$conn = mysql_connect($dbhost, $dbuser, $dbpass) or die ('Error connecting to mysql!');
		
		// Selection of the database.
		mysql_select_db($dbname);		
	}
	
	function closeConnection()
	{
		mysql_close($conn) or die ("ERROR 99: There was an error while trying to close mysql connection.");	
	}
	

?>
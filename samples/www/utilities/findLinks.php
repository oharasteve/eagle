<?php
   include("../db.php");

   $out = "findLinks.csv";
    header("Content-type: application/octet-stream");
    header("Content-Disposition: attachment; filename=\"" . $out . "\"");
    header("Content-Transfer-Encoding: binary");
    if (strpos($_SERVER['HTTP_USER_AGENT'], 'MSIE'))
    {
        // IE cannot download from sessions without a cache
        header('Cache-Control: public');
    }

   $query = "SELECT * FROM People;";
   $results = mysql_query($query);
   while ($row = mysql_fetch_array($results))
   {
      $key = $row["KeyName"];
      $mother = $row["MotherKey"];
      $father = $row["FatherKey"];
	  echo "Person" . ',' . $key . ',' . $mother . ',' . $father . "\n";
   }
   
   $query = "SELECT * FROM Details;";
   $results = mysql_query($query);
   while ($row = mysql_fetch_array($results))
   {
      $key = $row["KeyName"];
	  echo "Detail" . ',' . $key . "\n";
   }
   
   $query = "SELECT * FROM Marriages;";
   $results = mysql_query($query);
   while ($row = mysql_fetch_array($results))
   {
      $husband = $row["HusbandKey"];
	  $wife = $row["WifeKey"];
	  echo "Marriage" . ',' . $husband . ',' . $wife . "\n";
   }
   
   $query = "SELECT * FROM Photos;";
   $results = mysql_query($query);
   while ($row = mysql_fetch_array($results))
   {
      $dir = $row["Directory"];
      $photo = $row["Photo"];
      $suff = $row["Suffix"];
      $orig = $row["Original"];
	  echo "Photo" . ',' . $dir . ',' . $photo . ',' . $suff . ',' . $orig . "\n";
   }
   
   $query = "SELECT * FROM PeoplePhotos;";
   $results = mysql_query($query);
   while ($row = mysql_fetch_array($results))
   {
      $photo = $row["Photo"];
      $key = $row["KeyName"];
	  echo "PeoplePhoto" . ',' . $photo . ',' . $key . "\n";
   }
   
   $query = "SELECT * FROM Videos;";
   $results = mysql_query($query);
   while ($row = mysql_fetch_array($results))
   {
      $dir = $row["Directory"];
	  $video = $row["Video"];
      $photo = $row["Photo"];
	  echo "Video" . ',' . $dir . ',' . $video . ',' . $photo . "\n";
   }

   db_close();
?>

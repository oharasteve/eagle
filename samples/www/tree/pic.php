<html>

<?php
   # Connect to database
   include("../db.php");

   $pic = $_REQUEST["Pic"];
   $key = $_REQUEST["Key"];

   if ($key and $key != "")
   {
      $query = "SELECT * FROM PeoplePhotos, Photos WHERE PeoplePhotos.KeyName='" . $key .
                   "' AND Photos.Photo='" . $pic . "' AND PeoplePhotos.Photo='" . $pic . "'";
   }
   else
   {
      $query = "SELECT * FROM Photos WHERE Photo='" . $pic . "'";
   }
   $results = mysql_query($query);
   $photo = mysql_fetch_array($results);
   $dir = $photo["Directory"];
   $fname = $photo["Photo"] . $photo["Suffix"];

   $caption = $photo["Caption"];
   $year = $photo["Year"];
   if ($year and $year > 0)
   {
      if ($caption and $caption != "")
      {
         $caption = $caption . " (" . $year . ")";
      }
      else
      {
         $caption = "Year: " . $year;
      }
   }

   # DISABLED, Oct 10, 2005. Use $dir, not $dir2
   # make sure there is no / in the directory
   # $pos = strpos($dir, "/");
   # if ($pos and $pos > 0)
   # {
   #    $dir2 = substr($dir, 0, $pos);
   # }
   # else
   # {
   #    $dir2 = $dir;
   # }

   $ttl = $photo["Position"];
   if ($ttl and $ttl != "")
   {
      $ttl = " title=\"" . $key . " position: " . $ttl . "\"";
   }
   else
   {
      $ttl = $key;
   }

   print "<head>\n";
   print "<title>" . $pic . "</title>\n";
   print "</head>\n\n";

   print "<body bgcolor=\"#f0f0f0\">\n";
   print "<table border=5 cellpadding=10><tr><td align=center>\n";
   print "<img src=\"" . $dir . "/" . $fname . "\"" . htmlentities($ttl) . ">\n";
   print "<br><i>" . htmlentities($caption) . "</i>\n";
   print "</table>\n";

   if ($dir and $dir != "")
   {
      print "<p><font size=+1><a href=\"" . $dir . "/index.php\">\n";
      print "Click here to see more pictures from the same set of photos.</a></font>\n";
   }

   print "<p><font size=+1>Press the Back button to return to the previous screen.</font>\n";

   db_close();
?>

<p><hr><font size=-1><i>Email: <a href="mailto:steve@oharasteve.com">steve@oharasteve.com</a></i></font>

</body>
</html>

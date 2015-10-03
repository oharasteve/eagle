<html>
<head>
<title>Steve O'Hara Family Videos</title>
</head>
<body>

<h2>Steve O'Hara Family Videos</h2>

<p>These movies are pretty slow to download. Make sure you have the sound turned on.
Times are minutes and seconds.

<?php
   # Connect to database
   include("../db.php");

   print "<p><table border=1 cellpadding=5>";
   
   $query = "SELECT * FROM Videos ORDER BY Date DESC";
   $results = mysql_query($query);
   $cnt = 0;
   $w = 4;	// pictures per line
   while ($row = mysql_fetch_array($results))
   {
      if ($cnt % $w == 0)
      {
         print "<tr>\n";
      }
      $cnt++;

      $sz = $row["Size"] / 1024 / 1024;
      $mb = number_format($sz, 1);
      $dat = date("M d  Y", strtotime($row["Date"]));

      print "<td valign=top align=center width=200>\n";
      print "  " . $dat . ", " . $mb . "MB, " . $row["Time"] . "\n";
      print "  <br><a href=\"" . $row["Directory"] . "/" . $row["Video"] . "\">\n";
      print "  <img width=150 src=\"" . $row["Photo"] . "\"></a>\n";
      print "  <br>" . $row["Description"] . "\n";
      print "  <br>" . $row["Location"] . "\n";
   }

   $rem = $cnt % $w;
   if ($rem > 0)
   {
      for ($i = $rem; $i < $w; $i++)
      {
         print "<td>&nbsp;";
      }
   }

   print "</table>";
   db_close();
?>

<p><hr><font size=-1><i>Email: <a href="mailto:steve@oharasteve.com">steve@oharasteve.com</a></i></font>
</body>
</html>

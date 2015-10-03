<html>
<head>
<title>Steve O'Hara Photo Statistics</title>
</head>
<body>

<h2>Steve O'Hara Photo Statistics</h2>

<p>This summary page is really just for me. Why are you snooping around?
Anyways, the Viewings count for each picture is incremented whenever somebody
looks at the enlarged version. And, no, I'm not prying into your credit card numbers.
I don't even know how. Oh, by the way, your Visa bill is overdue. jk.</p>

<?php
   # Connect to database
   include("../db.php");

   print "<table border=1 cellpadding=5>";
   print "<tr><td><b><i>Pictures</i></b></td>";
   print "<td><b><i>Viewings</i></b></td>";
   print "<td><b><i>Average</i></b></td>";
   print "<td><b><i>Directory</i></b></td></tr>\n";

   $query = "SELECT distinct(Directory) FROM Photos WHERE NOT (Directory like '%secret%')";
   $results = mysql_query($query);
   $ndirs = 0;
   while ($row = mysql_fetch_array($results))
   {
      $dir = $row["Directory"];
      $query2 = "SELECT count(*) as cnt, sum(Viewings) as views FROM Photos where Directory = '" . $dir . "'";
      $results2 = mysql_query($query2);
      $row = mysql_fetch_array($results2);
      $npic = $row["cnt"];
      $nviews = $row["views"];

      $ndirs++;
      $avg = round($nviews / $npic);
      print "<tr><td align=center>" . number_format($npic);
      print "  <td align=center>" . number_format($nviews);
      print "  <td align=center>" . $avg;
      print "  <td><a href=\"" . $dir . "/index.php\">" . $dir . "</a>\n";
   }

   $query3 = "SELECT count(*) as cnt, sum(Viewings) as views FROM Photos WHERE NOT (Directory like '%secret%')";
   $results3 = mysql_query($query3);
   $row = mysql_fetch_array($results3);
   $npic = $row["cnt"];
   $nviews = $row["views"];
   $avg = round(10 * $nviews / $npic) / 10;
   print "<tr><td align=center><b><i>" . number_format($npic) . "</b></i>";
   print "  <td align=center><b><i>" . number_format($nviews) . "</b></i>";
   print "  <td align=center><b><i>" . $avg . "</b></i>";
   print "  <td><b><i>Total (" . $ndirs . " directories)</b></i>";
   print "</table>\n";

   # Show pictures with captions and originals
   
   $query = "SELECT count(*) AS CNT FROM Photos WHERE Caption = ''";
   $results = mysql_query($query);
   $row = mysql_fetch_array($results);
   $cnt = $row["CNT"];
   print "<p>Photos with a caption = " . number_format($npic - $cnt) . ", no caption = " . number_format($cnt) . ".</p>\n";

   $query = "SELECT count(*) AS CNT FROM Photos WHERE Original = ''";
   $results = mysql_query($query);
   $row = mysql_fetch_array($results);
   $cnt = $row["CNT"];
   print "<p>Photos with an original = " . number_format($npic - $cnt) . ", no original = " . number_format($cnt) . ".</p>\n";

   # Now show most popular pictures

   print "<br><br><hr><br><h3>Top 10 Photos</h3><table border=1 cellpadding=5><tr>";

   $query = "SELECT * FROM Photos ORDER BY Viewings DESC"; # Could add "WHERE Viewings > 40 AND NOT (Directory like '%secret%')"
   $results = mysql_query($query);
   $ndirs = 0;
   $cnt = 0;
   while ($row = mysql_fetch_array($results))
   {
      if ($cnt == 5) print "</tr><tr>";
      if ($cnt++ >= 10) break;

      $dir = $row["Directory"];
      $pic = $row["Photo"];
      $suffix = $row["Suffix"];
      $nviews = $row["Viewings"];

      print "<td align=center>" . $dir . "<br>" . $pic . "<br>";
      print "<a href=\"pic.php?Pic=" . $pic . "\"><img src=\"" . $dir . "/thumbs/" . $pic . "_thumb" . $suffix . "\"></a><br>Viewings = " . $nviews . "</td>";
   }
   print "</tr></table>";

   # Now show pictures by year
   
   print "<br><br><hr><br><h3>Photos per Year</h3><table border=1 cellpadding=5><tr>";

   $query = "SELECT Year, COUNT(Year) AS CNT FROM Photos GROUP BY Year ORDER BY Year";
   $results = mysql_query($query);
   $seq = 0;
   while ($row = mysql_fetch_array($results))
   {
      $year = $row["Year"];
      $cnt = $row["CNT"];

	  if ($year == 0)
	  {
	      $none = $cnt;
	  }
	  else
	  {
	      $seq++;
	      if ($seq % 10 == 1)
		  {
              if ($seq > 1) print "</td>";
              print "<td valign=top align=left>";
		  }
		  else
		  {
		      print "<br>";
		  }
          print $year . " - " . number_format($cnt) . "\n";
	  }
   }
   print "<br><i>none - " . number_format($none) . "</i></td></tr>\n";
   print "</table>";

   db_close();
?>

<p><hr><font size=-1><i>Email: <a href="mailto:steve@oharasteve.com">steve@oharasteve.com</a></i></font>
</body>
</html>

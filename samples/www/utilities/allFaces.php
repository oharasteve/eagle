<html>

<head>
<title>People's Faces</title>
</head>

<body bgcolor="#f0f0f0">

<h1>People's Faces</h1>

<?php
  include("db.php");

  print "<table border=\"1\"><tr><td align=\"left\" valign=\"top\">";
  
	$query = "SELECT Name, Person, COUNT(Name) AS CNT FROM Faces GROUP BY Name, Person ORDER BY Name";
	$results = mysql_query($query);
	$faces = 0;
	$perColumn = 75;
	$soFar = 0;
	$seq = 0;
	$total = 0;
	$cntFamily = 0;
	while ($face = mysql_fetch_array($results))
	{
		$name = $face["Name"];
		$count = $face["CNT"];
		$total += $count;
		if ($name == "Monica Harmon") continue;
		
	    $soFar++;
		if ($soFar > $perColumn)
		{
			print "</td><td align=left valign=top>";
			$soFar = 1;
		}
		else if ($soFar > 1)
		{
			print "<br>";
		}
		
		$isFamily = $face["Person"] != "";
		if ($isFamily) $cntFamily++;
		$familyMarker = $isFamily ? "*" : ".";
		
		$seq++;
		$fixed = str_replace(" ", "_", $name);
		print $seq . $familyMarker . " <a href=../tree/faces.php?Name=" . $fixed . ">" . $name . "</a> (" . $count . ")\n";
	}

	print "<br><br>Total = " . number_format($total);

  print "<br><br>Family (*) = " . $cntFamily;	
  print "<br>Friends (.) = " . ($seq - $cntFamily);	

  print "</tr></table>";
  
  db_close();
?>

<p><font size=-1><i>Email: <a href="mailto:steve@oharasteve.com">steve@oharasteve.com</a></i></font>

</body>
</html>

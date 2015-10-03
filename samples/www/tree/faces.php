<html>

<head>
<title>Faces</title>

<style type="text/css">
  td {
	  text-align: center;
	  width: 200;
  }
  table {
    display: inline-table;
  }
</style>

</head>

<body bgcolor="#f0f0f0">

<?php
   # Connect to database
   include("../db.php");

	$key = $_REQUEST["Key"];
	$fld = "Person";
	if (! $key || $key == "")
	{
		$key = $fixed = str_replace("_", " ", $_REQUEST["Name"]);
		$fld = "Name";
	}
	
	print "<i><a href=\"list.php\">Back to people list<a></i>";
	print "<br><i><a href=\"../utilities/allFaces.php\">Find portraits for other people<a></i>";

	print "<p><hr>\n\n";
	$didH1 = false;

	$query = "SELECT * FROM Faces, Photos WHERE Faces." . $fld . "='" . $key .
				  "' AND Photos.Photo=Faces.Photo ORDER BY Year, Month, Day";
	$results = mysql_query($query);
	while ($face = mysql_fetch_array($results))
	{
		if (! $didH1)
		{
			$name = $face["Name"];
			print "<h1>" . $name . "</h1>\n";
			$didH1 = true;
		}
		
		# Calculations for cropping image to just the face
		# $width = $face["Width"];
		# $height = $face["Height"];
		# $left = round(($face["Left"] * $width) / 65535);
		# $top = round(($face["Top"] *  $height) / 65535);
		# $right = round(($face["Right"] *  $width) / 65535);
		# $bottom = round(($face["Bottom"] * $height) / 65535);
		
		$yrFace = $face["Year"];
    print "<table><tr valign=top>\n";

    $suffix = $face["Suffix"];
		$photoName = $face["Photo"] . $suffix;
		$dir = $face["Directory"];
		$caption = $face["Caption"];
		if ($caption == "")
		{
			if ($yrFace != 0)
			{
				$caption = "Year: " . $yrFace;
			}
		}
		else
		{
			if ($yrFace != 0)
			{
				$caption = $caption . " (" . $yrFace . ")";
			}
		}

		$nc = strlen($photoName);
    $sufflen = strlen($suffix);  # Usually 4
		$thumbName = substr($photoName, 0, $nc-$sufflen) . "_thumb" . substr($photoName, $nc-$sufflen, $sufflen);
		$thumb = "<img src=\"" . $dir . "/thumbs/" . $thumbName . "\"" . $ttl . ">";
		$href = "<a href=\"/photos/pic.php?Pic=" . $face["Photo"] . "&Faces=Yes\">";

		print "<td>" . $href . $thumb . "<br>" . htmlentities($caption) . "</a>\n";
    print "</table>\n";
	}

  db_close();
?>

<p><hr><font size=-1><i>Email: <a href="mailto:steve@oharasteve.com">steve@oharasteve.com</a></i></font>

</body>
</html>

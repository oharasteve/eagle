<html>

<head>
<title>WikiTree Listing</title>
</head>

<body bgcolor="#f0f0f0">
<h1>WikiTree Listing</h1>

<p><i><a href="index.html">Back to main page</a></i>

<?php
   # Connect to database
   include("db.php");

   $query = "SELECT * FROM People WHERE WikiTree <> '' ORDER BY First, Last";
   $col = mysql_query($query);

   $seq = 0;
   while ($person = mysql_fetch_array($col))
   {
      $seq++;
      $wiki = $person["WikiTree"];
      print "<h2>" . $seq . ". " . anchor($person) . "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" . $wiki . "</h2>\n";
      print "<iframe width=\"496\" height=\"495\" src=\"http://www.wikitree.com/treewidget/" . $wiki . "\" ";
      print "scrolling=\"no\" frameborder=\"0\" marginheight=\"0\" marginwidth=\"0\"></iframe>\n";
   }

   db_close();

function spacer($piece)
{
   if ($piece == "")
   {
      return "";
   }
   return $piece . " ";
}

function z2($num)
{
	if ($num >= 10) return "" . $num;
	return "0" . $num;
}

function anchor($person)
{
   $name = spacer($person["Title"]) .
   	  spacer($person["First"]) .
   	  spacer($person["Middle"]) .
   	  spacer($person["Maiden"]) .
   	  spacer($person["Last"]) .
   	  spacer($person["Jr"]);

   $nick = $person["Nickname"];
   if ($nick != "")
   {
      $name = $name . "(" . $nick . ") ";
   }

   $k = $person["KeyName"];
   return "<a href=\"view.php?Key=" . $k . "\">" . rtrim($name) . "</a>";
}

?>

<p><font size=-1><i>Email: <a href="mailto:steve@oharasteve.com">steve@oharasteve.com</a></i></font>

</body>
</html>

<html>

<head>
<title>WikiTree Entries</title>
</head>

<body bgcolor="#f0f0f0">
<h1>WikiTree Entries</h1>

<p><i><a href="index.html">Back to main page</a></i>
<br/><hr/>
<?php
   # Connect to database
   include("db.php");

   $query = "SELECT * FROM People WHERE WikiTree <> '' ORDER BY WikiTree";
   $col = mysql_query($query);

   while ($person = mysql_fetch_array($col))
   {
      $wiki = $person["WikiTree"];
      print "<br/>" . $wiki . " - " . anchor($person) . "\n";
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
   return rtrim($name);
}

?>

<p><font size=-1><i>Email: <a href="mailto:steve@oharasteve.com">steve@oharasteve.com</a></i></font>

</body>
</html>

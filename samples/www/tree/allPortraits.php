<html>

<head>
<title>All Portraits</title>

<style type="text/css">
  td.x {
	  text-align: center;
	  width: 200;
  }
  table {
    display: inline-table;
  }
</style>

</head>

<body bgcolor="#f0f0f0">

<h1>Portraits, sorted by year born</h1>

<?php
   # Connect to database
   include("../db.php");

   $monthArray = array("January", "February", "March", "April", "May", "June", "July", "August",
                  "September", "October", "November", "December");
   $months = array("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
   
   $query = "SELECT * FROM People ORDER BY YrBorn, MonBorn, DayBorn";
   $results = mysql_query($query);
   while ($person = mysql_fetch_array($results))
   {
      $key = $person["KeyName"];
      $name = pretty($person);

      $Phquery = "SELECT * FROM PeoplePhotos, Photos WHERE PeoplePhotos.KeyName='" . $key .
                      "' AND Photos.Photo=PeoplePhotos.Photo ORDER BY Year";
      $Phresults = mysql_query($Phquery);
      $photos = 0;
      while ($photo = mysql_fetch_array($Phresults))
      {
         if ($photos == 0)
         {
            print "<hr/><h3>Portraits of " . $name . "</h3>\n";
         }
         $photos++;

         print "<table><tr><td valign=top>\n";
         $suffix = $photo["Suffix"];
         $photoName = $photo["Photo"] . $suffix;
         $dir = $photo["Directory"];
         $caption = $photo["Caption"];
         $yrPhoto = $photo["Year"];
         if ($caption == "")
         {
            if ($yrPhoto != 0)
            {
               $caption = "Year: " . $yrPhoto;
            }
         }
         else
         {
            if ($yrPhoto != 0)
            {
               $caption = $caption . " (" . $yrPhoto . ")";
            }
         }

         $ttl = $photo["Position"];
         if ($ttl and $ttl != "")
         {
            $ttl = " title=\"Position: " . $ttl . "\"";
         }

         $nc = strlen($photoName);
         $sufflen = strlen($suffix);  # Usually 4
         $thumbName = substr($photoName, 0, $nc-$sufflen) . "_thumb" . substr($photoName, $nc-$sufflen, $sufflen);
         $thumb = "<img src=\"" . $dir . "/thumbs/" . $thumbName . "\"" . $ttl . ">";
         $href = "<a href=\"/photos/pic.php?Pic=" . $photo["Photo"] . "\">";

         print "<td class=x>" . $href . $thumb . "<br>" . htmlentities($caption) . "</a>\n";
         print "</table>\n";
      }
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

function pretty($person)
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

   return htmlentities(rtrim($name));
}

?>

<p><font size=-1><i>Email: <a href="mailto:steve@oharasteve.com">steve@oharasteve.com</a></i></font>

</body>
</html>

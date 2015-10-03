<?php

function init()
{
   searchButton();

   print "<p><i>Click on a picture to see more pictures.</i>";
   print "<hr>";
   print "<center>\r\n";

   # Connect to database
   include("../db.php");
}

function start_table()
{
}

# Obsolete misnamed function
function pic($dir, $pic, $ttl, $movies)
{
   shared($dir, $pic, $ttl, $movies);
}

function subdir($dir, $pic, $ttl)
{
   shared($dir, $pic, $ttl, 0);
}

function shared($dir, $pic, $ttl, $movies)
{
   if ($movies == 0)
   {
      $query = "SELECT count(*) AS cnt FROM Videos WHERE Directory LIKE '" . $dir . "/%' OR " .
         "Directory LIKE '%/" . $dir . "/%' OR Directory LIKE '%/" . $dir . "'";
      $results = mysql_query($query);
      $cnt = mysql_fetch_array($results);
      $movies = $cnt["cnt"];
   }

   $query = "SELECT count(*) AS cnt FROM Photos WHERE Directory LIKE '" . $dir . "/%' OR " .
      "Directory LIKE '%/" . $dir . "/%' OR Directory LIKE '%/" . $dir . "'";
   $results = mysql_query($query);
   $cnt = mysql_fetch_array($results);
   $npic = $cnt["cnt"];

   $query = "SELECT * FROM Photos WHERE Photo='" . $pic . "'";
   $results = mysql_query($query);
   $p = mysql_fetch_array($results);
   $loc = $p["Directory"];
   $suff = $p["Suffix"];

   if ($movies == 0)
   {
      $mov = "";
   }
   else if ($movies == 1)
   {
      $mov = ", 1 Movie Clip";
   }
   else
   {
      $mov = ", " . $movies . " Movie Clips";
   }

   print "<table><tr><td><a href=\"" . $dir . "/index.php\">\r\n";
   print "  <img class=mult src=\"" . $loc . "/thumbs/" . $pic . "_thumb" . $suff .
      "\" title=\"" . $npic . " pictures" . $mov . "\">";
   print "<br>" . $ttl . "</a></tr></table>\r\n";
}

function end_table()
{
}

function done()
{
   print "</center>\r\n";
   db_close();
}

function searchButton()
{
   print "<form action=\"/photos/search.php\" target=\"search_pix\" method=\"post\">\n";
   print "  Search for:&nbsp;\n";
   print "  <input type=text name=SrchFor value=\"\">\n";
   print "  &nbsp;&nbsp;<button type=submit>Search</button>\n";
   print "</form>\n";
}
?>

<?php
   include("../db.php");

   $srch = trim($_REQUEST["SrchFor"]);
   if ($srch == null or $srch == "")
   {
      $srch = "dylan";	// Why not?
   }
   $ttl = "Search Results for '" . htmlentities($srch) . "'";

   $scope = trim($_REQUEST["Scope"]);
   if ($scope == null)
   {
      $scope = "";
   }

   print "<html>\n";
   print "<head>\n";
   print "<title>" . $ttl . "</title>\n";
   print "<style type=\"text/css\">\n";
   print "  td {\n";
   print "	  text-align: center;\n";
   print "	  width: 240;\n";
   print "  }\n";
   print "  td.wide {\n";
   print "	  text-align: center;\n";
   print "	  width: 400;\n";
   print "  }\n";
   print "</style>\n";
   print "</head>\n";

   if ($detail_print)
   {
      print "<body>\n";
   }
   else
   {
      print "<body bgcolor=\"#F0F0F0\">\n";
   }
   print "<h1>" . $ttl . "</h1>\n";

   searchButton($srch, $scope);

   $query = "SELECT * FROM Photos WHERE (Photo like '%" . htmlentities($srch) . "%' or Caption like '%" . htmlentities($srch) . "%')" .
      " and Directory like '%" . htmlentities($scope) . "%' and not (Directory like '%secret%') order by Directory, Photo";
   $results = mysql_query($query);
   $cnt = 0;
   while ($photo = mysql_fetch_array($results))
   {
      $pic = $photo["Photo"];
      $dir = $photo["Directory"];
      $caption = $photo["Caption"];
      $suffix = $photo["Suffix"];

      if ($cnt % 4 == 0)
      {
         if ($cnt > 0)
         {
            print "</table></center>\n\n";
         }
         print "<center><table border=0 cellpadding=10><tr valign=top>";
      }

      print "<td><a href=\"pic.php?Pic=" . $pic . "\">\n";
      print "   <img src=\"" . $dir . "/thumbs/" . $pic . "_thumb" . $suffix . "\" title=\"Click to see full-sized version\">\n";
      print "   <br>" . htmlentities($caption) . "</a>\n";

      $cnt++;
      if ($cnt == 500)
      {
         break;
      }
   }

   if ($cnt == 0)
   {
      print "<p><i>Sorry, no results found for " . htmlentities($srch) . "</i></p>";
   }
   else
   {
      print "</table></center>\n\n";
      if ($cnt > 1)
      {
         print "<p><i>" . $cnt . " picures found for '" . htmlentities($srch) . "'</i></p>";
      }
   }

   db_close();

function searchButton($srch, $scope)
{
   print "<form action=\"search.php\" target=\"search_pix\" method=\"post\">\n";
   print "  New search:&nbsp;\n";
   print "  <input type=text name=SrchFor value=\"" . htmlentities($srch) . "\" title=\"pattern to match picture name or caption\">\n";
   print "  &nbsp;&nbsp;Directory:\n";
   print "  <input type=text name=Scope value=\"" . htmlentities($scope) . "\" title=\"pattern to match directory name\">\n";
   print "  &nbsp;&nbsp;<button type=submit>Search</button>\n";
   print "</form>\n";
}
?>

<?php
function pdir2($dir, $dat)
{
   pdir($dir, $dat);
}

function pdir($dir, $dat)
{
   # Connect to database
   include("../db.php");

   $detail_print = $_REQUEST["Print"] != null;	// Uses only full size images, for printing

   # Hack until all the index.php files are updated (which is impossible)
   if (substr($dir,0,1) != '/') $dir = '/photos/' . $dir;

   $query = "SELECT * FROM Photos WHERE Directory='" . $dir . "' order by Year, Month, Day, Photo";
   $results = mysql_query($query);
   while ($photo = mysql_fetch_array($results))
   {
      $pic = $photo["Photo"];
      $caption = $photo["Caption"];
      $suffix = $photo["Suffix"];

      $year = $photo["Year"];
      $month = $photo["Month"];
      $day = $photo["Day"];
      $views = $photo["Viewings"];

      if ($year and $year > 0)
      {
         $date = $year;

         if ($month and $month > 0)
         {
            if ($day and $day > 0)
            {
               $date = $month . "/" . $day . "/" . $year;
            }
            else
            {
               $date = $month . "/" . $year;
            }
         }

         if ($caption and $caption != "")
         {
            $caption = $caption . " (" . $date . ")";
         }
         else
         {
            $caption = "Date: " . $date;
         }
      }

      print "   <table><tr><td>\n";
	  print "     <font size=-1><i>" . $pic . $suffix;
      if ($detail_print)
      {
         $w = $photo["Width"];
         $h = $photo["Height"];

         print "</i></font><br>\n";
         if ($h > $w)
         {
            print "     <img src=\"" . $pic . $suffix . "\" width=130>\n";
         }
         else
         {
            print "     <img src=\"" . $pic . $suffix . "\" height=130>\n";
         }
         print "     <br>" . htmlentities($caption) . "\n";
      }
      else
      {
         print " (" . $views. ")</i></font><br>\n";
         print "     <a href=\"/photos/pic.php?Pic=" . $pic . "\">\n";	# "&Faces=Yes"
         print "     <img src=\"thumbs/" . $pic . "_thumb" . $suffix . "\" title=\"Click to see full-sized version\"><br>" . htmlentities($caption) . "</a>\n";
      }
	  
      print "   </td></tr></table>\n";
   }

   print "<hr><font size=-1><i>Last Updated on " . $dat . "\n";
   if (! $detail_print)
   {
      print "<br>Email: <a href=\"mailto:steve@oharasteve.com\">steve@oharasteve.com</a>\n";
      print "<br><a href=\"index.php?Print=yes\">Printable version</a>\n";
   }
   print "</i></font>\n";

   print "</body>\n";
   print "</html>\n";

   db_close();
}
?>

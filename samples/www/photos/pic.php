<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style type="text/css">
  img.FaceStyle { border:2px solid red; }
  img.NoFaceStyle { border:0px solid red; }
</style>

<script>
function checkArrows(prv, nxt)
{
   var key = ('which' in event) ? event.which : event.keyCode;
   if (prv.length > 0 && key == 37)    // left arrow
   {
      //alert("goto " + prv);
      location = 'pic.php?Pic=' + prv;
      return false;
   }
   if (nxt.length > 0 && key == 39)    // right arrow
   {
      //alert("goto " + nxt);
      location = 'pic.php?Pic=' + nxt;
      return false;
   }
   return;
}

function showFaces(show)
{
   var showButton = document.getElementById('ShowFaces');
   var hideButton = document.getElementById('HideFaces');
   showButton.disabled = (show ? true : false);
   hideButton.disabled = (show ? false : true);

   // Update anchors on next and prev
   fixHref(show, 'NextPic');
   fixHref(show, 'PrevPic');

   for (i = 1; i < 40; i++)
   {
      var face = document.getElementById('face-' + i);
      if (face == null) break;
      face.style.borderWidth = (show ? 2 : 0);
      var label = document.getElementById('label-' + i);
      if (label) label.style.visibility = (show ? 'visible' : 'hidden');
   }
}

function fixHref(show, anchorId)
{
   var elt = document.getElementById(anchorId);
   if (elt != null)
   {
      var address = elt.href;
      if (show && address.indexOf('&Faces=Yes') < 0) elt.href += "&Faces=Yes";
      if (! show && address.indexOf('&Faces=Yes') > 0) elt.href = address.substring(0, address.length-10);
   }
}
function delpp(pic,who)
{
   if (confirm("Delete " + who + " from " + pic + "?"))
   {
      parent.location = "pic.php?Update=delete&Pic=" + pic + "&Who=" + who;
   }
}
</script>
</head>
<body bgcolor="#f0f0f0" onload="pik.focus()">

<?php
   # Connect to database
   include("../db.php");

   # Set to 1 when running XENU please! Use 0 for normal.
   $xenu = 0;
   
   $faces = 0;
   if ($_REQUEST["Faces"] == "Yes")
   {
      $faces = 1;
   }

   $quiet = 0;
   if ($_REQUEST["q"] != null)
   {
      $quiet = 1;
   }
   
   $months = array("January", "February", "March", "April", "May", "June", "July", "August",
                   "September", "October", "November", "December");

   $pic = $_REQUEST["Pic"];

   $query = "SELECT * FROM Photos WHERE Photo='" . $pic . "'";
   $results = mysql_query($query);
   $photo = mysql_fetch_array($results);
   $dir = $photo["Directory"];
   $suffix = $photo["Suffix"];
   $photoName = $photo["Photo"];
   $fname = $photoName . $suffix;
   $cap = $photo["Caption"];
   $year = $photo["Year"];
   $month = $photo["Month"];
   $day = $photo["Day"];
   $original_short = $photo["Original"];
   $width = $photo["Width"];
   $height = $photo["Height"];
   $original = $original_short;

   # Move all the originals to the new website
   if (substr($original,0,4) != "http")
   {
      $original = "http://www.oharasteven.com" . $original;
   }

   # Update number of times this photo has been looked at
   # Turn off when running XENU please!
   if ($xenu == 0)
   {
      $views = $photo["Viewings"];
      $upquery = "UPDATE Photos SET Viewings=" . ($views+1) . " WHERE Photo='" . $pic . "'";
      mysql_query($upquery);
   }

   $update = $_REQUEST["Update"];

   #
   # Updating date and/or caption?
   #

   if ($update == "caption")
   {
      $new_month = $_REQUEST["Month"];
      $new_day = $_REQUEST["Day"];
      $new_year = $_REQUEST["Year"];
      $new_cap = stripslashes($_REQUEST["Caption"]);

      if ($new_month == "0" and $new_day == "0" and $new_year != "" and strpos($new_year, "/") > 0)
      {
          $slash1 = strpos($new_year, "/");
          $slash2 = strpos($new_year, "/", $slash1 + 1);
          if ($slash2 > 0)
          {
              $new_month = substr($new_year, 0, $slash1);
              $new_day = substr($new_year, $slash1+1, $slash2-$slash1-1);
              $new_year = substr($new_year, $slash2+1);
          }
      }
      
      if ($month != $new_month or $day != $new_day or $year != $new_year or $cap != $new_cap)
      {
         $sets = "Photo='" . $pic . "', Dir='" . $dir . "', Suffix='" . $suffix .
            "', Caption='" . addslashes($cap) . "'";
         if ($year and $year > 0)
         {
            if ($month and $month > 0)
            {
               $sets = $sets . ", Month=" . $month;
               if ($day and $day > 0)
               {
                  $sets = $sets . ", Day=" . $day;
               }
            }
            $sets = $sets . ", Year=" . $year;
         }
         # print "<p>" . $sets . "</p>";
         $upquery = "INSERT INTO PhotosEditLog SET " . $sets;
         mysql_query($upquery);

         $month = $new_month;
         $day = $new_day;
         $year = $new_year;
         if (!$year) $year = 0;
         $cap = $new_cap;
         $upquery = "UPDATE Photos SET Month=" . $month . ", Day=" . $day . ", Year=" . $year .
             ", Caption='" . addslashes($cap) . "' WHERE Photo='" . $pic . "'";
         mysql_query($upquery);
      }
   }

   #
   # Updating people position info?
   #

   if ($update == "people")
   {
      $query = "SELECT * FROM PeoplePhotos WHERE Photo='" . $pic . "'";
      $results = mysql_query($query);
      while ($row = mysql_fetch_array($results))
      {
         $key = $row["KeyName"];
         $new_position = $_REQUEST["POS_" . $key];
             $upquery = "UPDATE PeoplePhotos SET Position = '" . $new_position .
                "' WHERE Photo='" . $pic . "' and KeyName='" . $key . "'";
           mysql_query($upquery);
      }
   }

   #
   # Deleting a person from a picture?
   #

   if ($update == "delete")
   {
      $who = $_REQUEST["Who"];

      // Save before deleting
      $upquery = "INSERT INTO PeoplePhotosDeletions SET Photo='" . $pic .
         "', KeyName='" . $who . "', Position=(SELECT Position FROM PeoplePhotos" .
         " WHERE Photo='" . $pic . "' AND KeyName='" . $who . "')";
      mysql_query($upquery);

      $upquery = "DELETE FROM PeoplePhotos WHERE Photo='" . $pic . "' AND KeyName='" . $who . "' LIMIT 1";
      mysql_query($upquery);
   }

   #
   # Addng a person (or people) to a picture?
   #

   if ($update == "addem")
   {
      foreach ($_REQUEST as $key => $val)
      {
         if (substr($key, 0, 4) == "CHK_")
         {
            if ($val == "on")
            {
               $upquery = "INSERT INTO PeoplePhotos SET Photo='" . $pic . "', KeyName='" .
                   substr($key, 4) . "', Position=''";
               mysql_query($upquery);
            }
         }
      }
   }

   #
   # Pick up date and caption
   #

   $caption = $cap;
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

   #
   # Find previous and next photos
   #

   $prev = null;
   $next = null;
   $query = "SELECT * FROM Photos WHERE Directory='" . $dir . "' order by Year, Month, Day, Photo";
   $results = mysql_query($query);
   $prevrow = null;
   while ($row = mysql_fetch_array($results))
   {
      if ($row["Photo"] == $pic)
      {
         $prev = $prevrow["Photo"];
         $prevname = $prev . "_thumb" . $prevrow["Suffix"];
         if ($faces && $prev) $prev = $prev . "&Faces=Yes";
         if ($row = mysql_fetch_array($results))
         {
            $next = $row["Photo"];
            $nextname = $next . "_thumb" . $row["Suffix"];
            if ($faces && $next) $next = $next . "&Faces=Yes";
         }
      }
      $prevrow = $row;
   }

    if (! $quiet)
    {
       backto($dir);
       searchButton();
    }

   #
   # Finally, show the picture and the previous and next pictures
   #
   print "<table id=\"pik\" border=5 cellpadding=10 onkeyup=\"return checkArrows('" .
      $prev . "','" . $next . "');\"><tr>\n";

   if ($prev && ! $quiet)
   {
      print "<td align=center width=200>\n";
      print "<a id=PrevPic href=\"pic.php?Pic=" . $prev . "\">\n";
      print "<img src=\"" . $dir . "/thumbs/" . $prevname . "\"" . htmlentities($pic) . ">\n";
      print "<br><i>&lt;&lt; Previous &lt;&lt;</i></a></td>\n";
   }

   print "<td align=center>\n";
   print $dir . "/" . $fname . "<br>";
   print "<div style='position:relative;'><img src=\"" . $dir . "/" . $fname . "\"" . htmlentities($pic) . ">\n";
   
   $query = "SELECT * FROM Faces WHERE Photo='" . $photoName . "'";
   $results = mysql_query($query);
   $seq = 0;
   while ($row = mysql_fetch_array($results))
   {
      $seq++;
      $left = round(($row["Left"] * $width) / 65535);
      $top = round(($row["Top"] *  $height) / 65535);
      $right = round(($row["Right"] *  $width) / 65535);
      $bottom = round(($row["Bottom"] * $height) / 65535);
      $name = $row["Name"];
      $person = $row["Person"];
      $lowYear = $row["LowYear"];
      $highYear = $row["HighYear"];

      print "<div id='label-" . $seq . "' style='position:absolute; color:red; left:" .
          ($left+3) . "px; top:" . $top . "px; visibility:" . ($faces ? "visible" : "hidden") .
          "'>" . $name . "</div>\n";
      print "<a style='position:absolute; left:" .
          $left . "px; top:" . $top . "px; width:" .
          ($right-$left) . "px; height:" . ($bottom-$top). "px;'";
      print " title='" . $name . "'";
      if ($person) print " href='/tree/view.php?Key=" . $person . "'";
      print ">\n";
      print "    <img id='face-" . $seq . "' class='" . ($faces ? "" : "No") . "FaceStyle'";
      print " title='" . $name . "' src=\"/photos/blank.gif\" width='100%' height='100%'></a>\n";
   }
   
   print "</div><br><i>" . htmlentities($caption) . "</i></td>\n";

   if ($next && ! $quiet)
   {
      print "<td align=center width=200>\n";
      print "<a id=NextPic href=\"pic.php?Pic=" . $next . "\">\n";
      print "<img src=\"" . $dir . "/thumbs/" . $nextname . "\"" . htmlentities($pic) . ">\n";
      print "<br><i>&gt;&gt; Next &gt;&gt;</i></a></td>\n";
   }

   print "</table>\n";

    #
    # Show button for faces
    #
    if (! $quiet)
    {
        $disableShow = "disabled=disabled ";
        $disableHide = $disableShow;
        if ($faces) $disableHide = "";
        if (! $faces) $disableShow = "";

        print "<p><input id='ShowFaces' " . $disableShow . "type=button onclick='showFaces(true);' value='Show Faces'>\n";
        print "&nbsp;<input id='HideFaces' " . $disableHide . "type=button onclick='showFaces(false);' value='Stop Showing Faces'>\n";
    }
    else
    {
        print "<p>\n";
    }
   
   #
   # See if we know where the original is
   #
   if ($original_short && $original_short != "")
   {
      print "&nbsp;&nbsp;&nbsp;<a href=\"" . $original . "\">";
      $lastDot = strrpos($original, ".");
      $lastSlash = strrpos($original, "/");
      if ($lastDot && $lastSlash)
      {
         $origName = substr($original, $lastSlash+1, $lastDot-$lastSlash-1);
         $origSuff = substr($original, $lastDot);
         if ($origSuff == ".pdf")
         {
            $origThumb = "thumbs/" . $origName . "_pdf_thumb.jpg";
         }
         else
         {
            $origThumb = "thumbs/" . $origName . "_thumb" . $origSuff;
         }
         
         print "<img height=40 src=\"" . substr($original,0,$lastSlash+1) . $origThumb . "\">";
      }
      print "</a> &lt;-- Click on this picture to see the original.</p>";
   }

    if (! $quiet)
    {
        print "<p><i>Please feel free to update the photo database!</i>\n";

        #
        # Let them update the date and caption
        #
        print "<table><tr><td>\n";
        print "<form action=\"pic.php\" method=\"post\">\n";
        print "<table border=1><tr><td>";
        print "  Date:&nbsp;\n";
        print "  <td><input type=hidden name=Update value=\"caption\">\n";
        print "  <input type=hidden name=Pic value=\"" . htmlentities($pic) . "\">\n";

        # Month
        print "  <select name=Month>\n";
        print "  <option value=0>Month</option>\n";
        for ($i=1; $i<=12; $i++)
        {
          $sel = "";
          if ($month == $i)
          {
             $sel = " selected";
          }
          print "  <option value=" . $i . $sel . ">" . $months[$i-1] .  "</option>\n";
        }
        print "  </select>\n";

        # Day
        print "  <select name=Day>\n";
        print "  <option value=0>Day</option>\n";
        for ($i=1; $i<=31; $i++)
        {
          $sel = "";
          if ($day == $i)
          {
             $sel = " selected";
          }
          print "  <option value=" . $i . $sel . ">" . $i .  "</option>\n";
        }
        print "  </select>\n";

        # Year
        print "  <input type=\"text\" maxlength=10 size=6 name=Year value=\"" . $year . "\">&nbsp;&nbsp;\n";

        # Caption
        print "<tr><td>Caption:&nbsp;\n";
        print "  <td><input type=\"text\" maxlength=500 size=50 name=Caption value=\"" . htmlspecialchars($cap) . "\">&nbsp;&nbsp;\n";

        print "  <tr><td>&nbsp;<td><button type=submit>Update Date and Caption</button>\n";
        print "</table></form>\n";
    }
   #
   # Look for connections to people in the database
   #

    if (! $quiet)
    {
        $query = "SELECT * FROM PeoplePhotos WHERE Photo='" . $pic . "' ORDER BY KeyName";
        $results = mysql_query($query);
        $prevrow = null;
        $cnt = 0;

        print "<td><form action=\"pic.php\" method=\"post\">\n";
        print "  <input type=hidden name=Update value=\"people\">\n";
        print "  <input type=hidden name=Pic value=\"" . htmlentities($pic) . "\">\n";
        print "<p><table border=0><tr><td>";
        print "<table border=1 cellpadding=5>\n";
        print "<tr><td><i>Delete</i><td width=200><i>Person</i><td><i>Position</i>\n";

        while ($row = mysql_fetch_array($results))
        {
          $position = $row["Position"];
          if ($position == null)
          {
             $position = "";
          }
          $key = $row["KeyName"];
          //print "<tr><td align=center><a href=\"pic.php?Update=delete&Pic=" . htmlentities($pic) . "&Who=" . $key . "\">";
          //print "  <img border=0 src=\"del.gif\"></a>\n";
          print "<tr><td align=center><button type=button onclick='delpp(\"" . htmlentities($pic) . "\",\"" . $key . "\")'>";
          print "     <img border=0 src=\"del.gif\"></button>";
          print "  <td>" . anchor($key);
          print "  <td><input type=text maxlength=40 name=\"POS_" . $key . "\" value=\"" . $position . "\">\n";
          $cnt++;
        }
        print "</table><td valign=top><a href=\"add.php?Pic=" . htmlentities($pic) . "\"><font size=+1>Add Portraits</font></a>\n";
        if ($cnt > 0)
        {
          print "<br><br><button type=submit>Update Positions</button>\n";
        }
        print "</table></form></table>\n";
    }
   
    if (! $quiet)
    {
        print "<p>Link for email (copy both picture and name, then paste in email):\n";
        print "<p><img src=\"" . $dir . "/thumbs/" . $pic . "_thumb" . $suffix . "\"/>\n";
        print "<a href=\"/photos/pic.php?q=1&Pic=" . $pic . "\">\n";
        print "<br/><tt>" . $pic . "</tt></a>&nbsp;&nbsp;<-- Click\n";
    }
   
    db_close();

# show nice "back to" line
function backto($dir)
{
   $pos0 = strpos($dir, "/", 1);
   $pos1 = strpos($dir, "/", $pos0 + 1);
   $dir0 = substr($dir, 1, $pos0 - 1);
   print "<p><i>Back to ... <a href=\"/index.html\">main</a>\n";
   if ($dir0 && $dir0 != "")
   {
      print "... <a href=\"/" . $dir0 . "/index.php\">" . $dir0 . "</a>\n";
   }

   if ($pos1 and $pos1 > 0)
   {
      $dir1 = substr($dir, $pos0 + 1, $pos1 - $pos0 - 1);
      $dir2 = substr($dir, $pos1 + 1);
      print "... <a href=\"/" . $dir0 . "/" . $dir1 . "/index.php\">" . $dir1 . "</a>\n";

      $pos2 = strpos($dir2, "/");
      if ($pos2 and $pos2 > 0)
      {
         $dir3 = substr($dir2, $pos2 + 1);
         $dir2 = substr($dir2, 0, $pos2);
         print "... <a href=\"/" . $dir0 . "/" . $dir1 . "/" . $dir2 . "/index.php\">" . $dir2 . "</a>\n";
         print "... <a href=\"/" . $dir0 . "/" . $dir1 . "/" . $dir2 . "/" . $dir3 . "/index.php\">" . $dir3 . "</a>\n";
      }
      else
      {
         print "... <a href=\"/" . $dir0 . "/" . $dir1 . "/" . $dir2 . "/index.php\">" . $dir2 . "</a>\n";
      }
   }

   print "</i></p>\n";
}

function searchButton()
{
   print "<form action=\"search.php\" target=\"search_pix\" method=\"post\">\n";
   print "  Search for:&nbsp;\n";
   print "  <input type=text name=SrchFor value=\"\" title=\"pattern to match picture name or caption\">\n";
   print "  &nbsp;&nbsp;Directory:\n";
   print "  <input type=text name=Scope value=\"\" title=\"pattern to match directory name\">\n";
   print "  &nbsp;&nbsp;<button type=submit>Search</button>\n";
   print "</form>\n";
}

function spacer($piece)
{
   if ($piece == "")
   {
      return "";
   }
   return $piece . " ";
}

function anchor($key)
{
   $query2 = "SELECT * FROM People WHERE KeyName='" . $key . "'";
   $results2 = mysql_query($query2);
   $person = mysql_fetch_array($results2);
   if ($person)
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
      return "<a href=\"../tree/view.php?Key=" . $k . "\">" . rtrim($name) . "</a>";
   }
   return $key;
}
?>

<p><hr><font size=-1><i>Email: <a href="mailto:steve@oharasteve.com">steve@oharasteve.com</a></i></font>

</body>
</html>

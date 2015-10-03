<html>

<head>
<title>Family Tree</title>

<style type="text/css">
  td.x {
      text-align: center;
      width: 200;
  }
</style>

</head>

<body bgcolor="#f0f0f0">

<?php
   # Connect to database
   include("../db.php");

   $monthArray = array("January", "February", "March", "April", "May", "June", "July", "August",
                  "September", "October", "November", "December");
   $months = array("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
   
   $ancestors = 0;

   $now = getdate();
   $currMon = $now["mon"];    # 1 - 12
   $currDay = $now["mday"]; # 1 - 31
   $currYr = $now["year"];  # e.g., 2004

   $key = $_REQUEST["Key"];
   if ($key == "All")
   {
      $all = TRUE;
      $picsPerRow = 2;
      $query = "SELECT * FROM People ORDER BY YrBorn, MonBorn, DayBorn";
   }
   else
   {
      $all = FALSE;
      $picsPerRow = 4;
      $query = "SELECT * FROM People WHERE KeyName='" . $key . "'";
      print "<i><a href=\"list.php\">Back to people list</a></i>";
   }

   $results = mysql_query($query);
   while ($person = mysql_fetch_array($results)) // Normally just 1
   {
      $key = $person["KeyName"];
      $name = pretty($person);

      $monBorn = $person["MonBorn"];
      $dayBorn = $person["DayBorn"];
      $yrBorn = $person["YrBorn"];
      $whereBorn = $person["WhereBorn"];
      $monDied = $person["MonDied"];
      $dayDied = $person["DayDied"];
      $yrDied = $person["YrDied"];
      $whereBuried = $person["WhereBuried"];
      $motherKey = $person["MotherKey"];
      $fatherKey = $person["FatherKey"];
      $note = $person["Note"];
      $sex = $person["Sex"];
      $alive = $person["Alive"];
      $wiki = $person["WikiTree"];
      $grave = $person["FindAGrave"];

      print "<p><hr>\n\n";
      print "<table width=\"100%\"><tr><td valign=top align=left>\n";
      
      if ($all)
      {
         print "<tt><b><font size=+2>" . $yrBorn . ": " . $key . "</font></b></tt>\n";
      }
      else
      {
         print "<h1>" . $name . "</h1>\n";
      }

      print "<br>Name: " . $name . "\n";

#BORN
      print "<br>Born: " . nicedate($monBorn, $dayBorn, $yrBorn) . "\n";

      if ($whereBorn != "")
      {
         print "<br>Born in " . htmlentities($whereBorn) . "\n";
      }

#DIED
      if ($yrDied and $yrDied != "0")
      {
         print "<br>Died: " . nicedate($monDied, $dayDied, $yrDied) . "\n";

         #AGE DIED?

         if ($whereBuried != "")
         {
             print "<br>Died in " . htmlentities($whereBuried);
         }
      }

#CURRENT CONTACT INFO
      if ($alive and $alive == "Y")
      {
         $query4 = "SELECT * FROM Details WHERE KeyName='" . $key . "'";
         $results4 = mysql_query($query4);
         $curr = mysql_fetch_array($results4);
         if ($curr != FALSE)
         {
            if ($all)
            {
               onerow($curr["CellPhone"], "Cell phone");
               onerow($curr["HomePhone"], "Home phone");
               onerow($curr["WorkPhone"], "Work phone");
               onerow($curr["OtherPhone"], "Other phone");
               onerow($curr["HomeEmail"], "Home email");
               onerow($curr["WorkEmail"], "Work email");
               onerow($curr["OtherEmail"], "Other email");
               onerow($curr["HomeAddress1"], "Home address 1");
               onerow($curr["HomeAddress2"], "Home address 2");
               onerow($curr["HomeAddress3"], "Home address 3");
               onerow($curr["WorkOrSchool"], "Work or school");
               onerow($curr["WorkAddress1"], "Work address 1");
               onerow($curr["WorkAddress2"], "Work address 2");
               onerow($curr["WorkAddress3"], "Work address 3");
            }
            else
            {
               $contact = "../family/edit_details.php?Op=Edit&Key=" . $key;
               print "<br><br><a href=\"" . $contact . "\" target=\"FamilyPhone\">" .
                  "Current phone and address</a> <font size=-1><i>(password rquired)</i></font>\n";
            }
         }
         else if (!$all)
         {
            print "<p><a href=\"../family/edit_details.php?Op=Add&Key=" . $key . "\" target=\"FamilyPhone\">\n";
            print "Add phone and address details</a>&nbsp;&nbsp;";
            print "<font size=-1><i>(Password required)</i></font>";
         }
      }

#HUSBAND OR WIFE?
      if ($sex == "M")
      {
         $spousetype = "Wife";
         $mytype = "Husband";
      }
      else
      {
         $spousetype = "Husband";
         $mytype = "Wife";
      }

      $query1 = "SELECT * FROM Marriages WHERE " . $mytype . "Key='" . $key . "' ORDER BY YrWed";
      $results1 = mysql_query($query1);
      while ($marriage = mysql_fetch_array($results1))
      {
         if ($marriage["Divorced"] != "Divorced")
         {
            print "<br>\n";

            $query2 = "SELECT * FROM People WHERE KeyName='" . $marriage[$spousetype . "Key"] . "'";
            $results2 = mysql_query($query2);
            $spouse = mysql_fetch_array($results2);

            #  ex. Husband: John Doe (1901-2001)
            print "<br>" . $spousetype . ": " . anchor($spouse) . legacy($spouse) . "\n";

            $yrWed = $marriage["YrWed"];
            if ($yrWed and $yrWed != "0")
            {
               $dayWed = $marriage["DayWed"];
               $monWed = $marriage["MonWed"];
               print "<br>Married: " . nicedate($monWed, $dayWed, $yrWed) . "\n";
            }
            $marriedwhere = $marriage["Where"];
            if ($marriedwhere != "")
            {
               print " in " . $marriedwhere . "\n";
            }
         }
      }

#PARENTS
      if ($motherKey != "" or $fatherKey != "")
      {
         print "<br>\n";
      }

#MOTHER
      $mother = FALSE;
      if ($motherKey != "")
      {
          $Mquery = "SELECT * FROM People WHERE KeyName='" . $motherKey . "'";
             $Mresults = mysql_query($Mquery);
          $mother = mysql_fetch_array($Mresults);
          if ($mother == FALSE)
          {
              print "ERROR: No Entry for ". $mother["KeyName"];
          }
          else
          {
              print "<br>Mother: " . anchor($mother) . legacy($mother) . "\n";
          }
      }

#FATHER
      $father = FALSE;
      if ($fatherKey != "")
      {
       $Fquery = "SELECT * FROM People WHERE KeyName='" . $fatherKey . "'";
       $Fresults = mysql_query($Fquery);
       $father = mysql_fetch_array($Fresults);
       if ($father == FALSE)
       {
            print "ERROR: No Entry for ". $father["KeyName"];
       }
       else
       {
             print "<br>Father: " . anchor($father) . legacy($father) . "\n";
       }
      }

#SIBLINGS
      if ($mother or $father)
      {
         $siblings = 0;
         if ($mother)
         {
            if ($father)
            {
               $sel = "FatherKey='" . $father["KeyName"] . "' or MotherKey = '" . $mother["KeyName"] . "'";
            }
            else
            {
               $sel = "MotherKey = '" . $mother["KeyName"] . "'";
            }
         }
         else
         {
               $sel = "FatherKey='" . $father["KeyName"] . "'";
         }
         $Squery = "SELECT * FROM People WHERE " . $sel . " ORDER BY YrBorn, MonBorn";

         $Sresults = mysql_query($Squery);
         while ($sibling = mysql_fetch_array($Sresults))
         {
            if ($sibling["KeyName"] != $key)
            {
               if ($siblings == 0)
               {
                  print "<br>\n";
               }
               $siblings++;

               $half = "";
               if ($mother and $father)
               {
                  if ($father["KeyName"] != $sibling["FatherKey"] or $mother["KeyName"] != $sibling["MotherKey"])
                  {
                     $half = "Half-";
                  }
               }

               if ($sibling["Sex"] == "M")
               {
                    $sib = "Brother";
               }
               else
               {
                    $sib = "Sister";
                }
               print "<br>" . $half . $sib . ": " . anchor($sibling) . legacy($sibling) . "\n";
            }
         }
      }

#CHILDREN
      if ($sex == "M")
      {
         $parenttype = "Father";
      }
      else
      {
         $parenttype = "Mother";
      }

      $Cquery = "SELECT * FROM People WHERE " . $parenttype . "Key='" . $key . "' ORDER BY YrBorn, MonBorn";
      $Cresults = mysql_query($Cquery);
      $kids = 0;
      while ($kid = mysql_fetch_array($Cresults))
      {
           if ($kids == 0)
           {
            print "<br>\n";
           }
           $kids++;
           if ($kid["Sex"] == "M")
           {
              $child = "Son";
           }
           else
           {
              $child = "Daughter";
           }
         print "<br>" . $child . ": " . anchor($kid) . legacy($kid) . "\n";
     }

#NOTE
     if ($note and $note != "")
     {
         print "<br><br>Note: " . htmlentities($note) . "\n";
     }

#GRAVE
     if ($grave and $grave != "")
     {
         print "<br><br>Find-A-Grave: <a href=\"http://www.findagrave.com/cgi-bin/fg.cgi?page=gr&GRid=" . htmlentities($grave) . "\">" . htmlentities($grave) . "</a>\n";
     }

#WEB CONNECTIONS
     $Wquery = "SELECT * FROM WebLinks WHERE KeyName='" . $key . "'";
     $Wresults = mysql_query($Wquery);
     $numlinks = 0;
     while ($item = mysql_fetch_array($Wresults))
     {
           if ($numlinks == 0)
           {
           print "<br>\n";
           }
           $numlinks++;

         $lnk = $item["Link"];
         print "<br>Web link: <a href=\"" . $lnk . "\"><tt>" . $lnk . "</tt></a>\n";
     }

#EDIT
     print "<br><br><form action=\"../family/edit_person.php\" target=\"edit_person\" method=\"get\">\n";
     print "  <input type=hidden name=Key value=\"" . $key . "\">\n";
     print "  <button type=submit>Edit</button> <font size=-1><i>(password required)</i></font>\n";
     print "</form>\n";

#WIKITREE
    if ($wiki != "")
    {
        print "</td><td valign=top align=left>";
        
        print "<iframe width=\"496\" height=\"495\" src=\"http://www.wikitree.com/treewidget/" . $wiki . "\" ";
        print "scrolling=\"no\" frameborder=\"0\" marginheight=\"0\" marginwidth=\"0\"></iframe>\n";
    }

     print "</td></tr></table>\n";

#FACES
     $Fquery = "SELECT * FROM Faces, Photos WHERE Faces.Person='" . $key .
                      "' AND Photos.Photo=Faces.Photo ORDER BY Year";
     $Fresults = mysql_query($Fquery);
     $faces = 0;
     $prevYear = -1;
     while ($face = mysql_fetch_array($Fresults))
     {
         $yrFace = $face["Year"];
         if ($yrFace != $prevYear) {
             $prevYear = $yrFace;

             if (($faces % $picsPerRow) == 0)
             {
                if ($faces == 0 and !$all)
                {
                   print "<hr/><h3>One-a-Year Faces for " . $name . "</h3>\n";
                   print "<p><b><a href=\"faces.php?Key=" . $key . "\">See all Faces for " . $name . "</a></b><br>\n";
                }
                else
                {
                   print "</table>\n";
                }
                print "<br><table><tr valign=top>\n";
             }
             $faces++;

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
             
             print "<td class=x>" . $href . $thumb . "<br>" . htmlentities($caption) . "</a>\n";
          }
      }
      if ($faces != 0)
      {
         print "</table>\n";
      }

#PHOTOS
      $Phquery = "SELECT * FROM PeoplePhotos, Photos WHERE PeoplePhotos.KeyName='" . $key .
                      "' AND Photos.Photo=PeoplePhotos.Photo ORDER BY Year";
      $Phresults = mysql_query($Phquery);
      $photos = 0;
      while ($photo = mysql_fetch_array($Phresults))
      {
         if (($photos % $picsPerRow) == 0)
         {
            if ($photos == 0 and !$all)
            {
               print "<hr/><h3>Portraits of " . $name . "</h3>\n";
            }
            else
            {
               print "</table>\n";
            }
            print "<br><table><tr valign=top>\n";
         }
         $photos++;

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
      }
      if ($photos != 0)
      {
         print "</table>\n";
      }

# GREAT-GREAT-GREAT-GRANDPARENTS
      if (!$all)
      {
         $ancestors = 0;
         grandparents($person, 1, "", "");
         if ($ancestors > 10)
         {
            print "<p>Total direct ancestors = " . $ancestors . ".\n";
         }
      }

# GREAT-GREAT-GREAT-GRANDCHILDREN
      if (!$all)
      {
         grandchildren($person, 1, "", "");
      }
   }

# Create the GED entry for this person
   print "<p><hr>";
   print "Click <a href=\"ged.php?Key=" . $key . "\">here</a> to create a GED entry for WikiTree";

   db_close();

function grandparents($person, $depth, $prefix1, $prefix2)
{
   global $ancestors;
   
   # Safety valve
   if ($depth > 50)
   {
      return;
   }

   $mKey = $person["MotherKey"];
   $fKey = $person["FatherKey"];
   if ($mKey == "" and $fKey == "")
   {
      return;
   }

   if ($depth == 1)
   {
      print "<p><hr><b>Direct Ancestors of " . pretty($person) . "</b>\n";
   }

   if ($mKey != "")
   {
       $query = "SELECT * FROM People WHERE KeyName='" . $mKey . "'";
          $results = mysql_query($query);
       $ma = mysql_fetch_array($results);
       if ($ma != FALSE)
       {
           print "<br><tt>&nbsp;&nbsp;" . $prefix1 . $prefix2 . "M:</tt> " . anchor($ma) . legacy($ma) . "\n";
           grandparents($ma, $depth+1, $prefix1 . ".&nbsp;", $prefix2 . "G");
           $ancestors++;
       }
   }

   if ($depth == 1)
   {
      print "<br>\n";
   }

   if ($fKey != "")
   {
       $query = "SELECT * FROM People WHERE KeyName='" . $fKey . "'";
          $results = mysql_query($query);
       $pa = mysql_fetch_array($results);
       if ($pa != FALSE)
       {
           print "<br><tt>&nbsp;&nbsp;" . $prefix1 . $prefix2 . "F:</tt> " . anchor($pa) . legacy($pa) . "\n";
           grandparents($pa, $depth+1, $prefix1 . ".&nbsp;", $prefix2 . "G");
           $ancestors++;
       }
   }
}

function grandchildren($person, $depth, $prefix1, $prefix2)
{
   # Safety valve
   if ($depth > 50)
   {
      return;
   }

   $sex = $person["Sex"];
   if ($sex == "M")
   {
      $parent = "Father";
   }
   else
   {
      $parent = "Mother";
   }

   $query = "SELECT * FROM People WHERE " . $parent . "Key='" . $person["KeyName"] . "' ORDER BY YrBorn";
   $results = mysql_query($query);
   $count = 0;
   while ($kid = mysql_fetch_array($results))
   {
      if ($depth == 1 and $count == 0)
      {
         print "<p><hr><b>Direct Descendents of " . pretty($person) . "</b>\n";
      }
      $count++;

      if ($kid["Sex"] == "M")
      {
         $sondau = "S";    // Son
      }
      else
      {
         $sondau = "D";    // Daughter
      }
      print "<br><tt>&nbsp;&nbsp;" . $prefix1 . $prefix2 . $sondau . ":</tt> " . anchor($kid) . legacy($kid) . "\n";
      grandchildren($kid, $depth+1, $prefix1 . ".&nbsp;", $prefix2 . "G");
   }
}

function onerow($piece, $nam)
{
   if ($piece and $piece != "")
   {
      print "<br>" . htmlentities($nam) . ": <tt>" . $piece . "</tt>\n";
   }
}

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

function anchor($person)
{
   $k = $person["KeyName"];
   return "<a href=\"view.php?Key=" . $k . "\">" . pretty($person) . "</a>";
}

function legacy($person)
{
   global $months;

   $born = $person["YrBorn"];
   $died = $person["YrDied"];

   if (!$died or $died == "0")
   {
      $mon = $person["MonBorn"];
      if ($mon > 0)
      {
         $day = $person["DayBorn"];
         if ($day > 0)
         {
            return " <font size=-1>(b. " . $months[$mon-1] . " " . $day . ", " . $born . ")</font>";
         }
         return " <font size=-1>(b. " . $months[$mon-1] . " " . $born . ")</font>";
      }
      return " <font size=-1>(b. " . $born . ")</font>";
   }
   else
   {
      return " <font size=-1>(" . $born . "-" . $died . ")</font>";
   }
}

function nicedate($m, $d, $y)
{
   global $monthArray;

   if ($m and $m != "0")
   {
      $mon = $monthArray[$m-1];
      if ($d and $d != "0")
      {
         return $mon . " " . $d . ", " . $y;
      }
      return $mon . ", " . $y;
   }

   return $y;
}

?>

<p><font size=-1><i>Email: <a href="mailto:steve@oharasteve.com">steve@oharasteve.com</a></i></font>

</body>
</html>

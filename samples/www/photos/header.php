<?php
function phdr($ttl, $par1)
{
   hshared($ttl, "photos", $par1, "");
}

function phdr2($ttl, $par1, $par2)
{
   hshared($ttl, "photos", $par1, $par2);
}

function pheader($dir, $ttl)
{
   $pos1 = strpos($dir, "/", 1);
   $pos2 = strpos($dir, "/", $pos1 + 1);
   $pos3 = strpos($dir, "/", $pos2 + 1);

   if ($pos3 === false)	# not found -> just one directory
   {
      $par0 = substr($dir, 1, $pos1 - 1);
      $par1 = substr($dir, $pos1 + 1, $pos2 - $pos1 - 1);
      hshared($ttl, $par0, $par1, "");
   }
   else
   {
      $par0 = substr($dir, 1, $pos1 - 1);
      $par1 = substr($dir, $pos1 + 1, $pos2 - $pos1 - 1);
      $par2 = substr($dir, $pos2 + 1, $pos3 - $pos2 - 1);
      hshared($ttl, $par0, $par1, $par2);
   }
}

function hshared($ttl, $par0, $par1, $par2)
{
   $detail_print = $_REQUEST["Print"] != null;	// Uses only full size images, for printing

   print "<html>\n";
   print "<head>\n";
   print "<title>" . $ttl . "</title>\n";
   print "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">";
   print "<style type=\"text/css\">\n";
   print "  td {\n";
   print "	  text-align: center;\n";
   print "	  width: 240;\n";
   print "  }\n";
   print "  tr {\n";
   print "    vertical-align: top;\n";
   print "  }\n";
   print "  table {\n";
   print "    page-break-inside: avoid;\n";
   print "    vertical-align: top;\n";
   print "    display: inline-table;\n";
   print "    height: 210;\n";
   print "    border-top-style: dotted;\n";
   print "    border-width: thin;\n";
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


   if (! $detail_print)
   {
      print "<p><i>Back to ... <a href=\"/index.html\">main</a>\n";
      if ($par2 == "")
      {
         print "... <a href=\"../../index.php\">" . $par0 . "</a>\n";
         print "... <a href=\"../index.php\">" . $par1 . "</a></i>\n";
      }
      else
      {
         print "... <a href=\"../../../index.php\">" . $par0 . "</a>\n";
         print "... <a href=\"../../index.php\">" . $par1 . "</a>";
         print "... <a href=\"../index.php\">" . $par2 . "</a></i>\n";
      }

      searchButton();

      print "<p><i>Click on a picture to see the full-sized version.\n";
      print "There are very large originals for most photos as well.</i></p>\n";

      print "<hr>\n";
   }
}

function searchButton()
{
   print "<form action=\"/photos/search.php\" target=\"search_pix\" method=\"post\">\n";
   print "  New search:&nbsp;\n";
   print "  <input type=text name=SrchFor value=\"\" title=\"pattern to match picture name or caption\">\n";
   print "  &nbsp;&nbsp;Directory:\n";
   print "  <input type=text name=Scope value=\"\" title=\"pattern to match directory name\">\n";
   print "  &nbsp;&nbsp;<button type=submit>Search</button>\n";
   print "</form>\n";
}

function porig($loc)
{
   # Also be sure to manually update these three:
   #     photos\index.php
   #     photos\year-2008\2008-SJWedding\index.php
   #     photos\year-2009\2009_rob_rob\index.php
   if ($_REQUEST["Print"] == null)
   {
      print "<p>Originals can be found <a href=\"http://www.oharasteven.com/originals/" . $loc . "/index.html\">here.</a></p>";
   }
}
?>

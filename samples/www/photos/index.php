<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Steve O'Hara Family Photo Album</title>
<link rel="stylesheet" type="text/css" href="/photos/photos.css">
</head>

<body bgcolor="#F0F0F0">
<h1>Steve O'Hara Photo Album</h1>
<p>There are tons of pictures out here. I hope you enjoy them! -Steve</p>

<p><a href="/index.html"><font size=-1><i>Back to Steve's Website</i></font></a>

<?php
  include("count.php");
  init();
  
  subdir("year-2015", "IMG_20150103_154036", "2015 Photos");
  subdir("year-2014", "2014-01-20_Robin_1", "2014 Photos");
  subdir("year-2013", "jan13a_003", "2013 Photos");
  subdir("year-2012", "feb10a_013", "2012 Photos");
  subdir("year-2011", "2011-01-04_Ally_swooshing", "2011 Photos");
  subdir("year-2010", "rosa_bernhard", "2010 Photos");
  subdir("ScanMyPhotos", "C12-0842", "ScanMyPhotos");
  subdir("year-2009", "pop_and_david_peering", "2009 Photos");
  subdir("year-2008", "SJ2008_matt_117", "2008 Photos");
  subdir("year-2007", "20070108_Lincolns_View", "2007 Photos");
  subdir("shane_jamie", "sja_jamie_and_i", "Shane and Jamie");
  subdir("year-2006", "feb06_group_shot", "2006 Photos");
  subdir("year-2005", "jul05_078", "2005 Photos");
  subdir("year-2004", "Group_Shot_1", "2004 Photos");
  subdir("years-2001-2003", "cirque003", "2001 to 2003 Photos");

  print("<table><tr><td><a href=\"/videos/index.html\">");
  print("  <img class=mult src=\"/videos/Robin_Dragonettes/thumbs/Last_Mar_28_2003.jpg\" width=175 title=\"Some very large video clips\">");
  print("  <br>2000-2003 Family Videos</a></tr></table>");

  subdir("years-1996-2001", "apr00_P0000371", "1996 to 2001 Photos");
  subdir("years-1990-1996", "9091_pg078_daisy_punchbowl_hawaii", "1990 to 1996 Photos");
  subdir("years-1980-1990", "36_shane_robin_and_lance5", "1980 to 1990 Photos");
  subdir("pre-1980-ohara", "unknown_dad", "Steve Pre-1980 Photos");
  subdir("pre-1980-johnson", "Daisy_portrait", "Daisy Pre-1980 Photos");
  subdir("unsorted", "two_for_lunch", "Unsorted Photos");
  subdir("Books", "ArtOHara_Front", "Custom Books");

  done();
?>

<hr/><form action=screensaver.php method=post>
   <input type=submit value="Download photo captions to a screensaver.xml file (slow)"/>
</form>

<p>Click <a href="/originals/index.html">here</a> for some of the original scans and digital photos.</p>

<p>Click <a href="/collages/index.html">here</a> for some one page printable collages.</p>

<p>Click <a href="countpix.php">here</a> for some photo statistics.</p>

<p>Click <a href="pdf/index.html">here</a> for some printable PDF versions of these photos (not very useful).</p>

<p>Click <a href="/tree/allPortraits.php">here</a> to see everybody's Portraits, all at once.</p>

<p>Click <a href="https://photos.google.com/search">here</a> to search Google+ for people, places, things.</p>

<p>Click <a href="/Pix/index.html">here</a> for some screensaver pictures.</p>

<p><font size=-1><i>Last Updated on 7/6/2015
<br/>Email: <a href="mailto:steve@oharasteve.com">steve@oharasteve.com</a></i></font></p>

</body>
</html>

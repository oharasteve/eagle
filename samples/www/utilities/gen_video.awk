BEGIN {
  print "<html>";
  print "<head>";
  print "  <style type=\"text/css\">";
  print "    table { display:inline-table; page-break-inside: avoid; }";
  print "  </style>";
  print "  <title>Videos - " DIR "</title>";
  print "<script src=\"../vid.js\" language=\"javascript\"></script>";
  print "</head>";
  print "<body>";
  print "<center>";
  print "<h1>Videos - " DIR "</h1>";
  print "";
  print "<table cellpadding=\"10\" border=\"1\">";
  print "  <tr>";
  print "    <td bgcolor=\"#d0d0d0\"><center><b><font size=\"+1\">Play Video<br>(Pretty Fast !!!)</font><b></td>";
  print "    <td bgcolor=\"#d0d0d0\"><center><b><font size=\"+1\">Download Video<br>(Warning: SLOW !!!)</font><b></td>";
  print "  </tr>";
  print "";
  print "<script language=\"javascript\">";
}

/.avi/ || /.wmv/ || /.mpg/ || /.mp4/ || /.3gp/ {
  siz = substr($0, 25, 6)
  nam = substr($0, 40, length($0)-40-3);
  suff = substr($0, length($0)-3, 4);
  print "  vid(\"x\", \"" nam "\", \"" suff "\", \"" siz " MB (x:xx)\");";
}

END {
  print "</script>";
  print "";
  print "</table>";
  print "";
  print "</center>";
  print "</body>";
  print "</html>";
}

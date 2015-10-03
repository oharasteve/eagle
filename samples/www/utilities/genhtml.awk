BEGIN {
  print "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r";
  print "<html xmlns=\"http://www.w3.org/1999/xhtml\">\r";
  print "<head>\r";
  print "<style type=\"text/css\">\r";
  print "   table { display:inline-table; page-break-inside:avoid; }\r";
  print "   td { padding:5px; text-align:center; }\r";
  print "</style>\r";
  print "<title>Originals - " DIR "</title>\r";
  print "</head>\r";
  print "<body>\r";
  print "<center>\r";
  print "<h1>Originals - " DIR "</h1>\r";
}

/.jpg/ || /.JPG/ || /.png/ || /.PNG/ || /.gif/ || /.GIF/ {
  nam = substr($0, 0, length($0)-4);
  suff = substr($0, length($0)-3, 4);
  print "<table><tr><td><a href=\"" nam suff "\">\r";
  print "  <img src=\"thumbs/" nam "_thumb" suff "\"/><br/><font size=-1>" nam suff "</font></a>\r";
  print "</td></tr></table>\r";
  print "\r";
}

/.jpeg/ || /.JPEG/ {
  nam = substr($0, 0, length($0)-5);
  suff = substr($0, length($0)-4, 5);
  print "<table><tr><td><a href=\"" nam suff "\">\r";
  print "  <img src=\"thumbs/" nam "_thumb" suff "\"/><br/><font size=-1>" nam suff "</font></a>\r";
  print "</td></tr></table>\r";
  print "\r";
}

END {
  print "</center>\r";
  print "</body>\r";
  print "</html>\r";
}

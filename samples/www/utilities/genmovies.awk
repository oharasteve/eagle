BEGIN {
  print "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r";
  print "<html xmlns=\"http://www.w3.org/1999/xhtml\">\r";
  print "<head>\r";
  print "<title>Archival Movies</title>\r";
  print "</head>\r";
  print "<body>\r";
  print "<center><table border=1 cellpadding=5>\r";
  print "  <tr><td>Movie<td align=center>Date<td align=right>Size\r";
}

!/index.html/ && substr($0, 3, 1) == "/" && substr($0, 40, 1) != "." {
  print "  <tr><td><a href=\"" substr($0,40,length($0)-39) "\">" substr($0,40,length($0)-39) "</a>";
  print "    <td>" substr($0,1,10);
  print "    <td align=right>" substr($0,28,12);
}

END {
  print "</table></center>\r";
  print "</body>\r";
  print "</html>\r";
}

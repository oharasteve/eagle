BEGIN {
  pic = "?";
  suff = ".jpg";
  path = ARGV[1];
  sub(".*\\\\photos\\\\", "", path);
  sub("\\\\\[^\\\\\]*$","", path);
  gsub("\\\\","/",path);
}

/mailto:/ {
  next;
}

/class=mult/ {
  exit;
}

/^<td><a href=/ {
  pos = index($0, ".jpg");
  suff = ".jpg";
  if (pos == 0) {
    pos = index($0, ".JPG");
    suff = ".JPG";
  }

  if (pos != 0) {
    pic = substr($0, 14, pos-14);
  }
  else {
    pic = "?";
  }
}

/<br>.*<\/a>/ && pic != "?" {
  p1 = index($0, "<br>");
  p2 = index($0, "</a>");
  cap = substr($0, p1+4, p2-p1-4);
  print "\"" path "\",\"" pic "\",\"" suff "\",0,0,0,\"" cap "\"\r";
}
BEGIN {
  dir = "";
  cnt=0;
}

/^ Direct/ {
  dir = substr($4, 14);
}

/^BREAK/ {
  cnt++;
  print "  ", cnt;
}

/jpg$/ || /JPG$/ {
  printf("%10s %5s %2s %8s  %s\\%s\n", $1, $2, $3, $4, dir, $5);
}
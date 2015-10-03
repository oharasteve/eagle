BEGIN {
  jpg = 0;
  mpeg = 0;
}

!/href/ {
  next;
}

/\.jpg/ || /\.JPG/ {
  jpg++;
}

/\.mpg/ || /\.MPG/ || /\.mpeg/ || /\.MPEG/ || /\.mov/ || /\.MOV/ {
  mpeg++;
}

END {
  printf("    jpg=%5d mpeg=%3d\r\n", jpg, mpeg);
}
BEGIN {
  prev = "?";
  cnt = 0;
}

{
  curr = toupper($1);
  if (prev == curr) {
    cnt++;
    print $1;
  }
  prev = curr;
}
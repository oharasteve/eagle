BEGIN {
    dir = "NoWay";
    total = 0;
    print "s+@NOTE@+DO NOT MODIFY THIS FILE. Please edit index0.html.+";
}

/ Directory of / {
    pos = index($0, "\\originals\\");
    dir = substr($0, pos+11, length($0)-13);
    dir2 = dir;
    gsub(".*\\\\", "", dir2);
}

/File\(s\)/ {
    if (index(dir,"thumbs") > 0) next;
    if (index(dir," of ") > 0) next;
    if (dir == "_notes") next;
    cnt = $1 - 1;
    if (cnt > 0) {
        total += cnt;
	      print "s+>" dir2 "<+>" dir2 " (" cnt ")<+;";
    }
}

END {
    left = total / 1000;
    right = total % 1000;
    printf("s+Total = TBD+Total = %d,%3.3d+;\n", left, right);
}
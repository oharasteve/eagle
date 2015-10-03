BEGIN {
    nxt = 0;
    orphans = 0;
    problems = 0;
    missings = 0;
}

nxt == 1 {
    print;
    nxt = 0;
}

/Problem at/ {
    print;
    nxt = 1;
    problems++;
}

/^*** Orphan: / {
    orphans++;
}

/^*** Missing/ {
    missings++;
    print;
}

END {
    print "";
    print "Problems = " problems;
    print "Orphans  = " orphans;
    print "Missing  = " missings;
}
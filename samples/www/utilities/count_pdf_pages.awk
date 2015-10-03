BEGIN {
    pages = 0;
}

/Type.*Page[^s]/ {
    pages++;
}

END {
    print "{i}pages=" pages "{/i}{/font}{/a}";
}
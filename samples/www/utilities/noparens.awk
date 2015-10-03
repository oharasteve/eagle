{
  before = substr($0,2,length($0)-3);
  after = before;
  gsub(/ \(/,"_",after);
  gsub(/\(/,"_",after);
  gsub(/\)/,"",after);
  print "move \"" before "\" \"" after "\"";
}
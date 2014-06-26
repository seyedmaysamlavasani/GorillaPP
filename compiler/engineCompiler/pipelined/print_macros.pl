#!/usr/bin/perl
#if (%ARGV != 0) {
#  print "Error: This program needs one argument as the name of the module\n";
#} else {
#  $moduleName = @ARGV[0];
#}

while (my $line = <STDIN>) {
  if ($line =~ /\W*#define\W*(\w*).*/) {
    print $1 . "\n";
  }
}

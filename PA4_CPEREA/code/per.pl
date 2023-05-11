# Calicia Perea
# March 21 23
# Language :Perl
#   The pattern is the corrupt data ALWAYS starts with a CONTROL-C 
#   and always ends in a CONTROL-B, and that at most 4 carriage returns 
#   are introduced with  such an event.  

#!/usr/bin/perl

use strict;
use warnings;

#Take in input file

my $input = 'control-char.txt';
open (FH,'<', $input) or die $!;

# Store in output 
open (FW,'>', "perlout.txt") or die $!;

#Declaring variables to tell precisely if control starts with ^C
# and ends with ^B
my $opC = 1; #false
my $endC = 1; # false

# read input file
my $line = <FH>;

#For loop used to convert characters to decimal 
#End of text for ^C == 0x03 in ASCII or 3 in decimal 
#Start of text for ^B == 0x02 in ASCII or 2 in decimal
#Characters in btwn are not printed
while( $line){
    my $str = $line;
    my @spl = split('', $str); 
    #displays result using foreach loop
    foreach my $i (@spl) {
        #conditiono after ^C happens
        if(ord($i)==3 and $opC == 1){
            $opC = 0;
            $endC = 1;
        }
        #condition after ^B happens
        if(ord($i)==2 and $opC == 0){
            $opC = 1;
            $endC = 0;
        }
        #Print file, except what is inbtwn ^C and ^B
        if($opC == 1 and $endC == 1){
            print $i;
            $a = $i;
            print FW $a;
        }
        if($endC == 0){
            $endC = 1;
        }
    }
    $line = <FH>;
}
close(FW) or die $!;
close(FH) or die $!;

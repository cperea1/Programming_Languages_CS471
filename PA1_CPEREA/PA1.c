/*
Calicia Perea
Programming Languages- PA1


Problem Description
Created a C program that fills an integer array with integers and casts a string out. 
output should print my first and last name (Calicia Perea) with proper indentation and Capitalization. 

intput: ASCII letters of characters in my name with little endian
output: my name printed out eith propper capitalization and spacing.


*/

#include "stdio.h"

void main(){
  int A[4];
  char *S;
  
  A[0]= (105 * 256 *256 * 256) + (108*256* 256) + (97* 256) + (67);
  A[1]= (32*256* 256 * 256)+(97*256*256)+(105*256) +(99);
  A[2]= (101*256* 256*256) + (114* 256* 256) + (101* 256)+ (80);
  A[3]= (0*250)+(97);
  S = (char *)&A;
  printf("My name is %s\n" ,S) ;
}
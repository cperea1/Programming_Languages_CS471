# Calicia Perea
# March 21 23
# Language :Python
#   The pattern is the corrupt data ALWAYS starts with a CONTROL-C 
#   and always ends in a CONTROL-B, and that at most 4 carriage returns 
#   are introduced with  such an event.  

import sys
def main():
    #  Taking in the input file 
    input = open("control-char.txt", 'r')

    #   Storing in the output file
    output = open('python.txt','w')

    #Declaring variables to tell precisely if control starts with ^C
    # and ends with ^B
    opC = False
    endC= False

    #Read input file
    line = input.read()

    #For loop used to convert characters to decimal 
    #End of text for ^C == 0x03 in ASCII or 3 in decimal 
    #Start of text for ^B == 0x02 in ASCII or 2 in decimal
    #Characters in btwn are not printed
    for i in line:
        if not line:
            break
        #conditiono after ^C happens
        if ord(i)==3 and opC == False:
            opC = True
            endC = False

        #condition after ^B happens
        if ord(i) == 2 and opC == True:
            opC = False
            endC = True
        
        #Print file, except what is inbtwn ^C and ^B
        if opC == False and endC == False:
            sys.stdout.write(i)
            output.write(i)

        if endC ==True:
            endC = False

    input.close()
    output.close()

if __name__ == "__main__":
    main()

        


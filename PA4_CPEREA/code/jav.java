/* 
Calicia Perea
March 21, 2023
PA4: Java program 
Language :java 
  The pattern is the corrupt data ALWAYS starts with a CONTROL-C 
  and always ends in a CONTROL-B, and that at most 4 carriage returns 
  are introduced with  such an event.  
*/ 
import java.io.*;
import java.util.*;

public class jav {
    public static void main(String[] args){
        try{
            // input file
            FileReader input = new FileReader("control-char.txt");
            Scanner scan = new Scanner(input);

            //store ouput file
            FileWriter output = new FileWriter("jav.txt");

            //Declaring variables to tell precisely if control starts with ^C
            // and ends with ^B
            boolean opC = false;
            boolean endC= false;

            String line;

            while(scan.hasNextLine()){
                line= scan.nextLine();
                int length = line.length();


                //For loop used to convert characters to decimal 
                //End of text for ^C == 0x03 in ASCII or 3 in decimal 
                //Start of text for ^B == 0x02 in ASCII or 2 in decimal
                //Characters in btwn are not printed
                for (int i = 0; i<length; i++){
                    //condition after ^C happens
                    if((int)line.charAt(i)== 3 && opC == false){
                        opC= true;
                        endC = false;
                    }
                    //condition after ^B happens
                    if((int)line.charAt(i)== 2 && opC == true){
                        opC= false;
                        endC= true;
                    }
                    //printing file except what is inbtwn ^C and ^B
                    if(opC == false && endC == false){
                        System.out.print(line.charAt(i));
                        output.write(line.charAt(i));
                    }

                    if(endC == true)
                        endC = false;
                

            }
            if (opC == false){
                System.out.println();
                output.write("\n");
            }
        }
        input.close();
        output.close();
        scan.close();

    } catch(IOException e){
        e.printStackTrace();
        System.exit(1);
    }
}
}


import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Read2 {
    
        public void read(String fileName){
            String line = null;
            int num;
            New2<Integer,Integer> n2=new New2<Integer,Integer>();
            String[] elems = new String[2];

            try {                                                             // the buffered reader will read the input file line by line
                FileReader fileReader = new FileReader(fileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                num=Integer.parseInt(bufferedReader.readLine());

                while((line = bufferedReader.readLine()) != null && num > 0) {
                    elems = line.split(" ");
                    n2.put(Integer.parseInt(elems[0]), Integer.parseInt(elems[1]));
                    num--;
                }   

                bufferedReader.close(); 
                
               
            Scanner s = new Scanner(System.in);
            String[] lines;
           
            while(!(lines = s.nextLine().toString().split(" "))[0].equals("quit"))
                {
               
                if(lines[0].toString().equals("increase")){
                    
                    n2.increase(Integer.parseInt(lines[1]),Integer.parseInt(lines[2]));
                    
                }
                else if(lines[0].toString().equals("reduce")){
                   
                    n2.reduce(Integer.parseInt(lines[1]),Integer.parseInt(lines[2]));
                }
                else if(lines[0].toString().equals("count")){
                    System.out.println(n2.count(Integer.parseInt(lines[1])));
                }
                else if(lines[0].toString().equals("previous")){
                    System.out.println(n2.previous(Integer.parseInt(lines[1])));
                }
                else if(lines[0].toString().equals("next")){
                    System.out.println(n2.next(Integer.parseInt(lines[1])));
                }
                else if(lines[0].toString().equals("inrange")){
                    System.out.println(n2.inRange(Integer.parseInt(lines[1]),Integer.parseInt(lines[2])));
                }
                
                
                
            }
                
            }
            catch(FileNotFoundException ex) {
                System.out.println(
                    "Unable to open file '" + 
                    fileName + "'");                
            }
            catch(IOException ex) {
                System.out.println(
                    "Error reading file '" 
                    + fileName + "'");                  
               
            }
        }
        
    }


    


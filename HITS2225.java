/**Algorithms:
Start with each node having a hub score and authority score of 1.
Run the Authority Update Rule
Run the Hub Update Rule
Normalize the values by dividing each Hub score by square root of the sum of the squares of all Hub scores, and dividing each Authority score by square root of the sum of the squares of all Authority scores.
Repeat from the second step as necessary./*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.Scanner;

/**
 *
 * @author imame
 */
public class HITS2225 {

    /**
     * @param args the command line arguments
     */
    
    // Initializ2 authority and hub value
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        //File filename = new File("C:\\Users\\imame\\Desktop\\hits.txt");
        int A[][];
        String st;
        String initialvalue = args[1];
        String filename = args[2];
        String iterations = args[0];
        BufferedReader br = new BufferedReader(new FileReader(filename));
        //int iterations1 = 7;
        int iterations1 = Integer.parseInt(iterations);
        //int initialvalue1 = -1;
        Double initialvalue1 = Double.parseDouble(initialvalue);
        String[] line = br.readLine().split(" ");
        int vtc = Integer.parseInt(line[0]);
        int edg = Integer.parseInt(line[1]);
        A = new int[vtc][vtc];
        int counter  = 0;
        while ((st = br.readLine()) != null && counter < edg)
        {
            counter += 1;
            String[] ip = st.split(" ");
            A[Integer.parseInt(ip[0])][Integer.parseInt(ip[1])] = 1;
        }    
        if(vtc > 10)
        {
          iterations1 = 0.0;
          initilvalue1 = -1;
        }   
        
        double[] hub = new double[vtc];
        double[] authority = new double[vtc];
       //System.out.println(A);
       // initialize hub and authority
       if(initialvalue1 == 0)
       {
       for(int i = 0; i < vtc; i++)
       {
           hub[i] = 0;
           authority[i] = 0;
       }
       }
       else if(initialvalue1 == 1)
       {
          for(int i = 0; i < vtc; i++)
       {
           hub[i] = 1;
           authority[i] = 1;
       } 
       }
       else if(initialvalue1 == -1)
       {
           for(int i = 0; i < vtc; i++)
       {
           hub[i] = (1.0/vtc);
           authority[i] = (1.0/vtc);
       }  
       }
       else if(initialvalue1 == -2)
       {
             for(int i = 0; i < vtc; i++)
       {
           hub[i] = (1.0/Math.sqrt(vtc));
           authority[i] = (1.0/Math.sqrt(vtc));
       }  
       }
       // initialvalue condition
       Double errorate = 0.0;
       if(iterations1 == 0)
       {
           errorate = Math.pow(10,-5);
       }
       // iteration rule
       else if(iterations1 < 0)
       {
           errorate = Math.pow(10,iterations1);
       }
       System.out.println(+errorate);
       for(int  i = 0; i <vtc ; i++)
       {
           System.out.println("Base  : A/H[" +i+ "]=" +authority[i]+"/"+hub[i] );
       }
       if (errorate == 0.0)
       {
           for(int i = 0 ; i < iterations1; i++)
           {
               double norm = 0;
               for(int col = 0;col < vtc ; col++)
               {
                   double authsc = 0;
                   for(int row = 0; row < vtc ; row++)
                    {
                        if (A[row][col] == 1)
                        {
                            authsc += hub[row];
                        }
                    }
                   authority[col] = authsc;
                   norm += authsc*authsc;
               }
               norm = Math.sqrt(norm);
               for(int m = 0; m < vtc; m++)
               {
                   authority[m] = authority[m]/norm;
               }
               double norm1 = 0;
               for(int row = 0;row < vtc ; row++)
               {
                   double hubsc = 0;
                   for(int col = 0; col < vtc ; col++)
                    {
                        if (A[row][col] == 1)
                        {
                            hubsc += authority[col];
                        }
                    }
                   hub[row] = hubsc;
                   norm1 += hubsc*hubsc;
               }
               norm1 = Math.sqrt(norm1);
               for(int m = 0; m < vtc; m++)
               {
                   hub[m] = hub[m]/norm1;
               }
               System.out.printf(" Iter : %d",(i+1));
               for(int j = 0;j < vtc; j++)
               {
                   System.out.printf("  A/H[%d] = %f/%f",j,authority[j],hub[j]);                   
               }
               System.out.println();
           }
           
       }
       else
       {
           int diff[];
           double max = 1.0;
           while(max > errorate)
           {    
               int count = 0;
               count += 1;
               double[] oldHub = new double[vtc];
               double[] oldAuth = new double[vtc];
               oldHub = hub.clone();
               oldAuth = authority.clone();
               double norm = 0;
               for(int col = 0;col < vtc ; col++)
               {
                   double authsc = 0;
                   for(int row = 0; row < vtc ; row++)
                    {
                        if (A[row][col] == 1)
                        {
                            authsc += hub[row];
                        }
                    }
                   authority[col] = authsc;
                   norm += authsc*authsc;
               }
               norm = Math.sqrt(norm);
               for(int m = 0; m < vtc; m++)
               {
                   authority[m] = authority[m]/norm;
               }
               double norm1 = 0;
               for(int row = 0;row < vtc ; row++)
               {
                   double hubsc = 0;
                   for(int col = 0; col < vtc ; col++)
                    {
                        if (A[row][col] == 1)
                        {
                            hubsc += authority[col];
                        }
                    }
                   hub[row] = hubsc;
                   norm1 += hubsc*hubsc;
               }
               norm1 = Math.sqrt(norm);
               for(int m = 0; m < vtc; m++)
               {
                   hub[m] = hub[m]/norm1;
               }
               
               max = Math.abs(authority[0] - oldAuth[0]);
               for(int i=0; i<authority.length; i++)
               {
                    if(Math.abs(authority[i] - oldAuth[i])>max)
                        max = Math.abs(authority[i] - oldAuth[i]);
               }
               for(int i=0; i<hub.length; i++)
               {
                    if(Math.abs(hub[i] - oldHub[i])>max)
                       max = Math.abs(authority[i] - oldAuth[i]);
      
               }
               System.out.printf("Iter : %d",(count+1));
               for(int j = 0; j < vtc ; j++)
               {
                   System.out.printf(" A/H[%d] = %f/%f",j,authority[j],hub[j]);                   
               }
               System.out.println();
            }
           }
               
           }
}
       
    
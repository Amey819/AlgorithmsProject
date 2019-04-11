// Amey Pai Raiker cs610 ap2225 prp

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;
public class PGRK2225 {
  public static void main(String[] args) throws IOException{
      //File file = new File("C:\\Users\\imame\\Desktop\\hits.txt");
      int[][] A;              //  Create adjacency matrix A
      String st;
      double d = 0.85;   
      String initialvalue1 = args[1];
      String filename = args[2];
      String iterations1 = args[0];
      int iterations = Integer.parseInt(iterations1);
      BufferedReader br = new BufferedReader(new FileReader(filename));
      Double initialvalue = Double.parseDouble(initialvalue1);
      String[] line = br.readLine().split(" ");
        int vtc = Integer.parseInt(line[0]);
        int edg = Integer.parseInt(line[1]);
      A = new int[vtc][vtc];  
      int counter = 0;
        while ((st = br.readLine()) != null && counter < edg)
        {
            counter += 1;
            String[] ip = st.split(" ");
            A[Integer.parseInt(ip[0])][Integer.parseInt(ip[1])] = 1;
        }    
     if(vtc > 10)
     {
       iterations = 0.0;
       initialvalue = -1;
     }
    /*Scanner s = new Scanner(System.in);
    System.out.println("Input the initialvalue , iteration respectively");
    String init = s.nextLine();
    String[] initarray = init.split(" ");
    int[] array1 = new int[initarray.length];
    for(int  i = 0; i < initarray.length;i++)
    {
      array1[i] = Integer.parseInt(initarray[i]);
    }
    
    int initialvalue = array1[0];
    int iterations = array1[1];

    System.out.println("Enter no of nodes and edges");
    String input = s.nextLine();   
    double d = 0.85;   
    String[] strArray = input.split(" "); // split the input string into vertices and edges
    int[] intArray = new int[strArray.length];
    for(int i = 0; i < strArray.length; i++) {
      intArray[i] = Integer.parseInt(strArray[i]);
    }
    int vtc = intArray[0];
    int edg = intArray[1];

   
    System.out.println("vertices: " + vtc + " edge: " + edg);
    for(int i = 0; i < vtc;i++)
    {
      for(int j = 0; j<vtc;j++)
      {
        A[i][j] = 0;               // empty matrix
      }
    }
        */
    /*for(int i = 0; i < edg ; i++ )
    {
      String e = s.nextLine();
      String[] str = e.split(" ");
      int[] a = new int[str.length];
      for(int j = 0; j < str.length; j++) {
        a[j] = Integer.parseInt(str[j]);
      }
      int p = a[0];
      int q = a[1];
      A[p][q] = 1;                //   Create edges on the matrix
    }
    // ---------- system input taken -----------
    System.out.println("A:"+Arrays.deepToString(A));
*/
   // Array of probablities
   // value of PR will be according to the initialvalue
  double[] PR = new double[vtc];  // Create PR to store the pageranks
  if(initialvalue == 1)          // conditions to check initialvalue
  {
    for(int i = 0;i < vtc; i++)
   {
     PR[i] = 1.0;
   }
  }
  else if(initialvalue == -1) // if -1 then 1/n
  {
    for(int i = 0;i < vtc; i++)
   {
     PR[i] = (1.0/vtc);
   }
  }
  else if(initialvalue == 0)
  {
    for(int i = 0;i < vtc; i++)
   {
     PR[i] = 0.0;
   }
  }
  else if(initialvalue == -2)   // if -2 then 1/sqrt(n)
  {
    for(int i = 0;i < vtc; i++)
   {
     PR[i] = (1.0/(Math.sqrt(vtc)));
   }
  }
  else{
    System.out.println("Pleae enter initialvalue as 0,1,-1 or -2 only");
  }
    System.out.printf("Base :");
    for(int  i = 0; i <vtc ; i++)
       {
           System.out.printf("   PR["+i+"] = " +PR[i]); 
       } 
       System.out.println();
   //count of all outgoing nodes
   int[] C = new int[vtc];              // count of all the outgoing links
   for(int rows = 0; rows < vtc ; rows++)
   {
     for(int col = 0; col < vtc ; col++)
     {
       if(A[rows][col] == 1)
       {
         C[rows] += 1;
       }
     }
   }
   
   
   //System.out.println("C:"+Arrays.toString(C));
   // find pageranks
   double errorrate = 0.0;
   if(iterations == 0){
     errorrate = Math.pow(10,-5);
   }
   else if(iterations < 0)
   {
     errorrate = Math.pow(10,iterations);
   }
   else if(iterations > 0)
           {
               errorrate = 0.0;
           }
   else{
       System.out.println("Please enter the no of iterations or the errorate");
   }
   if (errorrate == 0.0) // if iteration is positive
   {
   for (int i = 0 ; i < iterations; i++)
   {
   double[] Temp = PR.clone();
   for(int col = 0; col < vtc; col++)
   {
   double pr = 0.0;
     for (int row = 0; row < vtc ; row++)
     {
       if(A[row][col] == 1) 
       {
         pr += (Temp[row]/C[row]); // Pagerank formula
       }
     }
     pr = ((1-d)/vtc) + (d*pr);
     PR[col] = pr;
   } 
   if(i < 10){
   System.out.printf("Iter: %d",(i+1));
   for(int j = 0;j < vtc; j++)
               {
                   System.out.printf("  PR[%d] = %f",j,PR[j]);                   
               }
               System.out.println();
   }
   else{
   System.out.printf("Iter: %d",(i+1));
   for(int j = 0;j < vtc; j++)
               {
                   System.out.printf(" PR[%d] = %f",j,PR[j]);                   
               }
               System.out.println();
   }
   }
   }
   else{     // else if iteration is negative
   double max = 1.0;
   int count = 0;
   while(max > errorrate){    // if convergence test passed
     double prevPR[] = new double[vtc];
     prevPR = PR.clone();        // to store all the oldPR values to compare ahead
     count += 1;
      for(int col = 0; col < vtc; col++)
      {
      double pr1 = 0;
        for (int row = 0; row < vtc ; row++)
        {
          if(A[row][col] == 1)  // Has a link pointed to it 
          {
            pr1 += (prevPR[row]/C[row]); // Formula to get pageranks
          }
        }
        pr1 = ((1-d)/vtc) + (d*pr1);
        PR[col] = pr1;
      } 
      max = Math.abs(PR[0] - prevPR[0]);  // get max value such that if this is less than errorate other values are understandably lower than errorate.
      for(int i=0; i< vtc; i++)
      {
        if(Math.abs(PR[i] - prevPR[i]) > max)
        {
            max = Math.abs(PR[i] - prevPR[i]);
        } 
      }
      if(count < 10)
      {
      System.out.printf("Iter: %d",(count));
      for(int j = 0; j < vtc ; j++)
               {
                   System.out.printf(" PR[%d] = %f",j,PR[j]);                    
               }
               System.out.println();
     }   
     else{
      System.out.printf("Iter: %d",(count));
      for(int j = 0; j < vtc ; j++)
               {
                   System.out.printf("PR[%d] = %f",j,PR[j]);                    
               }
               System.out.println();
     }
     
   }
}
}
}

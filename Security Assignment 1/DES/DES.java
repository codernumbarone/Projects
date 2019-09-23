import java.io.FileReader;
import java.util.Scanner;


public class DES {

	
	

public static void main(String[] args) {

	//Scanner message = new Scanner(new FileReader("C/Users/PrettySA/Desktop/GradientDescent.txt"));
	//String[] s1 = permutationTable(message.next());
	//String[] s1 = permutationTable("doneee?");
	//String[] s2 = permutationKey(key.next());
	//String[] s2 = permutationKey("3aaah");
	//String boo2 = transpose(s2);
	//String[] boo4 = keyGeneration(boo2); 
	//String boo5 = fRound(s1);
	//String boo6 = ArrayToString(finalPermutation(boo5));
System.out.println("hi");
	String message ="pretty";
    int[] key = {0 ,1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1};

}


private static int[] xor (int[] input1, int[] intput2){
       int[] output = new int[input1.length];
       
       for(int i = 0; i < input1.length; i++){
           output[i] = (input1[i]+intput2[i]) % 2;
       }
       return output;
   }



//public static void main(String args[]){	
	
 //   int[] key = {0 ,1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1};


 //   }

//pc1 table
	private static int[]  Pc1 = {
        57, 49, 41, 33, 25, 17, 9,
        1,  58, 50, 42, 34, 26, 18,
        10, 2,  59, 51, 43, 35, 27,
        19, 11, 3,  60, 52, 44, 36,
        63, 55, 47, 39, 31, 23, 15,
        7,  62, 54, 46, 38, 30, 22,
        14, 6,  61, 53, 45, 37, 29,
        21, 13, 5,  28, 20, 12, 4};
	 private static byte[] P = {
		        16, 7,  20, 21,
		        29, 12, 28, 17,
		        1,  15, 23, 26,
		        5,  18, 31, 10,
		        2,  8,  24, 14,
		        32, 27, 3,  9,
		        19, 13, 30, 6,
		        22, 11, 4,  25
		    };
	
	static int[] ipMessage = { 58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36,
			28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32,
			24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19,
			11, 3, 61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7 };
	
	static int[] expansionTable = { 32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9,
			10, 11, 12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20,
			21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1 };
	
	 private static byte[][] S = {
		        {14, 4,  13, 1,  2,  15, 11, 8,  3,  10, 6,  12, 5,  9,  0,  7,
		        0,  15, 7,  4,  14, 2,  13, 1,  10, 6,  12, 11, 9,  5,  3,  8,
		        4,  1,  14, 8,  13, 6,  2,  11, 15, 12, 9,  7,  3,  10, 5,  0,
		        15, 12, 8,  2,  4,  9,  1,  7,  5,  11, 3,  14, 10, 0,  6,  13},
		    
		        {15, 1,  8,  14, 6,  11, 3,  4,  9,  7,  2,  13, 12, 0,  5,  10,
		        3,  13, 4,  7,  15, 2,  8,  14, 12, 0,  1,  10, 6,  9,  11, 5,
		        0,  14, 7,  11, 10, 4,  13, 1,  5,  8,  12, 6,  9,  3,  2,  15,
		        13, 8,  10, 1,  3,  15, 4,  2,  11, 6,  7,  12, 0,  5,  14, 9},
		    
		        {10, 0,  9,  14, 6,  3,  15, 5,  1,  13, 12, 7,  11, 4,  2,  8,
		        13, 7,  0,  9,  3,  4,  6,  10, 2,  8,  5,  14, 12, 11, 15, 1,
		        13, 6,  4,  9,  8,  15, 3,  0,  11, 1,  2,  12, 5,  10, 14, 7,
		        1,  10, 13, 0,  6,  9,  8,  7,  4,  15, 14, 3,  11, 5,  2,  12},
		    
		        {7,  13, 14, 3,  0,  6,  9,  10, 1,  2,  8,  5,  11, 12, 4,  15,
		        13, 8,  11, 5,  6,  15, 0,  3,  4,  7,  2,  12, 1,  10, 14, 9,
		        10, 6,  9,  0,  12, 11, 7,  13, 15, 1,  3,  14, 5,  2,  8,  4,
		        3,  15, 0,  6,  10, 1,  13, 8,  9,  4,  5,  11, 12, 7,  2,  14},
		    
		        {2,  12, 4,  1,  7,  10, 11, 6,  8,  5,  3,  15, 13, 0,  14, 9,
		        14, 11, 2,  12, 4,  7,  13, 1,  5,  0,  15, 10, 3,  9,  8,  6,
		        4,  2,  1,  11, 10, 13, 7,  8,  15, 9,  12, 5,  6,  3,  0,  14,
		        11, 8,  12, 7,  1,  14, 2,  13, 6,  15, 0,  9,  10, 4,  5,  3},
		    
		        {12, 1,  10, 15, 9,  2,  6,  8,  0,  13, 3,  4,  14, 7,  5,  11,
		        10, 15, 4,  2,  7,  12, 9,  5,  6,  1,  13, 14, 0,  11, 3,  8,
		        9,  14, 15, 5,  2,  8,  12, 3,  7,  0,  4,  10, 1,  13, 11, 6,
		        4,  3,  2,  12, 9,  5,  15, 10, 11, 14, 1,  7,  6,  0,  8,  13},
		    
		        {4,  11, 2,  14, 15, 0,  8,  13, 3,  12, 9,  7,  5,  10, 6,  1,
		        13, 0,  11, 7,  4,  9,  1,  10, 14, 3,  5,  12, 2,  15, 8,  6,
		        1,  4,  11, 13, 12, 3,  7,  14, 10, 15, 6,  8,  0,  5,  9,  2,
		        6,  11, 13, 8,  1,  4,  10, 7,  9,  5,  0,  15, 14, 2,  3,  12},
		    
		        {13, 2,  8,  4,  6,  15, 11, 1,  10, 9,  3,  14, 5,  0,  12, 7,
		        1,  15, 13, 8,  10, 3,  7,  4,  12, 5,  6,  11, 0,  14, 9,  2,
		        7,  11, 4,  1,  9,  12, 14, 2,  0,  6,  10, 13, 15, 3,  5,  8,
		        2,  1,  14, 7,  4,  10, 8,  13, 15, 12, 9,  0,  3,  5,  6,  11}
		    };

//shift at 1,2,9,16 by 1 otherwise by 2
	private static byte[] shifts = {
	 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1 };
	

	
//arrays
     static int[] keyPc1Permuted = new int[56];
     static int[] keycombined_28 = new int[56];

private static int[] firstrray_28 = new int[28];
private static int[] secondarray_28 = new int[28];
private static int[] expand_48 = new int[48];

private static int[] L_32 = new int[32];
private static int[] R_32 = new int[32];

static int[] s1 = new int[6];
static int[] s2 = new int[6]; 
static int[] s3 = new int[6];
static int[] s4 = new int[6];
static int[] s5 = new int[6];
static int[] s6 = new int[6];
static int[] s7 = new int[6];
static int[] s8 = new int[6];

static String r1;
static String r2;
static  String r3;
static String r4;
static String r5;
static String r6;
static String r7;
static String r8;
static int r1decimal;
static int r2decimal;
static  int r3decimal;
static int r4decimal;
static int r5decimal;
static int r6decimal;
static int r7decimal;
static int r8decimal;



static String c1;
static String c2;
static  String c3;
static String c4;
static String c5;
static String c6;
static String c7;
static String c8;

static int c1decimal;
static int c2decimal;
static  int c3decimal;
static int c4decimal;
static int c5decimal;
static int c6decimal;
static int c7decimal;
static int c8decimal;



static  int[] output = new int[32];









static int[] new_L = new int[32];

private static int[] xorarray = new int[48];



private static int[] combined_28 = new int[56];
private static int[] plainpermutation= new int[ipMessage.length];



	
	
	//des function 
	
	
    private static int[] des(int[] key, int[] plain){
    	//key
    //first permutation key of 64 
    for (int i = 0; i <= (Pc1.length) - 1; i++) {
    keyPc1Permuted[i] = key[Pc1[i] - 1];
    }
    	
    		
    		
    		
    //split 56 into 2 28 arrays 
    		
    for(int i = 0; i < 28; i++){
    firstrray_28 [i] = key[Pc1[i] - 1];
    }	             
            
    for(int i = 28; i < 56; i++){
    secondarray_28 [i-28] = key[Pc1[i] -1];
    }
     
             
 
   
    
  //shift firstarray_28
    
   for (int i = 0; i < firstrray_28.length; i++) {
		for (int j = 0; j < (firstrray_28.length) - 1; j++) {

						int temp1 = firstrray_28[j];
						firstrray_28[j] = firstrray_28[j + 1];
						firstrray_28[j + 1] = temp1;
					}
				}
	    		
	    		
	    		//shift secondarray_28 
	    		
	    		for (int i = 0; i < secondarray_28.length; i++) {
					for (int j = 0; j < (secondarray_28.length) - 1; j++) {

						int temp1 =secondarray_28[j];
						secondarray_28[j] = secondarray_28[j + 1];
						secondarray_28[j + 1] = temp1;
					}
    
    
    }
	    		
	    		
	    		
//combine first nd second 28
	    		
	    		for (int i =0 ; i < 28 ; i++) {
	    			
	    			combined_28[i]= firstrray_28[i];}
	    		
                   for (int i =0 ; i < 28 ; i++) {
	    			
	    			combined_28[i+28]= secondarray_28[i];
	    			}
                   //seconnd permuation of 56 
            	   for (int i = 0; i <= (combined_28.length) - 1; i++) {
            	   keycombined_28[i] = key[combined_28[i] - 1];
            	   }
            	   //return keycombined_28;		
            	   
	    		
    
    
    //plaintext
            	   //permutation plaintext
                   for(int i =0; i < ipMessage.length; i++){
                	   plainpermutation[i] = key[ipMessage[i] - 1];}
                   
                   
                   //split 64 into 2 32 arrays 
   	    		
           	    for(int i = 0; i < 32; i++){
           	    L_32 [i] = key[ipMessage[i] - 1];
           	    }	             
           	            
           	    for(int i = 32; i < 64; i++){
           	    R_32 [i-32] = key[ipMessage[i] -1];
           	    }
           	    

           	 

                
    //L_shift 
           	    for(int i = 0; i < 32; i++){
           	    	
           	    new_L [i]= R_32 [i]; 
           	    
           	    }
           	    
           	    
           	    for(int i =0; i<16;i++) {
           	//expansion to 48 
           	
           	 for(int l= 0; l < 48; l++){
                 expand_48[l] = R_32[expansionTable[l] - 1];}
           	 
           	 //expand_48 xor keycombined_48 
           	 for(int l= 0 ; l < 48 ;  l++) {
           		 
           		 xorarray [l]= (expand_48[l]+keycombined_28[l])%2 ;
           	 }
    
           	 
//split xorarray to 8 slots each 6 bits
           	 
           	for(int l=0; l<6 ; l++) {
          	  
          	  s1[l]= xorarray[l];
            }
           for(int l=0; l<6 ; l++) {
          	  
          	  s2[l]= xorarray[l+6];
            } for(int l=0; l<6 ; l++) {
          	  
          	  s3[l]= xorarray[l+12];
            } for(int l=0; l<6 ; l++) {
          	  
          	  s4[l]= xorarray[l+18];
            } for(int l=0; l<6 ; l++) {
          	  
          	  s5[l]= xorarray[l+24];
            } for(int l=0; l<6 ; l++) {
          	  
          	  s6[l]= xorarray[l+30];
            } for(int l=0; l<6 ; l++) {
          	  
          	  s7[l]= xorarray[l+36];
            } for(int l=0; l<6 ; l++) {
          	  
          	  s8[l]= xorarray[l+42];
            }

//get row and coloumn from s tables
            
            
          r1=  s1[0]+""+s1[6];
          r1decimal=Integer.parseInt(r1,2);
          r2=  s2[0]+""+s2[6];
          r2decimal=Integer.parseInt(r2,2);

          r3=  s3[0]+""+s3[6];
          r3decimal=Integer.parseInt(r3,2);

          
          r4=  s4[0]+""+s4[6];
          r4decimal=Integer.parseInt(r4,2);

          
          r5=  s5[0]+""+s5[6];
          r5decimal=Integer.parseInt(r5,2);

          
          
          r6=  s6[0]+""+s6[6];
          r6decimal=Integer.parseInt(r6,2);

          
          r7=  s7[0]+""+s7[6];
          r7decimal=Integer.parseInt(r7,2);

          
          r8=  s8[0]+""+s8[6];
          r8decimal=Integer.parseInt(r8,2);



           
            
           c1 = s1[1]+""+s1[2]+""+s1[3]+""+s1[4];
           c1decimal=Integer.parseInt(c1,2);

           
           c2 = s2[1]+""+s2[2]+""+s2[3]+""+s2[4];
           c2decimal=Integer.parseInt(c2,2);

           
           c3 = s3[1]+""+s3[2]+""+s3[3]+""+s3[4];
           c3decimal=Integer.parseInt(c3,2);

           
           c4 = s4[1]+""+s4[2]+""+s4[3]+""+s4[4];
           c4decimal=Integer.parseInt(c4,2);

           
           c5 = s5[1]+""+s5[2]+""+s5[3]+""+s5[4];
           c5decimal=Integer.parseInt(c5,2);

           
           c6 = s6[1]+""+s6[2]+""+s6[3]+""+s6[4];
           c6decimal=Integer.parseInt(c6,2);

           
           c7 = s7[1]+""+s7[2]+""+s7[3]+""+s7[4];
           c7decimal=Integer.parseInt(c7,2);

           
           c8 = s8[1]+""+s8[2]+""+s8[3]+""+s8[4];
           c8decimal=Integer.parseInt(c8,2);
           
           int[]  r = {r1decimal,r2decimal,r3decimal,r4decimal,r5decimal,r6decimal,r7decimal,r8decimal};
           
           int[] C = {c1decimal,c2decimal,c3decimal,c4decimal,c5decimal,c6decimal,c7decimal,c8decimal};
           

for(int l=0; l< 8 ; l++) {

	
	int z = S[r[l]][C[l]];
    String b = Integer.toBinaryString(z);
    while(b.length() < 4){
        b = "0" + b;
    }
    
    for(int j = 0; j < 4; j++){
        output[(l*4) + j] = Integer.parseInt(b.charAt(j) + "");
    }
          }
int[] result = new int[32];
for(int k =0; k < P.length; k++){
result[k]  = output[P[k] - 1 ];
}
           	 
           	    }
				return plain;     	 
           	 
} 	
private static int[] ECB(String pText, String key){
    
    char[] pTextArray = pText.toCharArray();
    char[] keyArray = key.toCharArray();
    //array of nums
    int[] n = new int[pTextArray.length];
    int[] n2 = new int[keyArray.length];
    
    //converting pText to  int[] n
    for(int i = 0; i < pTextArray.length; i++){
        n[i] = pTextArray[i];
    }
    
    //converting the key to int[] n2
    for(int i = 0; i < keyArray.length; i++){
        n2[i] = keyArray[i];
    }
    
    String s = "";
    String sTemp;
    String sKey = "";
    String tempsKey;
    
    //expand each character in plaintext to 64 bits ASCII code
    for(int i = 0; i < n.length; i++){
        int tem = n[i];
        sTemp = Integer.toBinaryString(tem);
        //askkk
        while(sTemp.length() < 8){
            sTemp = "0" + sTemp;
            if(sTemp.length() < 8)
                sTemp = "0" + sTemp;
        }
        s = s + sTemp;
        
    }
    System.out.println("");
    System.out.println("Plaintext converted to binary: " + s);
    // ana henaaa
    //expand each character in key to 64 bits ASCII code
    for(int i = 0; i < n.length; i++){
        int tem = n2[i];
        tempsKey = Integer.toBinaryString(tem);
        while(tempsKey.length() < 8){
            tempsKey = "0" + tempsKey;
            if(tempsKey.length() < 8)
                tempsKey = "0" + tempsKey;
            
        }
        sKey = sKey + tempsKey;
    }
    
    if( s.length() < 64){
        throw new ArrayIndexOutOfBoundsException("Array size wrong");
    }
    else
    {
        // get the number of blocks
        int numofblock = ((s.length() - (s.length() % 64)) / 64);
        
        //padding 0 to plaintext to have equal 64 bits blocks
        int n3 = 64 - (s.length() - 64 * numofblock);
        for(int i = 0; i < n3; i++){
            s = s + "0";
        }
        
        //store binary text in the int[]
        int[] textResult = new int[s.length()];
        
        for(int i = 0; i < s.length(); i++){
            
            textResult[i] = Integer.parseInt(String.valueOf(s.charAt(i)));
            
        }
        
        
        //store binary key in the int[]
        int[] keyResult = new int[sKey.length()];
        for(int i = 0; i < sKey.length(); i++){
            
            keyResult[i] = Integer.parseInt(String.valueOf(sKey.charAt(i)));
            
        }
        
        System.out.println("");
        System.out.println("The orginal plaintext: ");
        System.out.println("{");
        for(int i = 0; i < textResult.length; i++){
            
            System.out.print("" + textResult[i] + ",");
        }// end for
        System.out.println("}" + " ---- Count : " + textResult.length);
        System.out.println("");

        // divide plaintext in every 64 bits blocks
        //n1 will be n4
        //n2 will be n5
        int[] n4 = new int[64];
        int[] n5 = new int[64];
        
        for(int i = 0 ; i < 64; i++){
            n4[i] = textResult[i];
        }
        for(int i = 0; i < 64; i++){
            n5[i] = textResult[i+64];
        }
        
        // System.out.println(" n1 L*****  " + n1.length);
        // System.out.println(" n2 L*****  " + n2.length);
        System.out.println("");
        System.out.println(" 1st block cipher: ");
        //// dy bayza
        int[] ciphertext1 = des(n4,keyResult);
        System.out.println("");
        System.out.println(" 2nd block cipher: ");
        //dyy bayzaaaa
         int[] ciphertext2 = des(n2,keyResult);
        //System.out.println(" c1 L*****  " + ciphertext1.length);
        //System.out.println(" c2 L*****  " + ciphertext2.length);
        
        
        int[] finaltext = new int[ciphertext1.length + ciphertext2.length];
        
        for(int i = 0; i < ciphertext1.length; i++){
            finaltext[i] = ciphertext1[i];
        }
        for(int i = 0; i < ciphertext2.length; i++){
            finaltext[i + 64] = ciphertext2[i];
        }
        
        
        int numofdex = finaltext.length / 8;
        String binarystring = "";
        int[] final_output = new int[numofdex];
        
        for(int i = 0; i < numofdex; i ++){
            
            for(int j = i*8; j <= i*8 +7; j++){
                
                binarystring = binarystring + finaltext[j];
            }
            
            int numtemp;
            numtemp = Integer.parseInt(binarystring,2);
            final_output[i] = numtemp;
            binarystring = "";
        }
        
        System.out.println("");
        System.out.println("Processing ECB......");
        System.out.println("......");
        System.out.println("......");

        System.out.println("Ciphertext of ECB: ");
        System.out.print("{");
        for(int i = 0; i < final_output.length; i++){
            
            System.out.print("" + final_output[i] + ",");
        }// end for
        System.out.println("}");
        System.out.println("");

        
        return final_output;
        
    }//end if
    
}//end ECB

private static int[] CBC(String plaintext, String key, String IV){
    
    char[] plainTextArray = plaintext.toCharArray();
    char[] keyArray = key.toCharArray();
    char[] iv_array = IV.toCharArray();
    
    int[] num = new int[plainTextArray.length];
    int[] num2 = new int[keyArray.length];
    int[] num3 = new int[iv_array.length];
    
    //convert plaintext to int[] num
    for(int i = 0; i < plainTextArray.length; i++){
        num[i] = plainTextArray[i];
    }
    
    //convert key to int[] num2
    for(int i = 0; i < keyArray.length; i++){
        num2[i] = keyArray[i];
    }
    
    //convert IV to int[] num3
    for(int i = 0; i < iv_array.length; i++){
        num3[i] = iv_array[i];
    }
    
    String stext = "";
    String temps;
    String skey = "";
    String temps_key;
    String siv = "";
    String temps_iv;
    
    //expand each character in plaintext to 8 bits ASCII code
    for(int i = 0; i < num.length; i++){
        int tem = num[i];
        temps = Integer.toBinaryString(tem);
        
        while(temps.length() < 8){
            temps = "0" + temps;
            if(temps.length() < 8)
                temps = "0" + temps;
        }
        stext = stext + temps;
    }
    
    System.out.println("Plaintext convert to binary: " + stext);
    System.out.println("");

    //expand each character in key to 8 bits ASCII code
    for(int i = 0; i < num2.length; i++){
        int tem = num2[i];
        temps_key = Integer.toBinaryString(tem);
        while(temps_key.length() < 8){
            temps_key = "0" + temps_key;
            if(temps_key.length() < 8)
                temps_key = "0" + temps_key;
            
        }
        skey = skey + temps_key;
    }
    
    System.out.println("key convert to binary: " + skey);
    System.out.println("");

    //expand each character in IV to 8 bits ASCII code
    for(int i = 0; i < num3.length; i++){
        int tem = num3[i];
        temps_iv = Integer.toBinaryString(tem);
        while(temps_iv.length() < 8){
            temps_iv = "0" + temps_iv;
            if(temps_iv.length() < 8)
                temps_iv = "0" + temps_iv;
            
        }
        siv = siv + temps_iv;
    }
    
    System.out.println("IV convert to binary: " + siv);
    System.out.println("");

    if( stext.length() < 64){
        throw new ArrayIndexOutOfBoundsException("Array size wrong");
    }
    else
    {
        // get the number of blocks
        int numofblock = ((stext.length() - (stext.length() % 64)) / 64);
        
        //padding 0 to plaintext to have equal 64 bits blocks
        int n = 64 - (stext.length() - 64 * numofblock);
        for(int i = 0; i < n; i++){
            stext = stext + "0";
        }
        
        //store binary text in the int[]
        int[] textResult = new int[stext.length()];
        
        for(int i = 0; i < stext.length(); i++){
            
            textResult[i] = Integer.parseInt(String.valueOf(stext.charAt(i)));
            
        }
        
        //store binary key in the int[]
        int[] keyResult = new int[skey.length()];
        for(int i = 0; i < skey.length(); i++){
            
            keyResult[i] = Integer.parseInt(String.valueOf(skey.charAt(i)));
        }
        
        //store binary IV in the int[]
        int[] iv_result = new int[siv.length()];
        for(int i = 0; i < siv.length(); i++){
            
            iv_result[i] = Integer.parseInt(String.valueOf(siv.charAt(i)));
            
        }
        
        // dividing plaintext in every 64 bits blocks
        int[] n1 = new int[64];
        int[] n2 = new int[64];
        
        for(int i = 0 ; i < 64; i++){
            n1[i] = textResult[i];
        }
        for(int i = 0; i < 64; i++){
            n2[i] = textResult[i+64];
        }
        
        System.out.println("");
        System.out.println(" 1st block cipher: ");
        
        //expand_48 xor keycombined_48 
      	// for(int l= 0 ; l < 48 ;  l++) {
      		 
      	//	 xorarray [l]= (expand_48[l]+keycombined_28[l])%2 ;
      //	 }
     
        // end xor
        int[] first = xor(n1, iv_result);
        int[] c1 = des(first,keyResult);
        System.out.println("");
        System.out.println(" 2nd block cipher: ");
        int[] second = xor(n2,c1);
        int[] c2 = des(second,keyResult);
        System.out.println("");

        
        int[] finaltext = new int[c1.length + c2.length];
        
        for(int i = 0; i < c1.length; i++){
            finaltext[i] = c1[i];
        }
        for(int i = 0; i < c2.length; i++){
            finaltext[i + 64] = c2[i];
        }
        
        
        int numofdex = finaltext.length / 8;
        String binarystring = "";
        int[] final_output = new int[numofdex];
        
        for(int i = 0; i < numofdex; i ++){
            
            for(int j = i*8; j <= i*8 +7; j++){
                
                binarystring = binarystring + finaltext[j];
            }
            
            int numtemp;
            numtemp = Integer.parseInt(binarystring,2);
            final_output[i] = numtemp;
            binarystring = "";
        }
        
        System.out.println("P CBC....");

        System.out.println("Ciphertext of CBC: ");
        System.out.print("{");
        for(int i = 0; i < final_output.length; i++){
            
            System.out.print("" + final_output[i] + ",");
        }
        System.out.println("}");
        System.out.println("");

        
        return final_output;
        
    }
    
};//ending CBC




}


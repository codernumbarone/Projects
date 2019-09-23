import java.util.Scanner;
import java.util.StringTokenizer;

public class Final {
	private static byte[]  PK = {
	        57, 49, 41, 33, 25, 17, 9,
	        1,  58, 50, 42, 34, 26, 18,
	        10, 2,  59, 51, 43, 35, 27,
	        19, 11, 3,  60, 52, 44, 36,
	        63, 55, 47, 39, 31, 23, 15,
	        7,  62, 54, 46, 38, 30, 22,
	        14, 6,  61, 53, 45, 37, 29,
	        21, 13, 5,  28, 20, 12, 4
	    };
	    
	    private static byte[] RotateLeft = {
	        1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1
	    };
	    
	    private static byte[] KeyChoice = {
	        14, 17, 11, 24, 1,  5,
	        3,  28, 15, 6,  21, 10,
	        23, 19, 12, 4,  26, 8,
	        16, 7,  27, 20, 13, 2,
	        41, 52, 31, 37, 47, 55,
	        30, 40, 51, 45, 33, 48,
	        44, 49, 39, 56, 34, 53,
	        46, 42, 50, 36, 29, 32
	    };
	    
	    private static byte[] IP = {
	        58, 50, 42, 34, 26, 18, 10, 2,
	        60, 52, 44, 36, 28, 20, 12, 4,
	        62, 54, 46, 38, 30, 22, 14, 6,
	        64, 56, 48, 40, 32, 24, 16, 8,
	        57, 49, 41, 33, 25, 17, 9,  1,
	        59, 51, 43, 35, 27, 19, 11, 3,
	        61, 53, 45, 37, 29, 21, 13, 5,
	        63, 55, 47, 39, 31, 23, 15, 7
	    };
	    
	    private static byte[] E = {
	        32, 1,  2,  3,  4,  5,
	        4,  5,  6,  7,  8,  9,
	        8,  9,  10, 11, 12, 13,
	        12, 13, 14, 15, 16, 17,
	        16, 17, 18, 19, 20, 21,
	        20, 21, 22, 23, 24, 25,
	        24, 25, 26, 27, 28, 29,
	        28, 29, 30, 31, 32, 1
	    };
	    
	    
	    private static byte[][] SBox = {
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
	    
	    private static byte[] IP1 = {
	        40, 8, 48, 16, 56, 24, 64, 32,
	        39, 7, 47, 15, 55, 23, 63, 31,
	        38, 6, 46, 14, 54, 22, 62, 30,
	        37, 5, 45, 13, 53, 21, 61, 29,
	        36, 4, 44, 12, 52, 20, 60, 28,
	        35, 3, 43, 11, 51, 19, 59, 27,
	        34, 2, 42, 10, 50, 18, 58, 26,
	        33, 1, 41, 9, 49, 17, 57, 25
	    };
	    
	    private static int[] C = new int[28];
	    private static int[] D = new int[28];
		static String input = "0123456789ABCDEF0123456789ABC7DF";
		static String key =   "133457799BBCDFF1";
		static String[] x;
		private static int[][] subkey = new int[16][48];
        static String s1 = "We Love Hadil";
        static String s2 = "ABCDEFGH";
        static String IV = "ABCDEFGH";

        


	    private static int[] L = new int[32];
	    private static int[] R = new int[32];

	    public static void main(String args[]) {
			int inputBits[] = new int[64];
			
			///ECB
			if(input.length()==16){
			
			
			for(int i=0 ; i < 16 ; i++) {

				String s = Integer.toBinaryString(Integer.parseInt(input.charAt(i) + "", 16));
				

				while(s.length() < 4) {
					s = "0" + s;
				}
				for(int j=0 ; j < 4 ; j++) {
					inputBits[(4*i)+j] = Integer.parseInt(s.charAt(j) + "");
				}
			}
			
			int keyBits[] = new int[64];
			for(int i=0 ; i < 16 ; i++) {
				String s = Integer.toBinaryString(Integer.parseInt(key.charAt(i) + "", 16));
				while(s.length() < 4) {
					s = "0" + s;
				}
				for(int j=0 ; j < 4 ; j++) {
					keyBits[(4*i)+j] = Integer.parseInt(s.charAt(j) + "");
				}
			}
			

			System.out.println("\n+++ ENCRYPTION equals +++");
			int outputBits[] = permute(inputBits, keyBits);
			}
			else if(input.length()%16==0 && input.length()/16>1){
					
					for(int k=0;k<input.length()/16;k++){
					
//					x=new String[input.length()/16];
					 x=input.split("(?<=\\G.{16})");
							
					
					for(int i=0 ; i < 16 ; i++) {

					x[k] = Integer.toBinaryString(Integer.parseInt(input.charAt(i) + "", 16));
					

					while(x[k].length() < 4) {
						x[k] = "0" + x[k];
					}
					for(int j=0 ; j < 4 ; j++) {
						inputBits[(4*i)+j] = Integer.parseInt(x[k].charAt(j) + "");
					}
				}
				
				int keyBits[] = new int[64];
				for(int i=0 ; i < 16 ; i++) {
					String s = Integer.toBinaryString(Integer.parseInt(key.charAt(i) + "", 16));
					while(s.length() < 4) {
						s = "0" + s;
					}
					for(int j=0 ; j < 4 ; j++) {
						keyBits[(4*i)+j] = Integer.parseInt(s.charAt(j) + "");
					}
				}
		      

				int outputBits[] = permute(inputBits, keyBits);
					}
					  int ecb[] = ECB(s1,s2);
				        System.out.println("************************   CBC  *************************");
				        int cbc[] = CBC(s1,s2,IV);
				        System.out.println("");
			}
			
		}
		
		private static int[] permute(int[] inputBits, int[] keyBits) {

			int newBits[] = new int[inputBits.length];
			for(int i=0 ; i < inputBits.length ; i++) {
				newBits[i] = inputBits[IP[i]-1];
			}
			

			int L[] = new int[32];
			int R[] = new int[32];
			int i;
			
			for(i=0 ; i < 28 ; i++) {
				C[i] = keyBits[PK[i]-1];
			}
			for( ; i < 56 ; i++) {
				D[i-28] = keyBits[PK[i]-1];
			}
			

			
			System.arraycopy(newBits, 0, L, 0, 32);
			System.arraycopy(newBits, 32, R, 0, 32);
			System.out.print("\nL0 = ");
			System.out.print("R0 = ");
			
			
			////
			for(int n=0 ; n < 16 ; n++) {
				System.out.println("\n-------------");
				System.out.println("Round " + (n+1) + ":" +"\n");

				int RN[] = new int[0];
			
					RN = fiestel(R, KS(n, keyBits));
				

				int newL[] = xor(L, RN);
				L = R;
				R = newL;

			}
			

			int output[] = new int[64];
			System.arraycopy(R, 0, output, 0, 32);
			System.arraycopy(L, 0, output, 32, 32);
			int finalOutput[] = new int[64];
			for(i=0 ; i < 64 ; i++) {
				finalOutput[i] = output[IP1[i]-1];
			}
			

			String hex = new String();
			for(i=0 ; i < 16 ; i++) {
				String bin = new String();
				for(int j=0 ; j < 4 ; j++) {
					bin += finalOutput[(4*i)+j];
				}
				int decimal = Integer.parseInt(bin, 2);
				hex += Integer.toHexString(decimal);
			}

				System.out.print("Encrypted text: ");
			System.out.println(hex.toUpperCase());
			return finalOutput;
		}
		
		private static int[] KS(int round, int[] key) {

			int C1[] = new int[28];
			int D1[] = new int[28];
			
			int rotationTimes = (int) RotateLeft[round];

			C1 = leftShift(C, rotationTimes);
			D1 = leftShift(D, rotationTimes);
			int CnDn[] = new int[56];
			System.arraycopy(C1, 0, CnDn, 0, 28);
			System.arraycopy(D1, 0, CnDn, 28, 28);

			int Kn[] = new int[48];
			for(int i=0 ; i < Kn.length ; i++) {
				Kn[i] = CnDn[KeyChoice[i]-1];
			}
			

			subkey[round] = Kn;
			C = C1;
			D = D1;
			return Kn;
		}
		
		private static int[] fiestel(int[] R, int[] roundKey) {
			int expandedR[] = new int[48];
			for(int i=0 ; i < 48 ; i++) {
				expandedR[i] = R[E[i]-1];
			}
			int temp[] = xor(expandedR, roundKey);

			int output[] = sBlock(temp);
			return output;
		}
		
		private static int[] xor(int[] a, int[] b) {
			int answer[] = new int[a.length];
			for(int i=0 ; i < a.length ; i++) {
				answer[i] = a[i]^b[i];
			}
			return answer;
		}
		
		private static int[] sBlock(int[] bits) {
			int output[] = new int[32];

			for(int i=0 ; i < 8 ; i++) {

				int row[] = new int [2];
				row[0] = bits[6*i];
				row[1] = bits[(6*i)+5];
				String sRow = row[0] + "" + row[1];

				int column[] = new int[4];
				column[0] = bits[(6*i)+1];
				column[1] = bits[(6*i)+2];
				column[2] = bits[(6*i)+3];
				column[3] = bits[(6*i)+4];
				String sColumn = column[0] +""+ column[1] +""+ column[2] +""+ column[3];

				int iRow = Integer.parseInt(sRow, 2);
				int iColumn = Integer.parseInt(sColumn, 2);
				
				int x = SBox[i][(iRow*16) + iColumn];

				String s = Integer.toBinaryString(x);

				while(s.length() < 4) {
					s = "0" + s;
				}
				for(int j=0 ; j < 4 ; j++) {
					output[(i*4) + j] = Integer.parseInt(s.charAt(j) + "");
				}
			}

			int finalOutput[] = new int[32];
			for(int i=0 ; i < 32 ; i++) {
				finalOutput[i] = output[P[i]-1];
			}
			return finalOutput;
		}
		
		private static int[] leftShift(int[] bits, int n) {

			int answer[] = new int[bits.length];
			System.arraycopy(bits, 0, answer, 0, bits.length);
			for(int i=0 ; i < n ; i++) {
				int temp = answer[0];
				for(int j=0 ; j < bits.length-1 ; j++) {
					answer[j] = answer[j+1];
				}
				answer[bits.length-1] = temp;
			}
			return answer;
		}
		


	    private static int[] DES(int[] plaintext, int[] key){
	        
	        if(plaintext.length == 64 && key.length == 64){
	            
	            //64 to 56 
	            for(int i = 0; i < 28; i++){
	                C[i] = key[PK[i] - 1];
	            }
	            
	            
	            for(int i = 28; i < 56; i++){
	                D[i-28] = key[PK[i] -1];
	            }
	            
	            
	            int[] pre_plaintext = new int[plaintext.length];
	            for(int i =0; i < plaintext.length; i++){
	                pre_plaintext[i] = plaintext[IP[i] - 1];
	            }
	            
	            System.arraycopy(pre_plaintext, 0, L, 0, 32);
	            System.arraycopy(pre_plaintext, 32, R, 0, 32);
	            

	            
	            for(int i = 0; i < 16; i++){
	                
	                //expand from 32 to 48 bits
	                int[] expand = new int[48];
	                for(int j = 0; j < 48; j++){
	                    expand[j] = R[E[j] - 1];
	                    
	                }
	                
	                int[] K = new int[48];
	                K = key_cal(key, i);
	                
	                int E_xor[] = xor1(expand,K);
	                
	                int cal[] = S_box(E_xor);
	                
	                int[] new_R = new int[0];
	                new_R = xor(L, cal);
	                
	                L = R;
	                R = new_R;
	            }
	            
	            int plaintext_final[] = new int[64];
	            System.arraycopy(R,0,plaintext_final,0,R.length);
	            System.arraycopy(L,0,plaintext_final,32,L.length);
	            
	            
	            int ciphertext1[] = new int[64];
	            for(int i = 0; i < plaintext_final.length; i++){
	                
	                ciphertext1[i] = plaintext_final[IP1[i] - 1];
	            }
	            
	            System.out.println("Ciphertext: ");

	            for(int i = 0; i < ciphertext1.length; i++){
	                
	                System.out.print("" + ciphertext1[i] + ",");
	            }


	            return ciphertext1;
	            
	        }
	        
	        else
	        {
	            throw new ArrayIndexOutOfBoundsException("Array size wrong");
	        }
	        
	    };
	    
	    private static int[] key_cal (int key[], int iter){

	        int CD[] = new int[56];
	        int tem_C[] = new int[28];
	        int tem_D[] = new int[28];
	        
	        int i = (int)RotateLeft[iter];
	        tem_C = left_shift(C, i);
	        tem_D = left_shift(D, i);
	          
	        
	        System.arraycopy(tem_C,0,CD,0,tem_C.length);
	        System.arraycopy(tem_D,0,CD,28,tem_D.length);
	        
	      
	        int tem_K[] = new int[48];
	        for(int j = 0; j < tem_K.length; j++){
	            tem_K[j] = CD[KeyChoice[j] - 1];
	        }
	        
	        C = tem_C;
	        D = tem_D;
	        
	        return tem_K;
	    }
	    
	    private static int[] S_box (int[] input){
	        int[] output = new int[32];
	        for(int i = 0; i < 8; i++){
	            
	            int row[] = new int[2];
	            int column[] = new int[4];
	            
	            // the first and the last of every 6 bits block are the Row locations
	            row[0] = input[6*i];
	            row[1] = input[(6*i) + 5];
	            String s1 = row[0] + "" + row[1];
	            // the 2nd to the 4th of every 6 bits block are the Column locations
	            column[0] = input[(6*i)+1];
	            column[1] = input[(6*i)+2];
	            column[2] = input[(6*i)+3];
	            column[3] = input[(6*i)+4];
	            String s2 =  column[0] +""+ column[1] +""+ column[2] +""+ column[3];
	            
	            int x = Integer.parseInt(s1, 2);
	            int y = Integer.parseInt(s2, 2);
	            int z = SBox[i][(x*16) + y];
	            
	            String b = Integer.toBinaryString(z);
	            
	            while(b.length() < 4){
	                b = "0" + b;
	            }
	            
	            for(int j = 0; j < 4; j++){
	                output[(i*4) + j] = Integer.parseInt(b.charAt(j) + "");
	            }
	        }
	        
	        int[] result = new int[32];
	        for(int i =0; i < P.length; i++){
	            result[i]  = output[P[i] - 1 ];
	        }
	        
	        return result;
	        
	    }
	    
	    private static int[] xor1 (int[] input1, int[] intput2){
	        int[] output = new int[input1.length];
	        
	        for(int i = 0; i < input1.length; i++){
	            output[i] = (input1[i]+intput2[i]) % 2;
	        }
	        return output;
	    }
	    
	    private static int[] left_shift(int[] input, int n){
	        
	        int temp[] = new int[input.length];
	        
	        if(n == 1){
	            temp[input.length - 1] = input[0];
	            for(int i = 0; i < input.length - 1; i++){
	                temp[i] = input[i+1];
	            }
	        }
	        else
	        {
	            temp[input.length - 2] = input[0];
	            temp[input.length - 1] = input[1];
	            for(int i = 0; i < input.length - 2; i++){
	                temp[i] = input[i+2];
	            }
	        }
	        
	        return temp;
	    }

	    
	    
	    private static int[] ECB(String plaintext, String key){
	        
	        char[] plaintext_array = plaintext.toCharArray();
	        char[] key_array = key.toCharArray();
	        
	        int[] num = new int[plaintext_array.length];
	        int[] num2 = new int[key_array.length];
	        
	        //convert plaintext to int[] num
	        for(int i = 0; i < plaintext_array.length; i++){
	            num[i] = plaintext_array[i];
	        }
	        
	        //convert key to int[] num2
	        for(int i = 0; i < key_array.length; i++){
	            num2[i] = key_array[i];
	        }
	        
	        String s = "";
	        String temps;
	        String s_key = "";
	        String temps_key;
	        
	        for(int i = 0; i < num.length; i++){
	            int tem = num[i];
	            temps = Integer.toBinaryString(tem);
	            
	            while(temps.length() < 8){
	                temps = "0" + temps;
	                if(temps.length() < 8)
	                    temps = "0" + temps;
	            }
	            s = s + temps;
	            
	        }
	        System.out.println("");
	        System.out.println("Plaintext convert to binary: " + s);
	        
	        for(int i = 0; i < num2.length; i++){
	            int tem = num2[i];
	            temps_key = Integer.toBinaryString(tem);
	            while(temps_key.length() < 8){
	                temps_key = "0" + temps_key;
	                if(temps_key.length() < 8)
	                    temps_key = "0" + temps_key;
	                
	            }
	            s_key = s_key + temps_key;
	        }
	        
	        if( s.length() < 64){
	            throw new ArrayIndexOutOfBoundsException("Array size wrong");
	        }
	        else
	        {
	            // get the number of blocks
	            int numofblock = ((s.length() - (s.length() % 64)) / 64);
	            
	            //padding 0 64 bits blocks
	            int n = 64 - (s.length() - 64 * numofblock);
	            for(int i = 0; i < n; i++){
	                s = s + "0";
	            }
	            
	            //binary text
	            int[] text_result = new int[s.length()];
	            
	            for(int i = 0; i < s.length(); i++){
	                
	                text_result[i] = Integer.parseInt(String.valueOf(s.charAt(i)));
	                
	            }
	            
	            
	            //binary key
	            int[] key_result = new int[s_key.length()];
	            for(int i = 0; i < s_key.length(); i++){
	                
	                key_result[i] = Integer.parseInt(String.valueOf(s_key.charAt(i)));
	                
	            }
	            

	            System.out.println("The orginal plaintext: ");
	            for(int i = 0; i < text_result.length; i++){
	                
	                System.out.print("" + text_result[i] + ",");
	            }
	            System.out.println("}" + " ---- Count : " + text_result.length);
	            System.out.println("");

	            // divide plaintext in every 64 bits blocks
	            int[] n1 = new int[64];
	            int[] n2 = new int[64];
	            
	            for(int i = 0 ; i < 64; i++){
	                n1[i] = text_result[i];
	            }
	            for(int i = 0; i < 64; i++){
	                n2[i] = text_result[i+64];
	            }
	            

	            System.out.println("");
	            System.out.println(" 1st block cipher: ");
	            int[] ciphertext1 = DES(n1,key_result);
	            System.out.println("");
	            System.out.println(" 2nd block cipher: ");
	            int[] ciphertext2 = DES(n2,key_result);
	            
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
	            
	            System.out.println("Processing ECB......");
	            System.out.println("Ciphertext of ECB: ");
	            for(int i = 0; i < final_output.length; i++){
	                
	                System.out.print("" + final_output[i] + ",");
	            }

	            return final_output;
	            
	        }
	        
	    }
	    
	    
	    

	    private static int[] CBC(String plaintext, String key, String IV){
	        
	        char[] plaintext_array = plaintext.toCharArray();
	        char[] key_array = key.toCharArray();
	        char[] iv_array = IV.toCharArray();
	        
	        int[] num = new int[plaintext_array.length];
	        int[] num2 = new int[key_array.length];
	        int[] num3 = new int[iv_array.length];
	        
	        
	        for(int i = 0; i < plaintext_array.length; i++){
	            num[i] = plaintext_array[i];
	        }
	        
	       
	        for(int i = 0; i < key_array.length; i++){
	            num2[i] = key_array[i];
	        }
	        
	        
	        for(int i = 0; i < iv_array.length; i++){
	            num3[i] = iv_array[i];
	        }
	        
	        String stext = "";
	        String temps;
	        String skey = "";
	        String temps_key;
	        String siv = "";
	        String temps_iv;
	        
	        //expand to 8 bits ASCII code
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
	            
	            int numofblock = ((stext.length() - (stext.length() % 64)) / 64);
	            
	            //padding 0 to plaintext to have equal 64 bits blocks
	            int n = 64 - (stext.length() - 64 * numofblock);
	            for(int i = 0; i < n; i++){
	                stext = stext + "0";
	            }
	            
	            //binary text
	            int[] text_result = new int[stext.length()];
	            
	            for(int i = 0; i < stext.length(); i++){
	                
	                text_result[i] = Integer.parseInt(String.valueOf(stext.charAt(i)));
	                
	            }
	            
	            //binary key
	            int[] key_result = new int[skey.length()];
	            for(int i = 0; i < skey.length(); i++){
	                
	                key_result[i] = Integer.parseInt(String.valueOf(skey.charAt(i)));
	            }
	            
	            //store binary IV in the int[]
	            int[] iv_result = new int[siv.length()];
	            for(int i = 0; i < siv.length(); i++){
	                
	                iv_result[i] = Integer.parseInt(String.valueOf(siv.charAt(i)));
	                
	            }
	            
	            // 
	            int[] n1 = new int[64];
	            int[] n2 = new int[64];
	            
	            for(int i = 0 ; i < 64; i++){
	                n1[i] = text_result[i];
	            }
	            for(int i = 0; i < 64; i++){
	                n2[i] = text_result[i+64];
	            }
	            
	            System.out.println("");
	            System.out.println(" 1st block cipher: ");
	            int[] first = xor(n1, iv_result);
	            int[] c1 = DES(first,key_result);
	            System.out.println("");
	            System.out.println(" 2nd block cipher: ");
	            int[] second = xor(n2,c1);
	            int[] c2 = DES(second,key_result);
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
	            
	            System.out.println("CBC....");
	            System.out.println("Ciphertext of CBC: ");
	            
	            for(int i = 0; i < final_output.length; i++){
	                
	                System.out.print("" + final_output[i] + ",");
	            }
	            
	            return final_output;
	            
	        }
	        
	    };
	    

	    
}

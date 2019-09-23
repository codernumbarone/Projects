import java.io.DataInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;


public class RSA {
	
	private BigInteger p;
    private BigInteger q;
    private BigInteger N;
    private BigInteger phi;
    private BigInteger e;
    private BigInteger d;
    private int        bitlength = 1024;
    private Random     r;
    public RSA()
    {
        r = new Random();
        //first we get p and q find  probable prime number that we multiply and gives n
        p = BigInteger.probablePrime(bitlength, r);
        q = BigInteger.probablePrime(bitlength, r);
        
        N = p.multiply(q);
        //phi da equal (p-1)*(q-1)
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        //e da less than n such that gcd (e,phi) =1
        e = BigInteger.probablePrime(bitlength / 2, r);
        
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0)
        {
            e.add(BigInteger.ONE);
        }
        //d equal e inverse one mod phi
        d = e.modInverse(phi);
    }
 
    public RSA(BigInteger e, BigInteger d, BigInteger N)
    {
        this.e = e;
        this.d = d;
        this.N = N;
    }
 
    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws IOException
    {
        RSA rsa = new RSA();
        DataInputStream in = new DataInputStream(System.in);
        String teststring;
        
        Scanner message = new Scanner(new FileReader("/Users/zayed/eclipse-workspace/RSA/src/message.txt"));        
        teststring = message.next();
        System.out.println("Encrypting String: " + teststring);
        System.out.println("String in Bytes: "
                + bytesToString(teststring.getBytes()));
        BigInteger encrypted = rsa.encrypt(teststring.getBytes());
        System.out.println(fromBigIntegerToString(encrypted));

        BigInteger decrypted = rsa.decrypt(encrypted.toByteArray());
        System.out.println("Decrypted String: " + fromBigIntegerToString(decrypted));
    }
 
    private static String bytesToString(byte[] encrypted)
    {
        String test = "";
        for (byte b : encrypted)
        {
            test =test+ Byte.toString(b);
        }
        return test;
    }
	public static String fromBigIntegerToString(BigInteger b) 
	{
		return new String(b.toByteArray());
	}
    // Encrypt message
    public BigInteger encrypt(byte[] message)
    {
        return (new BigInteger(message)).modPow(e, N);
    }
 
    // Decrypt message
    public BigInteger decrypt(byte[] message)
    {
        return (new BigInteger(message)).modPow(d, N);
    }
}





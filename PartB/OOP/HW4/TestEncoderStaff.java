import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Random;
import java.util.Scanner;

public class TestEncoderStaff {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String text = sc.nextLine();
		sc.close();
		try {
			StringWriter fw = new StringWriter();
			EncryptorWriter cw = new EncryptorWriter(fw,10);
			cw.write(text.toCharArray(), 0, text.length());
			String buffer = fw.getBuffer().toString();
			cw.close();
			System.out.println("Testing encryptor and decryptor.");
			System.out.println();
			System.out.println("Writing the following text:");
			System.out.println("\"" + text + "\"");
			System.out.println("which encoded to:");
			System.out.println("\"" + buffer + "\"");
			System.out.println();

			DecryptorReader dr = new DecryptorReader(new StringReader(buffer),10);
			char[] buff = new char[text.length()];
			int len = dr.read(buff);
			dr.close();
			System.out.println("Reading the following encoded text:");
			System.out.println("\"" + buffer + "\"");
			System.out.println("and decoding it to:");
			System.out.println("\"" + new String(buff) + "\"");

		} catch (IOException ioe) {
			System.err.println("IO exception");
		}
	}


}

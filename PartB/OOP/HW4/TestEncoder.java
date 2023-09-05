import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TestEncoder {
    public static void main(String[] args) throws IOException{
        File file ;
        int shift ;

        try {
            file = new File(args[0]);
        }
        catch (IndexOutOfBoundsException e){
            throw new IllegalArgumentException(e.getMessage());
        }
        try{
            shift = Integer.valueOf(args[1]);
        }
        catch (NumberFormatException | IndexOutOfBoundsException e){
            throw new IllegalArgumentException(e.getMessage());
        }
        try {
            FileReader reader = new FileReader(file);
            FileWriter writer = new FileWriter("encrypted_" + file);
            EncryptorWriter encryptorFile = new EncryptorWriter(writer, shift);
            char[] buff = new char[(int) file.length()];
            int len = reader.read(buff);
            encryptorFile.write(buff, 0, len);
            encryptorFile.flush();
            encryptorFile.close();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        try {
            FileReader reader = new FileReader("encrypted_" + file);
            FileWriter writer = new FileWriter("decrypted_" + file);
            DecryptorReader decryptorFile = new DecryptorReader(reader,shift);
            char[] buff = new char[(int)file.length()];
            int len = decryptorFile.read(buff,0,(int)file.length());
            decryptorFile.close();
            writer.write(buff,0,len);
            writer.flush();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}

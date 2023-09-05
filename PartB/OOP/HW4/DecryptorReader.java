import java.io.IOException;
import java.io.Reader;

public class DecryptorReader extends Reader {
    private final Reader r;
    private final int shift;
    public DecryptorReader(Reader r){
        this(r,0);
    }
    public DecryptorReader(Reader r,int shift){
        this.r = r;
        this.shift = shift;
    }

    @Override
    public int read(char[] buff){
        int x  = 0;
        try {
            x = read(buff , 0, buff.length);}
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        return x;
    }
    @Override
    public int read(char[] buff, int off, int len) throws IOException {
        int i ;
        for (i = off; i < len; i++){
            try {
                int f = r.read();
                if (f == -1){
                    return i - off;
                }
                buff[i] = (char)(f - this.shift);
            }
            catch (IOException e){
                return i - off -1;
            }
        }
        return i - off;
    }

    @Override
    public void close() throws IOException {
        r.close();
    }

}

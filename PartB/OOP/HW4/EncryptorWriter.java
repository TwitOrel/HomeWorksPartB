import java.io.IOException;
import java.io.Writer;

public class EncryptorWriter extends Writer {
    private final Writer w;
    private final int shift;
    public EncryptorWriter(Writer w){
        this(w,0);
    }
    public EncryptorWriter(Writer w,int shift){
        this.shift = shift;
        this.w = w;
    }
    @Override
    public void write(char[] buff, int offset, int len) throws IOException {
        for (int i = 0; i < len; i++){
            buff[i] += shift;
        }
        w.write(buff,offset,len);
    }

    @Override
    public void flush() throws IOException {
        w.flush();
    }

    @Override
    public void close() throws IOException {
        w.close();
    }
}

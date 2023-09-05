package lab01;

import java.awt.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class PointListCommandLine {
    private ArrayPointList list;
    InputStreamReader reader;
    StreamTokenizer tokens;
    public PointListCommandLine(){
        this.list = new ArrayPointList();
        this.reader = new InputStreamReader(System.in);
        this.tokens  = new StreamTokenizer(reader);
    }

    // Method to read and process user commands
    public void readCommand() throws IOException {
        while (true){
            if (tokens.nextToken() == StreamTokenizer.TT_WORD){
                String command = tokens.sval;
                switch (command){
                    case "add":
                        tokens.nextToken();
                        int x = (int)tokens.nval;
                        tokens.nextToken();
                        int y = (int)tokens.nval;
                        list.append(new Point(x,y));
                        break;
                    case "curr":
                        System.out.println(list.stringCursor());
                        break;
                    case "next":
                        System.out.println(list.goToNext());
                        break;
                    case "prev":
                        System.out.println(list.goToPrior());
                        break;
                    case "start":
                        System.out.println(list.goToBeginning());
                        break;
                    case "end" :
                        System.out.println(list.goToEnd());
                        break;
                    case "empty":
                        System.out.println(list.isEmpty());
                        break;
                    case "full":
                        System.out.println(list.isFull());
                        break;
                    case "clear":
                        list.clear();
                        break;
                    case "quit":
                        return;
                    default:
                        System.out.println("unknown command!");
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        PointListCommandLine elad = new PointListCommandLine();
        elad.readCommand();
    }

}

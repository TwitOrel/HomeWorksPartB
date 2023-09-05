import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class ShapesHandler {
        private static PrismPyramid[] arr;
        private static  final List<PrismPyramid> list = new LinkedList<>();
        private static final Map<Double, List<PrismPyramid>> map = new TreeMap<>();
        private static final List <String> errors = new LinkedList<>();
        private static double CastStD(String x){return Double.valueOf(x);}
        private static int CastStI(String x){return Integer.valueOf(x);}

    public static void main(String[] args){
            try {
                init(args[0],CastStI(args[1]));
            } catch (FileNotFoundException e) {
                System.out.println("cant find data base file!");
            }
        }
        public static PrismPyramid CreatePyramid(String[] x,int numOfLine,String line)throws HW3Exception{
            String[] typeNParams = x[1].split(":",2);
            PrismPyramid tmp;
            // check if length is positive
            String[] argsToBaseShape = x[2].split(",");
            if (CastStD(argsToBaseShape[3]) - CastStD(argsToBaseShape[0]) < 0){
                throw new HW3Exception("Length cannot be nagitive",numOfLine,line);
            }
            //check if we got the correct num of arguments
            if (x[2].split(",").length != 4) {
                throw new HW3Exception("didnt gave correct num of argument for creating Pyramid", numOfLine, line);
            }
            if (typeNParams[0].equals("C")) {
                // check if baseshape Circle have 3 arguments
                if (typeNParams[1].split(",").length != 3) {
                    throw new HW3Exception("type of Circle have to sign with 3 agmuments you gave" + typeNParams[1].split(",").length, numOfLine, line);
                }
                // check if radius is positive
                String[] argOfCircle = typeNParams[1].split(",");
                if (CastStD(argOfCircle[2]) < 0) {
                    throw new HW3Exception("Radius cannot be nagitive", numOfLine, line);
                }
            }
            else {
                // check if length is positive
                String[] argOfRectangle = typeNParams[1].split(",");
                if ( (CastStD(argOfRectangle[2]) < 0) || (CastStD(argOfRectangle[3]) < 0) ) {
                    throw new HW3Exception("Length cannot be nagitive", numOfLine, line);
                }
                //type baseShape of pyramid is R
                // chek if baseshape Rectangle have 4 arguments
                if (typeNParams[1].split(",").length != 4){
                    throw new HW3Exception("type of Rectangle have to sign with 4 arguments you gave"+typeNParams[1].split(",").length,numOfLine,line);
                }
            }
            String[] argOfCircleShape = x[2].split(",");
            tmp = new Pyramid(typeNParams[0],typeNParams[1],CastStD(argOfCircleShape[0]),CastStD(argOfCircleShape[1]),CastStD(argOfCircleShape[2]),CastStD(argOfCircleShape[3]));
            return tmp;
        }
        public static PrismPyramid CreatePrism(String[] x,int numOfLine, String line)throws HW3Exception{
            String[] typeNParams = x[1].split(":",2);
            PrismPyramid tmp;
            //check if we got the correct num of arguments
            if (x[2].split(",").length != 2) {
                throw new HW3Exception("didnt gave correct num of argument for creating Prism", numOfLine, line);
            }

            if (typeNParams[0].equals("C")) {
                // check if baseshape Circle have 3 arguments
                if (typeNParams[1].split(",").length != 3) {
                    throw new HW3Exception("type of Circle have to sign with 3 arguments you gave" + typeNParams[1].split(",").length, numOfLine, line);
                }
                // check if radius is positive
                String[] argOfCircle = typeNParams[1].split(",");
                if (CastStD(argOfCircle[2]) < 0) {
                    throw new HW3Exception("Radius cannot be nagitive", numOfLine, line);
                }
            }
            else {
                //type baseShape of pyramid is R
                // check if baseshape Rectangle have 4 arguments
                if (typeNParams[1].split(",").length != 4){
                    throw new HW3Exception("type of Rectangle have to sign with 4 arguments you gave"+typeNParams[1].split(",").length,numOfLine,line);
                }
                // check if length is positive
                String[] argOfRectangle = typeNParams[1].split(",");
                if ( (CastStD(argOfRectangle[2]) < 0) || (CastStD(argOfRectangle[3]) < 0) ){
                    throw new HW3Exception("Length cannot be nagitive",numOfLine,line);
                }
            }
            String[] argOfCircleShape = x[2].split(",");
            tmp = new Prism(typeNParams[0],typeNParams[1],CastStD(argOfCircleShape[0]),CastStD(argOfCircleShape[1]));
            return tmp;
        }
        public static void trackBugs(String line, int numOfLine)throws HW3Exception {
            //cheking if line contains anogth arguments
            int onecount = 0;
            Scanner s = new Scanner(line);
            s.useDelimiter("[#:,]");
            while (s.hasNext()){
                String stam = s.next();
                onecount++;
            }
            if (onecount < 7) {
                throw new HW3Exception("Not enough parameters in the input line.", numOfLine, line);
            }
            String[] splitedLine = line.split("");
            int twocount = 0;
            for (int i = 0; i < line.length(); i++) {
                if (splitedLine[i].equals("#")) {
                    twocount++;
                }
            }
            // cheking if there is 2 #
            if (twocount > 2) {
                throw new HW3Exception("Too many # in the input line.", numOfLine, line);
            }
            if (twocount < 2) {
                throw new HW3Exception("not much # in the input line.", numOfLine, line);
            }
            // cheking if currect type shape
            if (!splitedLine[0].equals("Y") && (!splitedLine[0].equals("R"))){
                throw new HW3Exception("The type of the shape should be Y(Pyramid) or R(Prism) and not "+splitedLine[0]+".",numOfLine,line);
            }
            if (!splitedLine[2].equals("R") && (!splitedLine[2].equals("C"))){
                throw new HW3Exception("the type of bake shape should be R(rectangle) or C(Circle) and not "+splitedLine[2]+".",numOfLine,line);
            }
        }
        private static void init(String filename, int numOfShapes)throws FileNotFoundException {
            PrismPyramid shape;
            int i = 0;
            int counter = 1;
            arr = new PrismPyramid[numOfShapes];
            Scanner s = new Scanner(new File(filename));
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] inLine = line.split("#",3);
                try {
                    trackBugs(line,counter);
                    if (inLine[0].equals("Y")) {
                        shape = CreatePyramid(inLine, counter, line);
                    }
                    else {
                        // "R"
                        shape = CreatePrism(inLine,counter,line);
                    }
                }
                catch (HW3Exception e){
                    errors.add(e.getError());
                    i++;
                    counter++;
                    continue;
                }
                //looking for good reading(can be Exceptions)

                list.add(0,shape);
                arr[i] = shape;
                if (!map.containsKey(shape.getHeight())) {
                        map.put(shape.getHeight(), new LinkedList<>());
                    }
                    // if map have this key so add this.shape to this.keyList
                    map.get(shape.getHeight()).add(shape);
                    i++;
                    counter++;
                    }
            Collections.reverse(list);

            Comparator <PrismPyramid> comparator = new Comparator<>() {
                @Override
                public int compare(PrismPyramid o1, PrismPyramid o2) {
                    if (o1 == o2 ){
                        return 0;
                    }
                    if (o1 == null){
                        return 1;
                    }
                    if (o2 == null) {
                        return -1;
                    }
                    return Double.compare(o1.getHeight(), o2.getHeight());
                }
            };
            try {
                FileWriter oneWriter = new FileWriter("shapesOutArr.txt");
                for (PrismPyramid p : arr) {
                    if (p == null) break;
                    oneWriter.write(p + "\n");
                }
                oneWriter.close();
                System.out.println("Successfully wrote to the file shapesOutArr,txt.");

                FileWriter twoWriter = new FileWriter("shapesOutList.txt");
                for (PrismPyramid p : list) {
                    if (p == null) break;
                    twoWriter.write(p + "\n");
                }
                twoWriter.close();
                System.out.println("Successfully wrote to the file shapesOutList,txt.");

                FileWriter threeWriter = new FileWriter("shapesSortOutMap.txt");
                for (List<PrismPyramid> pList : map.values()) {
                    for (PrismPyramid p : pList) {
                        if (p == null) break;
                        threeWriter.write(p + "\n");
                    }
                }
                threeWriter.close();
                System.out.println("Successfully wrote to the file shapesSortOutMap,txt.");

                FileWriter fourWriter = new FileWriter("shapesSortOutList.txt");
                Collections.reverse(list);
                list.sort(comparator);
                for (PrismPyramid p : list) {
                    if (p == null) break;
                    fourWriter.write(p + "\n");
                }
                fourWriter.close();
                System.out.println("Successfully wrote to the file shapesSortOutList,txt.");

                FileWriter fiveWriter = new FileWriter("Arrays.sort.txt");
                Arrays.sort(arr, comparator);
                for (PrismPyramid p : arr) {
                    if (p == null) break;
                    fiveWriter.write(p + "\n");
                }
                fiveWriter.close();
                System.out.println("Successfully wrote to the file Arrays.sort,txt.");
            }
            catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            }
            try {
                FileWriter OrelWriter = new FileWriter("ExceptionBugs.txt");
                for (String p : errors) {
                    if (p == null) break;
                    OrelWriter.write(p + "\n");
                }
                OrelWriter.close();
                System.out.println("Successfully wrote to the file ExceptionBugs.txt.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }
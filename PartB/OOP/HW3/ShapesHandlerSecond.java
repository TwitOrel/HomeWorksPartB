import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ShapesHandlerSecond {
    private static PrismPyramid[] arr;
    private static  final List<PrismPyramid> list = new LinkedList<>();
    private static final Map<Double, List<PrismPyramid>> map = new TreeMap<>();
    private static final List <String> bugs = new LinkedList<>();

    public static void main(String[] args){
        try {
            init(args[0],Integer.valueOf(args[1]));
        } catch (FileNotFoundException e) {
            System.out.println("cant find data base file!");
        }
    }
    private static void init(String filename, int numOfShapes)throws FileNotFoundException {
        int i =0;
        PrismPyramid shape;
        arr = new PrismPyramid[numOfShapes];
        Scanner s = new Scanner(new File(filename));
        while (s.hasNext()) {
            String line = s.nextLine();
            Scanner w = new Scanner(line);
            w.useDelimiter("[#:,]");
            while (w.hasNext()){
                String word = w.next();
                if (word.equals("Y")) {
                    String type = w.next();
                    if (type.equals("C")) {
                        String param = w.next() + "," + w.next() + "," + w.next();
                        shape = new Pyramid(type, param, w.nextDouble(), w.nextDouble(), w.nextDouble(), w.nextDouble());
                    }
                    else {
                        String param = w.next()+","+w.next()+","+w.next()+","+ w.next();
                        shape = new Pyramid(type, param,w.nextDouble(),w.nextDouble(), w.nextDouble(),w.nextDouble());
                    }
                    list.add(i, shape);
                    arr[i] = shape;
                    if (!map.containsKey(shape.getHeight())) {
                        map.put(shape.getHeight(), new LinkedList<>());
                    }
                    map.get(shape.getHeight()).add(shape);
                }
                // word = "R"
                else  {
                    String type = w.next();
                    if (type.equals("R")) {
                        String param = w.next() + "," + w.next() + "," + w.next()+ "," + w.next();
                        shape = new Prism(type, param, w.nextDouble(), w.nextDouble());
                    }
                    else {
                        String param = w.next() + "," + w.next() + "," + w.next();
                        shape = new Prism(type, param, w.nextDouble(), w.nextDouble());
                    }
                    list.add(i,shape);
                    arr[i] = shape;
                    if (!map.containsKey(shape.getHeight())) {
                        map.put(shape.getHeight(), new LinkedList<>());
                    }
                    map.get(shape.getHeight()).add(shape);
                }
                i++;
            }
        }
        Collections.reverse(list);
        try {
            FileWriter myWriter = new FileWriter("shapesOutArr.txt");
            for (PrismPyramid p : arr) {
                if (p==null) break;
                myWriter.write(p+"\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file shapesOutArr,txt.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            FileWriter myWriter = new FileWriter("shapesOutList.txt");
            for (PrismPyramid p : list) {
                if (p == null) break;
                myWriter.write(p+"\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file shapesOutList,txt.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            FileWriter myWriter = new FileWriter("shapesSortOutMap.txt");
            for (List<PrismPyramid> pList : map.values()) {
                for (PrismPyramid p : pList) {
                    if (p == null) break;
                    myWriter.write(String.valueOf(p) + "\n");
                }
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file shapesSortOutMap,txt.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // Comparator to sort PrismPyramid objects by height.
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
            FileWriter myWriter = new FileWriter("shapesSortOutList.txt");
            Collections.reverse(list);
            list.sort(comparator);
            for (PrismPyramid p : list) {
                    if (p == null) break;
                    myWriter.write(String.valueOf(p) + "\n");
                }
            myWriter.close();
            System.out.println("Successfully wrote to the file shapesSortOutList,txt.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            FileWriter myWriter = new FileWriter("Arrays.sort.txt");
            Arrays.sort(arr,comparator);
            for (PrismPyramid p : arr) {
                if (p == null) break;
                myWriter.write(String.valueOf(p) + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file Arrays.sort,txt.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
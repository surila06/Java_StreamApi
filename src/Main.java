import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");


        List<Integer> list = Arrays.asList(1,4,5,6,22,3,90,89,2,1,3,4,55,6);

        Stream<Integer> numStream=list.stream();

        numStream.forEach(n-> System.out.println(n));



    }
}
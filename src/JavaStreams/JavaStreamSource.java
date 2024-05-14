package JavaStreams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JavaStreamSource {
//    Convert Array of String to Stream
public static void main(String[] args) {

    useArraysStreamMethod().forEach(System.out::print);

    System.out.println("\n-----------");

    useStreamOfMethod().forEach(System.out::print);

    System.out.println("\n-----------");

    useStreamConcatToCombineStreams().forEach(System.out::print);
    System.out.println("\n-----------");
    Arrays.stream(printAlphabetsUsingIntStream().toArray()).map(i -> {
        char c = (char)i;
        return Character.toString((char) i).charAt(0);
    } ).mapToObj(i-> String.format("%c", (char)i))
//            .skip(1)
//            .dropWhile(i->i<='E')
//            .takeWhile(i->i<'a')
            .forEach(System.out::print);
//    System.out.println(String.format("%c", (char)65));
//            .forEach(s->System.out.printf("%c",s));;
}

    private static IntStream printAlphabetsUsingIntStream() {
        return IntStream.iterate((int)'A', i-> i<=(int)'z', i->i+1)
                .filter(Character::isAlphabetic);

    }

    private static Stream useStreamConcatToCombineStreams() {
        return Stream.concat(useArraysStreamMethod(),useStreamOfMethod());
    }

    private static Stream<String> useStreamOfMethod() {
        return Stream.of("One ", "Two ", "Three ").sorted(Comparator.reverseOrder());
    }

    private static Stream<String> useArraysStreamMethod() {
        String[] s = {"Four ", "Five ", "Six "};
       return Arrays.stream(s).map(String::toUpperCase);
    }
}

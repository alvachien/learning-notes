package com.alvachien;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FlatMapExamples {

    public static void main(String[] args) throws IOException {
        // https://introcs.cs.princeton.edu/java/data/TomSawyer.txt

        Stream<String> stream1 = Files.lines(Paths.get("files/TomSawyer_01.txt"));
        Stream<String> stream2 = Files.lines(Paths.get("files/TomSawyer_02.txt"));
        Stream<String> stream3 = Files.lines(Paths.get("files/TomSawyer_03.txt"));
        Stream<String> stream4 = Files.lines(Paths.get("files/TomSawyer_04.txt"));

//        System.out.println("Stream 1 : " + stream1.count());
//        System.out.println("Stream 2 : " + stream2.count());
//        System.out.println("Stream 3 : " + stream3.count());
//        System.out.println("Stream 4 : " + stream4.count());

        Stream<Stream<String>> streamOfStream = Stream.of(stream1, stream2, stream3, stream4);
//        System.out.println("# streams: " + streamOfStream.count());

//        Stream<String> streamOfLines = streamOfStream.flatMap(stream -> stream);
        Stream<String> streamOfLines = streamOfStream.flatMap(Function.identity());
//        System.out.println("# lines: " + streamOfLines.count());

        Function<String, Stream<String>> lineSplitter =
                line -> Pattern.compile(" ").splitAsStream(line);

        Stream<String> streamOfWords = streamOfLines.flatMap(lineSplitter)
                .map(word -> word.toLowerCase(Locale.ROOT))
                //.filter(word ->word.length() > );
                .distinct();
        System.out.println("# words: " + streamOfWords.count());

    }
}

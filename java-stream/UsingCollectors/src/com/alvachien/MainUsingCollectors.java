package com.alvachien;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainUsingCollectors {
    public static void main(String... args) throws IOException {
        // Words.shakespeare.txt
        // https://introcs.cs.princeton.edu/java/data/words.shakespeare.txt

        // ospd.txt
        // https://introcs.cs.princeton.edu/java/data/ospd.txt

        Path shakespearePath = Paths.get("files/words.shakespeare.txt");
        Path ospdPath = Paths.get("files/ospd.txt");

        try(Stream<String> ospd = Files.lines(ospdPath);
            Stream<String> shakespeare = Files.lines(shakespearePath); ) {
            Set<String> scrabbleWords = ospd.collect(Collectors.toSet());
            Set<String> shakespeareWords = shakespeare.collect(Collectors.toSet());

            System.out.println("Scrabble : " + scrabbleWords.size());
            System.out.println("Shakespeare : " + shakespeareWords.size());

            // Version 1. Workable version
//            final int [] scrabbleENScore = {
//                    // a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p,  q, r, s, t, u, v, w, x, y,  z
//                    1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10
//            };
//            Function<String, Integer> score =
//                    word -> word.toLowerCase().chars()
//                            .map(letter -> scrabbleENScore[letter - 'a']).sum();
//
//            // Find the best word
//            Map<Integer, List<String>> histoWordsByScore =
//                    shakespeareWords.stream()
//                            .filter(word -> scrabbleWords.contains(word))
//                            .collect(Collectors.groupingBy(score));
//
//            System.out.println("# histoWordsByScore = " + histoWordsByScore.size());
//
//            histoWordsByScore.entrySet()
//                    .stream()
//                    .sorted(Comparator.comparing(entry -> -entry.getKey()))
//                    .limit(3)
//                    .forEach(entry -> System.out.println(entry.getKey() + " - " + entry.getValue()));

            // Version 2:
            final int [] scrabbleENScore = {
                    // a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p,  q, r, s, t, u, v, w, x, y,  z
                    1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10
            };
            Function<String, Integer> score =
                    word -> word.toLowerCase().chars()
                            .map(letter -> scrabbleENScore[letter - 'a']).sum();

            // Find the best word
            Map<Integer, List<String>> histoWordsByScore =
                    shakespeareWords.stream()
                            .filter(word -> scrabbleWords.contains(word))
                            .collect(Collectors.groupingBy(score));

            System.out.println("# histoWordsByScore = " + histoWordsByScore.size());

            histoWordsByScore.entrySet()
                    .stream()
                    .sorted(Comparator.comparing(entry -> -entry.getKey()))
                    .limit(3)
                    .forEach(entry -> System.out.println(entry.getKey() + " - " + entry.getValue()));

            int [] scrabbleENDistribution = {
                    // a, b, c, d,  e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z
                    9, 2, 2, 1, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1
            };

            Function<String, Map<Integer, Long>> historWord =
                    word -> word.chars().boxed()
                            .collect(Collectors.groupingBy(letter -> letter,
                                                           Collectors.counting())
                            );

            Function<String, Long> nBlanks =
                    word -> historWord.apply(word)
                            .entrySet()
                            .stream()
                            .mapToLong(
                                entry -> Long.max(entry.getValue() - (long)scrabbleENDistribution[entry.getKey() - 'a'],
                                                  0L)
                            )
                            .sum();

            System.out.println("# of blanks of whizzing : " + nBlanks.apply("whizzing"));

            Function<String, Integer> score2 =
                    word -> historWord.apply(word)
                            .entrySet()
                            .stream()
                            .mapToInt(
                                   entry -> scrabbleENScore[entry.getKey() - 'a'] *
                                            Integer.min(entry.getValue().intValue(),
                                                    scrabbleENDistribution[entry.getKey() - 'a'])
                            )
                            .sum();
            System.out.println("# scores for whizzing : " + score.apply("whizzing"));
            System.out.println("# scores 2 for whizzing : " + score2.apply("whizzing"));

            Map<Integer, List<String>> histoWordsByScore2 = shakespeareWords.stream()
                    .filter((scrabbleWords::contains))
                    .filter(word -> nBlanks.apply(word) <= 2)
                    .collect(Collectors.groupingBy(score2));

            histoWordsByScore2
                    .entrySet()
                    .stream()
                    .sorted(Comparator.comparing(entry -> -entry.getKey()))
                    .limit(3)
                    .forEach(entry -> System.out.println(entry.getKey() + " - " + entry.getValue()));

        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

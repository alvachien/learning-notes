package com.alvachien;

import com.alvachien.model.Actor;
import com.alvachien.model.Movie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainMoviesActors {
    public static void main(String... args) throws IOException {
        Set<Movie> movies = new HashSet<>();

        Stream<String> lines = Files.lines(Paths.get("files", "movies-mpaa.txt"));

        lines.forEach((String line) -> {
            String[] elements = line.split("/");
            String title = elements[0].substring(0, elements[0].lastIndexOf("(")).trim();
            String releaseYear = elements[0].substring(elements[0].lastIndexOf("(") + 1, elements[0].lastIndexOf(")"));

            if (releaseYear.contains(",")) {
                // Will skip the movie with ';'
                return;
            }

            Movie movie = new Movie(title, Integer.parseInt(releaseYear));
            for (int i = 1; i < elements.length; i ++) {
                String[] name = elements[i].split(", ");
                String lastName = name[0].trim();
                String firstName = "";
                if(name.length > 1) {
                    firstName = name[1].trim();
                }
                Actor actor = new Actor(lastName, firstName);
                movie.addActor(actor);
            }

            movies.add(movie);
        });

        System.out.println("# movies size = " + movies.size());

        // # of actors
        long numberOfActors = movies.stream()
                .flatMap(movie ->  movie.actors().stream())
                .distinct()
                .count();
        System.out.println("# of actors = " + numberOfActors);


        // # of actors that played in the greatest # of movies
        //Map<Actor, Long> collect =
        Map.Entry<Actor, Long> mostedViewedActor =
            movies.stream()
                .flatMap(movie -> movie.actors().stream())
                .collect(Collectors.groupingBy(
                        // actor -> actor,
                        Function.identity(),
                        Collectors.counting()
                ))
                .entrySet().stream()
                .max(
                        //Comparator.comparing(entry -> entry.getValue())
                        Map.Entry.comparingByValue()
                )
                .get();

        System.out.println("Most viewed actor : " + mostedViewedActor);

        // Actor that played in the greatest # of movie during a year
        // Idea:
        // Map<released year, Map<Actor, # of movie during that year>>
        //Map<Integer, HashMap<Actor, AtomicLong>> collect =
        Map.Entry<Integer, Map.Entry<Actor, AtomicLong>> actorPlayedInyear = movies.stream()
                .collect(Collectors.groupingBy(
                        movie -> movie.getReleaseYear(),
                        Collector.of(
                                () -> new HashMap<Actor, AtomicLong>(),    // Supplier
                                (map, movie) -> {
                                    movie.actors().forEach(
                                            actor -> map.computeIfAbsent(actor, a -> new AtomicLong())
                                                    .incrementAndGet()
                                    );
                                }, // Accumulator
                                (map1, map2) -> {
                                    map2.entrySet().forEach(
                                            entry2 -> map1.merge(
                                                    entry2.getKey(), entry2.getValue(),
                                                    (al1, al2) -> {
                                                        al1.addAndGet(al2.get());
                                                        return al1;
                                                    }
                                            )
                                    );
                                    return map1;
                                }, // Combiner
                                Collector.Characteristics.CONCURRENT
                        )
                )) // Map<Integer, HashMap<Actor, AtomicLong>>
                .entrySet().stream()
                .collect(
                        Collectors.toMap(
                                entry -> entry.getKey(),
                                entry -> entry.getValue().entrySet().stream()
                                        .max(
                                                Map.Entry.comparingByValue(Comparator.comparing(l -> l.get()))
                                        )
                                        .get()
                        )
                )
                .entrySet().stream()
                .max(Map.Entry.comparingByValue(
                        Comparator.comparing(
                                entry -> entry.getValue().get()
                        )
                ))
                .get();

        System.out.println("Actor that played in the greatest # of movie during a year" + actorPlayedInyear);
    }
}

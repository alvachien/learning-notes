package com.alvachien;

import com.alvachien.model.City;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ComputeStatistics {
    public static void main(String[] args) {
        String lineForNewYork = "1;New York; New York; 8 336 817; 780.9";

        Function<String, Double> lineToDensity = line -> {
            String[] split = line.split(";");
            String populationAsString = split[3];
            populationAsString = populationAsString.replace(" ", "");
            int population = Integer.parseInt(populationAsString);

            String landAreaAsString = split[4];
            landAreaAsString = landAreaAsString.replace(" ", "").replace(",", ".");
            double landArea = Double.parseDouble(landAreaAsString);

            return population / landArea;
        };
        ToDoubleFunction<String> lineToDensity2 = line -> {
            return lineToDensity.apply(line);
        };

        double density = lineToDensity.apply(lineForNewYork);
        System.out.println("Density of New York = " + density);

        Path path = Path.of("data/cities.csv");
        try (Stream<String> lines = Files.lines(path, StandardCharsets.ISO_8859_1);) {
//            double max = lines.skip(2)
//                    .map(line -> lineToDensity.apply(line))
//                    .max(Comparator.naturalOrder())
//                    .orElseThrow();
//            System.out.println("Max. density in USA is: " + max);
//
//            double min = lines.skip(2)
//                    .mapToDouble(line -> lineToDensity.apply(line))
//                    .min()
//                    .orElseThrow();
//            System.out.println("Min. density in USA is: " + min);


            DoubleSummaryStatistics doubleSummaryStatistics = lines.skip(2)
                    .mapToDouble(lineToDensity2)
                    .summaryStatistics();
            System.out.println("Statistics is: " + doubleSummaryStatistics);

        } catch (IOException exp) {
            exp.printStackTrace();
        }

        // Using Collector
        System.out.println("===========================================");
        System.out.println("Using collector:");
        Function<String, String> lineToNumber = line -> line.split(";")[1];
        try (Stream<String> lines = Files.lines(path, StandardCharsets.ISO_8859_1);) {
            Set<String> cities = lines.skip(2)
                    .map(lineToNumber)
                    .collect(Collectors.toSet());
            System.out.println("# cities = " + cities.size());

            List<String> nameOfCitiesStartWithA = cities.stream().filter(city -> city.startsWith("A"))
                    .collect(Collectors.toList());
            System.out.println("# cities starts with A : " + nameOfCitiesStartWithA.size());
            System.out.println(nameOfCitiesStartWithA);

            System.out.println("Covert from stream to array: Object[] ar = cities.stream().toArray(); ");
            //Object[] ar = cities.stream().toArray();
            System.out.println("Smart way to convert stream to an array: Object[] ar = cities.stream().toArray(String[]::new)");
            String[] cityNamesA = cities.stream().filter(city -> city.startsWith("A"))
                    .toArray(String[]::new);
            System.out.println("# cities starts with A (array) : " + cityNamesA.length);

            String joined = cities.stream()
                    .filter(name -> name.length() == 4)
                    .collect(Collectors.joining(", "));
            System.out.println("Join cities whose name length is 4: " + joined);
        } catch (IOException exp) {
            exp.printStackTrace();
        }

        String emptyStream = Stream.<String>empty()
                .collect(Collectors.joining(",", "[", "]"));
        System.out.println("Collecting an empty stream : " + emptyStream);

        String singleEntryStream = Stream.<String>of("one")
                .collect(Collectors.joining(",", "[", "]"));
        System.out.println("Collecting an single entry stream : " + singleEntryStream);

        System.out.println("===========================================");
        Function<String, City> lineToCity = line -> {
            String[] split = line.split(";");
            String cityName = split[1].trim();
            String state = split[2].trim();
            String popluationString = split[3];
            popluationString = popluationString.replace(" ", "");
            int poplulation = Integer.parseInt(popluationString);

            String landAreaString = split[4];
            landAreaString = landAreaString.replace(" ", "").replace(",", ".");
            double landArea = Double.parseDouble(landAreaString);

            return new City(cityName, state, poplulation, landArea);
        };

        try (Stream<String> lines = Files.lines(path, StandardCharsets.ISO_8859_1);) {
            Set<City> cities = lines.skip(2)
                    .map(lineToCity)
                    .collect(Collectors.toSet());
            System.out.println("# cities = " + cities.size());

            Map<String, List<City>> citiesPerState = cities.stream()
                    .collect(Collectors.groupingBy(
                            city -> city.getState()
                    ));
            System.out.println("# size of Map : " + citiesPerState.size());

            System.out.println(citiesPerState.get("Utah"));

            Map<String, Long> numberOfCitiesPerState =
            cities.stream()
                    .collect(Collectors.groupingBy(city -> city.getState(),Collectors.counting()));
            System.out.println(numberOfCitiesPerState);

            Map.Entry<String, Long> stateWithMostCities =
                    numberOfCitiesPerState.entrySet().stream()
                    // .max(Comparator.comparing(entry -> entry.getValue()))
                             .max(Map.Entry.comparingByValue())
                            .orElseThrow();
            System.out.println(stateWithMostCities);

            int populationOfUtah = citiesPerState.get("Utah").stream()
//                    .mapToInt(city -> city.getPopulation())
//                    .sum();
                    .collect(Collectors.summingInt(city -> city.getPopulation()));
            System.out.println("Population of Utah : " + populationOfUtah);

            Map<String, Integer> populationOfCitiesPerSate =
                    cities.stream()
                    .collect(Collectors.groupingBy(
                            city -> city.getState(),
                            Collectors.summingInt(city -> city.getPopulation())
                    ));
            System.out.println("Population of Utah : " + populationOfCitiesPerSate.get("Utah"));
            System.out.println("Population: " + populationOfCitiesPerSate);

            Map.Entry<String, Integer> entry = populationOfCitiesPerSate.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .orElseThrow();
            System.out.println(entry);
        } catch (IOException exp) {
            exp.printStackTrace();
        }
    }
}

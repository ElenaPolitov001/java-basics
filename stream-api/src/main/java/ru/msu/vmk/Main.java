package ru.msu.vmk;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        //classic
        Stream<String> strings = Arrays.asList("a", "b", "c").stream();

        //from values
        Stream<String> valuesStream = Stream.of("1", "2", "3");

        //from array
        Stream<Double> doubleStream = Arrays.stream(new Double[] {12.0d, 15.0d});

        //from file
        Stream<String> fromFile = Files.lines(Path.of(ClassLoader.getSystemResource("lenses.data").toURI()));
        //fromFile.forEach(System.out::println);

        //infinite stream from iterate

        var n = "abcd";

        Stream<Integer> iterateExample2 = Stream
                .iterate(1, l -> l + 1)
                .limit(n.length())
                .skip(n.length() -1);
        //iterateExample2.forEach(System.out::println);

        Stream<String> iterateExample = Stream
                .iterate("ab", l -> l + "a")
                .limit(3);
        //iterateExample.forEach(System.out::println);

        //infinite stream from generate

        Stream generateExample = Stream.generate(Math::random).limit(5);
        //generateExample.forEach(System.out::println);


        List<Employee> employees = Files
                .lines(Path.of(ClassLoader.getSystemResource("employees.data").toURI()))
               // .peek(System.out::println)
                .map(Employee::parse)
               // .peek(System.out::println)
                .collect(Collectors.toList());

        //employees.forEach(System.out::println);


        //filter example
        employees
                .stream()
                .filter(e -> e.getPosition() == Employee.Position.JUNIOR && e.getSalary() < 459)
        //        .forEach(System.out::println)
        ;

        //aggregate example
        Double grossSalaryAverage = employees
                .stream()
                .mapToDouble(e -> e.getSalary()*100/87)
                .average()
                .getAsDouble();
   //     System.out.println("grossSalaryAverage = " + grossSalaryAverage);
   //     System.out.println();

        //group example
        employees
                .stream()
                .collect(Collectors.groupingBy(Employee::getPosition))
                .entrySet()
        //        .forEach(System.out::println)
        ;

        System.out.println();

        //group with aggregate example
        employees
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getProfession,
                                Collectors.minBy(Comparator.comparingInt(Employee::getAge))))
                .entrySet()
                .forEach(System.out::println);

    }
}
package org.task4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Task4 {
    public static void main(String[] args) throws IOException {
        Path file = Path.of(args[0]);

        List<Long> numbers = Files.readAllLines(file).stream().mapToLong(Long::parseLong).boxed().toList();

        long average = Math.round(numbers.stream().mapToDouble(x -> x).average().getAsDouble());

        Long result = numbers.stream().map(x -> average - x).map(Math::abs).reduce(0L,Long::sum);

        System.out.println(result);
    }
}
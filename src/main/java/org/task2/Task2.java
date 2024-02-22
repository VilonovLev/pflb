package org.task2;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Task2 {
    public static void main(String[] args) throws IOException {
        List<String> strings = Files.readAllLines(Path.of(args[0]));
        List<String> dots = Files.readAllLines(Path.of(args[1]));

        Circle circle = getCircle(strings);

        for (String str : dots) {
            Dot dot = getDot(str);
            int result = isDotInCircle(circle, dot);
            System.out.println(result);
        }
    }

    private static int isDotInCircle(Circle circle, Dot dot) {
        int result = (dot.x().subtract(circle.dot().y()).pow(2))
                .add(dot.y().subtract(circle.dot().y()).pow(2))
                .compareTo(circle.radius().pow(2));
        return switch (result) {
            case -1 -> 1;
            case 1 -> 2;
            default -> 0;
        };
    }

    private static Circle getCircle(List<String> strings) {
        return new Circle(getDot(strings.get(0)),new BigDecimal(strings.get(1)));
    }

    private static Dot getDot(String dot) {
        String[] result = dot.split(" ");
        return new Dot(new BigDecimal(result[0]),new BigDecimal(result[1]));
    }
}
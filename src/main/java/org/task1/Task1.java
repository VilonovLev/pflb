package org.task1;

import java.util.*;
import java.util.stream.IntStream;

public class Task1 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        List<Integer> result = IntStream.range(1,n+1).boxed().toList();
        Iterator<Integer> iterator = result.iterator();
        StringBuilder sb = new StringBuilder();
        sb.append(iterator.next());

        while (true) {
            int next = 0;
            for (int i = 0; i < m - 1; i++) {
                if (!iterator.hasNext()) {
                    iterator = result.iterator();
                }
                next = iterator.next();
            }
            if (next == 1) {break;}
            sb.append(next);
        }
        System.out.println(sb);
    }
}
package com.hai.javase.java8;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams {
    private enum Status {
        OPEN, CLOSED
    }

    private static final class Task {
        private final Status status;
        private final Integer points;

        Task(final Status status, final Integer points) {
            this.status = status;
            this.points = points;
        }

        public Integer getPoints() {
            return points;
        }

        public Status getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return String.format("[%s, %d]", status, points);
        }
    }

    final Collection<Task> tasks = Arrays.asList(
            new Task(Status.OPEN, 5),
            new Task(Status.OPEN, 13),
            new Task(Status.CLOSED, 8)
    );

    @Test
    public void test1() {
        int result = tasks
                .stream()
                .filter(task -> task.getStatus() == Status.OPEN)
                .mapToInt(Task::getPoints)
                .sum();
        System.out.println(result);
    }

    @Test
    //parallel stream test
    public void test2() {
        int result = tasks
                .stream()
                .parallel()
//                .map(task -> task.getPoints())
                .map(Task::getPoints)
                .reduce(0, Integer::sum);
        System.out.println(result);

        int result2 = tasks
                .parallelStream()
//                .map(task -> task.getPoints())
                .map(Task::getPoints)
                .reduce(0, Integer::sum);
        System.out.println(result2);
    }

    @Test
    //groupingBy...
    public void test3() {
        Map<Status, List<Task>> map = tasks.parallelStream().collect(Collectors.groupingBy(Task::getStatus));
        System.out.println(map);
    }

    @Test
    //TODO 未跑通
    public void test4() {
        int totalPoints = tasks.parallelStream().mapToInt(Task::getPoints).sum();
        Collection<String> result = tasks
                .parallelStream()
                .mapToInt(Task::getPoints)
                .asLongStream()
                .mapToDouble(points -> points / totalPoints)
                .boxed()
                .mapToLong(weight -> (long) (weight * 100))
                .mapToObj(percentage -> percentage + "%")
                .collect(Collectors.toList());
        System.out.println(totalPoints);
        System.out.println(result);
    }

    @Test
    //stream op io...
    public void test5() {
        Path path = new File("D:\\Data\\notes\\mysql-trigger.txt").toPath();
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            lines
                    .onClose(() -> System.out.println("Done!"))
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
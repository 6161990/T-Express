package com.yoon.TExpress.modernJavaInAction.eight;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LambdaTest {

    @Test
    void step1_movieRightBy() {
        Point p1 = new Point(5, 5);
        Point p2 = p1.moveRightBy(10);

        assertThat(15).isEqualTo(p2.getX());
        assertThat(5).isEqualTo(p2.getY());
    }

    @Test
    void step2_comparingTwoPoints() {
        Point p1 = new Point(10, 15);
        Point p2 = new Point(10, 20);

        int compare = Point.compareByXAndThenY.compare(p1, p2);

        assertTrue(compare < 0);
    }

    @Test
    void step3() {
        List<Point> points = Arrays.asList(new Point(5, 5), new Point(10, 5));
        List<Point> expectedPoints = Arrays.asList(new Point(15, 5), new Point(20, 5));

        List<Point> newPoints = Point.moveAllPointsRightBy(points, 10);

        assertThat(expectedPoints).isEqualTo(newPoints);
    }



    private static class Point {
        public final static Comparator<Point> compareByXAndThenY =
                Comparator.comparing(Point::getX).thenComparing(Point::getY);
        private final int x;
        private final int y;

        public static List<Point> moveAllPointsRightBy(List<Point> points, int x){
            return points.stream().map(p -> new Point(p.getX() + x, p.getY())).collect(toList());
        }

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Point moveRightBy(int x){
            return new Point(this.x + x, this.y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}

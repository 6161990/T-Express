package com.yoon.TExpress.modernJavaInAction.eight;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LambdaTest {

    @Test
    void step1_movieRightBy() {
        Point p1 = new Point(5, 5);
        Point p2 = p1.moveRightBy(10);

        assertThat(15).isEqualTo(p2.getX());
        assertThat(5).isEqualTo(p2.getY());
    }

    private static class Point {
        private final int x;
        private final int y;

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
    }
}

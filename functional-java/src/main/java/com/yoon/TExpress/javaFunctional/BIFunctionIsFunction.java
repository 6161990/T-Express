package com.yoon.TExpress.javaFunctional;

import java.util.function.BiFunction;
import java.util.function.Function;

public class BIFunctionIsFunction {

    public static <R> R powToString(Integer a1, Integer a2, BiFunction<Integer, Integer, Double> func,
                                    Function<Double, R> func2){
        return func.andThen(func2).apply(a1, a2);
    }

    public static <R extends GPS> R factory(String Latitude, String Longitude,
                                            BiFunction<String, String, R> func){
        return func.apply(Latitude, Longitude);
    }

    public static class GPS {
        String Latitude;
        String Longitude;

        public GPS(String latitude, String longitude) {
            Latitude = latitude;
            Longitude = longitude;
        }

        public String getLatitude() {
            return Latitude;
        }

        public void setLatitude(String latitude) {
            Latitude = latitude;
        }

        public String getLongitude() {
            return Longitude;
        }

        public void setLongitude(String longitude) {
            Longitude = longitude;
        }

        @Override
        public String toString() {
            return "GPS{" +
                    "Latitude='" + Latitude + '\'' +
                    ", Longitude='" + Longitude + '\'' +
                    '}';
        }
    }
}

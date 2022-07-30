package com.yoon.TExpress.javaFunctional;

import com.yoon.TExpress.javaFunctional.BIFunctionIsFunction.GPS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class BIFunction5 {

    @Test
    void BIFunction_2개의_인자_Type_T_U를_받고_1개의_객체R_를_리턴하는_함수형_인터페이스() {
/**     리팩토링 to 16 line
        String actual = sut.powToString(2, 4, (a1, a2) -> Math.pow(a1, a2), (r) -> "Result: " + String.valueOf(r));
 * */
        String actual = BIFunctionIsFunction.powToString(2, 4, Math::pow, (r) -> "Result: " + r);

        Assertions.assertEquals(actual, "Result: 16.0");
    }

    @Test
    void BIFunction_factoryTest() {
/**     리팩토링 to 26 line
        BIFunctionIsFunction.GPS sut = BIFunctionIsFunction.factory("40.782429", "-94.390121", (a1, a2)-> new BIFunctionIsFunction.GPS(a1, a2));
 * */
        GPS sut = BIFunctionIsFunction.factory("40.782429", "-94.390121", GPS::new);

        Assertions.assertEquals(sut.toString(), "GPS{Latitude='40.782429', Longitude='-94.390121'}");
    }

    @Test
    void BiFunction_withMapTest() {
        Map<Integer, String> map = new HashMap<>();
        String msg = "Hello ";
        String defaultUser = "Anonymous";

        map.put(7, "Peter");
        map.put(5, "Philip");
        map.put(3, "Martin");
        map.put(4, null);
        System.out.println("Init HashMap: " +  map);

        BiFunction<Integer, String, String> f1 = (key, person) -> person == null ? defaultUser : person.toUpperCase();
        map.compute(4, f1);
        map.compute(3, f1);
        Assertions.assertEquals(map.get(4), "Anonymous");
        Assertions.assertEquals(map.get(3), "MARTIN");

        BiFunction<Integer, String, String> f2 = (key, oldValue) -> msg + oldValue + "!";
        map.computeIfPresent(7, f2);
        map.computeIfPresent(5, f2);
        Assertions.assertEquals(map.get(7), "Hello Peter!");
        Assertions.assertEquals(map.get(5), "Hello Philip!");
    }
}

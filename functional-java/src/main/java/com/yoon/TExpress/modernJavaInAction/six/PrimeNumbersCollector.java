package com.yoon.TExpress.modernJavaInAction.six;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

public class PrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

    /**  두 개의 빈 리스트를 포함하는 맵으로 수집 동작 실행 */
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<>() {{
            put(true, new ArrayList<>());
            put(false, new ArrayList<>());
        }};
    }

    /**  현재까지 발견한 소수 리스트를 isPrime 메서드로 전달 */
    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
            acc.get(isPrime(acc.get(true), candidate)).add(candidate);
        };
    }

    private Boolean isPrime(List<Integer> primes, Integer candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return customTakeWhile(primes, i -> i <= candidateRoot)
                .stream()
                .noneMatch(p -> candidate % p ==0);
    }

    public static <A> List<A> customTakeWhile(List<A> list, Predicate<A> p){
        int i = 0;
        for (A item : list){
            if(!p.test(item)){
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }

    /**  두 번째 맵을 첫 번째 맵에 병합 */
    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() { // 학습 목적의 구현
        return (trueMap, falseMap) -> {
            trueMap.get(true).addAll(falseMap.get(false));
            return trueMap;
        };
    }

    /**  최종 수집 과정에서 데이터 변환이 필요하지 않으므로 항등 함수를 반환 */
    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    /**  발견한 소수의 순서에 의미가 있으므로 컬렉터는 IDENTITY_FINISH */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }

}
